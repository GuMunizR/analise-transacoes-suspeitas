package br.com.gustavomr.analise.transacoes.suspeitas.service;

import br.com.gustavomr.analise.transacoes.suspeitas.model.Importacao;
import br.com.gustavomr.analise.transacoes.suspeitas.repository.ImportacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ImportacaoService {

    private ImportacaoRepository importacaoRepository;

    public List<Importacao> listarImportacao(){
        return importacaoRepository.findAll();
    }

    public Importacao buscarPorId(Long id) {
        return importacaoRepository.findById(id).get();
    }

    public void salvar(Importacao importacao) {
        importacaoRepository.save(importacao);
    }
}
