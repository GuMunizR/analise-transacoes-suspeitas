package br.com.gustavomr.analise.transacoes.suspeitas.repository;

import br.com.gustavomr.analise.transacoes.suspeitas.model.Importacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImportacaoRepository extends JpaRepository<Importacao, Long> {

}
