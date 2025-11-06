package org.example.emotiwave.infra.repository;

import org.example.emotiwave.application.dto.out.EstatisticaResponse;
import org.example.emotiwave.domain.entities.AnaliseMusica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AnaliseMusicaRepository extends JpaRepository<AnaliseMusica, Long> {

    @Query(value = """
    SELECT
        (SELECT a2.polaridade FROM analise_musica a2
         WHERE a2.usuario_id = :userId
           AND (:inicio IS NULL OR a2.analisado_em >= :inicio)
           AND (:fim IS NULL OR a2.analisado_em <= :fim)
         GROUP BY a2.polaridade
         ORDER BY COUNT(*) DESC
         FETCH FIRST 1 ROWS ONLY) AS polaridade_predominante,

        AVG(a.score) AS mediaScore,

        (SELECT a3.label FROM analise_musica a3
         WHERE a3.usuario_id = :userId
           AND (:inicio IS NULL OR a3.analisado_em >= :inicio)
           AND (:fim IS NULL OR a3.analisado_em <= :fim)
         GROUP BY a3.label
         ORDER BY COUNT(*) DESC
         FETCH FIRST 1 ROWS ONLY) AS sentimento_predominante,

        (SELECT a4.intensidade FROM analise_musica a4
         WHERE a4.usuario_id = :userId
           AND (:inicio IS NULL OR a4.analisado_em >= :inicio)
           AND (:fim IS NULL OR a4.analisado_em <= :fim)
         GROUP BY a4.intensidade
         ORDER BY COUNT(*) DESC
         FETCH FIRST 1 ROWS ONLY) AS intensidade_predominante,

        COUNT(*) AS total_musicas_analisadas
    FROM analise_musica a
    WHERE a.usuario_id = :userId
      AND (:inicio IS NULL OR a.analisado_em >= :inicio)
      AND (:fim IS NULL OR a.analisado_em <= :fim)
    """, nativeQuery = true)
    EstatisticaResponse gerarEstatisticas(
            @Param("userId") Long userId,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim
    );
}

