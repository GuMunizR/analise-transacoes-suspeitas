package br.com.gustavomr.analise.transacoes.suspeitas.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XStreamAlias("origem")
public class OrigemDto{
    private String banco;
    private String agencia;
    private String conta;

    public boolean atributosNaoNulo() {
        if(banco == null || agencia == null || conta == null){
            return true;
        }
        return false;
    }
}
