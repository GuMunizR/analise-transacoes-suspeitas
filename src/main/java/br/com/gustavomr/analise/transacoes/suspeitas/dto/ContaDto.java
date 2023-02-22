package br.com.gustavomr.analise.transacoes.suspeitas.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class ContaDto {

    private String banco;
    private String agencia;
    private String  conta;
    private BigDecimal valorMovimento;
    private String tipoMovimento;

    public ContaDto(String banco, String agencia, String conta, BigDecimal valorMovimento, String tipoMovimento) {
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
        this.valorMovimento = valorMovimento;
        this.tipoMovimento = tipoMovimento;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContaDto contaDto = (ContaDto) o;
        return Objects.equals(banco, contaDto.banco) && Objects.equals(agencia, contaDto.agencia) && Objects.equals(conta, contaDto.conta) && Objects.equals(tipoMovimento, contaDto.tipoMovimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banco, agencia, conta, tipoMovimento);
    }

    public void somarValor(BigDecimal valorMovimento) {
        this.valorMovimento = this.valorMovimento.add(valorMovimento);
    }
}
