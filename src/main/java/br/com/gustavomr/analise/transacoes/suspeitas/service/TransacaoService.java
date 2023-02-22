package br.com.gustavomr.analise.transacoes.suspeitas.service;

import br.com.gustavomr.analise.transacoes.suspeitas.model.Importacao;
import br.com.gustavomr.analise.transacoes.suspeitas.model.Transacao;
import br.com.gustavomr.analise.transacoes.suspeitas.repository.TransacaoRepository;
import br.com.gustavomr.analise.transacoes.suspeitas.util.LeitorTransacaoCsv;
import br.com.gustavomr.analise.transacoes.suspeitas.util.LeitorTransacaoXml;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TransacaoService {

    private TransacaoRepository transacaoRepository;
    private ImportacaoService importacaoService;
    private UsuarioService usuarioService;

    public String salvarTransacao(MultipartFile arquivo) {
        try{
            List<Transacao> transacoes = null;
            if(arquivo.getContentType().contains("xml")){
                transacoes = new LeitorTransacaoXml().lerTransacoes(new InputStreamReader(arquivo.getInputStream()));
            }
            else if(arquivo.getContentType().contains("csv")){
                transacoes = new LeitorTransacaoCsv().lerTransacoes(new InputStreamReader(arquivo.getInputStream()));
            }
            else{
                return "err: Formato de arquivo invalido";
            }
            if(transacoes.size() == 0){
                return "err: O arquivo está vazio";
            }
            String data = transacoes.get(0).getData().toString().substring(0, transacoes.get(0).getData().toString().indexOf("T"));
            if(existeBanco(data)){
                return "err: Já foi feita uma importação com essa data de transações";
            }
            transacoes = transacoes.stream()
                    .filter(x-> x.getData().toString().contains(data))
                    .collect(Collectors.toList());
            transacoes.forEach(transacaoRepository::save);

            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            importacaoService.salvar(new Importacao(LocalDateTime.now(), LocalDate.parse(data), usuarioService.buscarPorEmail(email)));
        }
        catch (Exception e){
            return "err: Não foi possível ler o arquivo, verifique o arquivo e tente novamente";
        }
        return "scss: Transações salvas com sucesso";
    }

    private boolean existeBanco(String data){
        return transacaoRepository.findByData(LocalDate.parse(data)).isPresent();
    }

    public List<Transacao> listarTransacoesData(LocalDate data) {
        return transacaoRepository.listaPorData(data);
    }
}
