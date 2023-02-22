package br.com.gustavomr.analise.transacoes.suspeitas.config.security;

import br.com.gustavomr.analise.transacoes.suspeitas.model.Usuario;
import br.com.gustavomr.analise.transacoes.suspeitas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if(usuarioOpt.isPresent()){
            Usuario  usuario = usuarioOpt.get();
            return new User(usuario.getUsername(), usuario.getPassword(), usuario.getAtivo(), true, true, true, usuario.getPerfis());
        }
        throw new UsernameNotFoundException("Usuario não encontrado");
    }
}
