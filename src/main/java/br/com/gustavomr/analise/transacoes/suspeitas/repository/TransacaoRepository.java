package br.com.gustavomr.analise.transacoes.suspeitas.repository;

import br.com.gustavomr.analise.transacoes.suspeitas.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query(value = "SELECT * FROM transacao t WHERE CAST(t.data AS DATE) = :data LIMIT 1",
    nativeQuery = true)
    Optional<Transacao> findByData(@Param("data") LocalDate data);

    @Query(value = "SELECT * FROM transacao t WHERE CAST(t.data AS DATE) = :data",
            nativeQuery = true)
    List<Transacao> listaPorData(@Param("data") LocalDate data);

    @Query("SELECT t FROM Transacao t WHERE YEAR(t.data) = :ano AND MONTH(t.data)= :mes AND t.valorTransacao >= 100000")
    List<Transacao> analiseTransacao(@Param("ano") Integer ano, @Param("mes") Integer mes);

    @Query("SELECT t FROM Transacao t WHERE YEAR(t.data) = :ano AND MONTH(t.data)= :mes")
    List<Transacao> analiseConta(Integer ano, Integer mes);
}
