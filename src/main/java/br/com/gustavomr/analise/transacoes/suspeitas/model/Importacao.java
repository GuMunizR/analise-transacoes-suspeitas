package br.com.gustavomr.analise.transacoes.suspeitas.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Importacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataImportacao;
    private LocalDate dataTransacao;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    public Importacao() {
    }

    public Importacao(LocalDateTime dataImportacao, LocalDate dataTransacao, Usuario usuario) {
        this.dataImportacao = dataImportacao;
        this.dataTransacao = dataTransacao;
        this.usuario = usuario;
    }
}
