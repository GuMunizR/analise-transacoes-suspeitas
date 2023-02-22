package br.com.gustavomr.analise.transacoes.suspeitas.dto;

import br.com.gustavomr.analise.transacoes.suspeitas.model.Transacao;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDateTime;


@Setter
@Getter
@XStreamAlias("transacao")
public class TransacaoDto {

    private OrigemDto origem;
    private DestinoDto destino;
    private String valor;
    private String data;

    public Transacao getTransacao() {
        try{
            if(origem.atributosNaoNulo() || destino.atributosNaoNulo() || origem == null || destino == null){
                return null;
            }
            return  new Transacao(origem.getBanco(), origem.getAgencia(), origem.getConta(),
                    destino.getBanco(), destino.getAgencia(), destino.getConta(), new BigDecimal(valor), LocalDateTime.parse(data));
        }catch (NullPointerException | NumberFormatException | DateTimeException e){
            return null;
        }
    }
}

