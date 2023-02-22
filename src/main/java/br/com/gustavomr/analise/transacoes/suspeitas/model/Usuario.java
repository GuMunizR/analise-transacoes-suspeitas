package br.com.gustavomr.analise.transacoes.suspeitas.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
public class Usuario implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;
    private String senha;

    private Boolean ativo;

    @ManyToMany
    @JoinTable(name="tb_Usuarios_perfis", joinColumns = @JoinColumn(name = "id_usuarios"),
            inverseJoinColumns = @JoinColumn(name = "id_perfis"))
    private List<Perfil> perfis = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.ativo = true;
    }

    public String geradorSenhaAleatoria(){
        String letras = "abcdefghijklmnopqrstuvwxyz123456789ABCDEFGHIJKLMNOPRSTUVWXYZ@-&_?";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder senha = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            senha.append(letras.charAt(secureRandom.nextInt(letras.length())));
        }
        setSenha(senha.toString());
        return senha.toString();
    }

    private void setSenha(String senha){
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
