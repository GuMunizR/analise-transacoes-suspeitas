package br.com.gustavomr.analise.transacoes.suspeitas.repository;

import br.com.gustavomr.analise.transacoes.suspeitas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(@Param("email") String email);
    @Query("SELECT u FROM Usuario u WHERE u.ativo = true")
    List<Usuario> buscarTodos();
}
