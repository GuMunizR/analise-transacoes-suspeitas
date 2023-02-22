package br.com.gustavomr.analise.transacoes.suspeitas.dto;


import br.com.gustavomr.analise.transacoes.suspeitas.model.Importacao;
import br.com.gustavomr.analise.transacoes.suspeitas.model.Transacao;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DetalharDto {

    private String nomeUsuario;
    private LocalDateTime dataImportacao;
    private LocalDate dataTransacao;
    private List<Transacao> listaTransacao;

    public DetalharDto() {
    }

    public DetalharDto(Importacao importacao, List<Transacao> listaTransacao) {
        this.nomeUsuario = importacao.getUsuario().getNome();
        this.dataImportacao = importacao.getDataImportacao();
        this.dataTransacao = importacao.getDataTransacao();
        this.listaTransacao = listaTransacao;
    }
}
