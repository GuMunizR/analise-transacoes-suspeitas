package br.com.gustavomr.analise.transacoes.suspeitas.service;

import br.com.gustavomr.analise.transacoes.suspeitas.dto.AgenciaDto;
import br.com.gustavomr.analise.transacoes.suspeitas.dto.ContaDto;
import br.com.gustavomr.analise.transacoes.suspeitas.model.Transacao;
import br.com.gustavomr.analise.transacoes.suspeitas.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AnaliseService {

    private TransacaoRepository transacaoRepository;


    public List<Transacao> analisarTransacao(Integer ano, Integer mes) {
        return transacaoRepository.analiseTransacao(ano, mes);
    }

    public List<ContaDto> analisarConta(Integer ano, Integer mes) {
         List<Transacao> listaTransacoes = transacaoRepository.analiseConta(ano, mes);
         List<ContaDto> contas = new ArrayList<>();
         for(Transacao transacao : listaTransacoes){
             ContaDto contaSaida = new ContaDto(transacao.getBancoOrigem(), transacao.getAgenciaOrigem(), transacao.getContaOrigem(),
                     transacao.getValorTransacao(), "SAIDA");
             ContaDto contaEntrada = new ContaDto(transacao.getBancoDestino(), transacao.getAgenciaDestino(), transacao.getContaDestino(),
                     transacao.getValorTransacao(), "ENTRADA");
             if(! contas.contains(contaSaida)){
                 contas.add(contaSaida);
             }
             else{
                 contas.get(contas.indexOf(contaSaida)).somarValor(contaSaida.getValorMovimento());
             }
             if(! contas.contains(contaEntrada)){
                 contas.add(contaEntrada);
             }
             else{
                 contas.get(contas.indexOf(contaEntrada)).somarValor(contaEntrada.getValorMovimento());
             }
         }
         contas.removeIf(conta -> conta.getValorMovimento().compareTo(new BigDecimal(1000000)) <= 0);
        return contas;
    }

    public List<AgenciaDto> analisarAgencia(Integer ano, Integer mes) {
        List<Transacao> listaTransacoes = transacaoRepository.analiseConta(ano, mes);
        List<AgenciaDto> agencias = new ArrayList<>();
        for(Transacao transacao : listaTransacoes){
            AgenciaDto agenciaSaida = new AgenciaDto(transacao.getBancoOrigem(), transacao.getAgenciaOrigem(),
                    transacao.getValorTransacao(), "SAIDA");
            AgenciaDto agenciaEntrada = new AgenciaDto(transacao.getBancoDestino(), transacao.getAgenciaDestino(),
                    transacao.getValorTransacao(), "ENTRADA");
            if(! agencias.contains(agenciaSaida)){
                agencias.add(agenciaSaida);
            }
            else{
                agencias.get(agencias.indexOf(agenciaSaida)).somarValor(agenciaSaida.getValorMovimento());
            }
            if(! agencias.contains(agenciaEntrada)){
                agencias.add(agenciaEntrada);
            }
            else{
                agencias.get(agencias.indexOf(agenciaEntrada)).somarValor(agenciaEntrada.getValorMovimento());
            }
        }
        agencias.removeIf(conta -> conta.getValorMovimento().compareTo(new BigDecimal(1000000000)) <= 0);
        return agencias;
    }
}
