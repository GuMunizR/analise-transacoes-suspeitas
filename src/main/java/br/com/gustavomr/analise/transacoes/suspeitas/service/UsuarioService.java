package br.com.gustavomr.analise.transacoes.suspeitas.service;

import br.com.gustavomr.analise.transacoes.suspeitas.dto.UsuarioDto;
import br.com.gustavomr.analise.transacoes.suspeitas.model.Usuario;
import br.com.gustavomr.analise.transacoes.suspeitas.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private JavaMailSender emailSender;

    public List<UsuarioDto> listarUsuarios(){

        return usuarioRepository.buscarTodos().stream().map(UsuarioDto::new).collect(Collectors.toList());
    }

    public String cadastrarUsuario(String nome, String email){
        if(usuarioRepository.findByEmail(email).isPresent()){
            return "err: Já existe usuário com esse email";
        }
        Usuario usuario = new Usuario(nome, email);
        String senha = usuario.geradorSenhaAleatoria();
        enviarEmail(senha, usuario.getEmail());
        usuarioRepository.save(usuario);
        return "scss: Sua senha para se logar foi enviada no email";
    }

    private void enviarEmail(String senha, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(email);
        message.setSubject("Sua senha para acessar a aplicacao (Não Responda)");
        message.setText("Abaixo  senha para poder se logar na aplicacao: \n " + senha);
        emailSender.send(message);
    }

    @Transactional
    public String alterar(Long id, String nome, String email){
        Usuario usuario = usuarioRepository.findById(id).get();
        if(! email.equals(usuario.getEmail()) && usuarioRepository.findByEmail(email).isPresent()){
            return "O email informado já existe na aplicacão";
        }
        usuario.setEmail(email);
        usuario.setNome(nome);
        usuarioRepository.save(usuario);
        return "Usuario alterado com sucesso";
    }

    public UsuarioDto buscarPorId(Long id) {
        return new UsuarioDto(usuarioRepository.findById(id).get());
    }

    public void desativarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).get();
    }
}
