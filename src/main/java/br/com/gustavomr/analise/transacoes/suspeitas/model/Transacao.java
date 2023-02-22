package br.com.gustavomr.analise.transacoes.suspeitas.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bancoOrigem;
    private String agenciaOrigem;
    private String contaOrigem;
    private String bancoDestino;
    private String agenciaDestino;
    private String contaDestino;
    private BigDecimal valorTransacao;
    private LocalDateTime data;

    public Transacao() {
    }
    public Transacao(String bancoOrigem, String agenciaOrigem, String contaOrigem, String bancoDestino, String agenciaDestino, String contaDestino, BigDecimal valorTransacao, LocalDateTime data) {
        this.bancoOrigem = bancoOrigem;
        this.agenciaOrigem = agenciaOrigem;
        this.contaOrigem = contaOrigem;
        this.bancoDestino = bancoDestino;
        this.agenciaDestino = agenciaDestino;
        this.contaDestino = contaDestino;
        this.valorTransacao = valorTransacao;
        this.data = data;
    }
}
