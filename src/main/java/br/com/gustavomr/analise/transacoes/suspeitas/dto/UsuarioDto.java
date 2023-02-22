package br.com.gustavomr.analise.transacoes.suspeitas.dto;

import br.com.gustavomr.analise.transacoes.suspeitas.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {

    private Long id;
    private String nome;
    private String email;

    public UsuarioDto() {
    }

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }
}
