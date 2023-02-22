package br.com.gustavomr.analise.transacoes.suspeitas.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class AgenciaDto {

    private String banco;
    private String agencia;
    private BigDecimal valorMovimento;
    private String tipoMovimento;

    public AgenciaDto(String banco, String agencia, BigDecimal valorMovimento, String tipoMovimento) {
        this.banco = banco;
        this.agencia = agencia;
        this.valorMovimento = valorMovimento;
        this.tipoMovimento = tipoMovimento;
    }

    public void somarValor(BigDecimal valorMovimento) {
        this.valorMovimento = this.valorMovimento.add(valorMovimento);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgenciaDto that = (AgenciaDto) o;
        return Objects.equals(banco, that.banco) && Objects.equals(agencia, that.agencia) && Objects.equals(tipoMovimento, that.tipoMovimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banco, agencia, tipoMovimento);
    }
}
