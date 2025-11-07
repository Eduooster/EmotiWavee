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
        SELECT AVG(a.score)
        FROM t_analise_musica a
        JOIN t_musica m ON a.musica_id = m.id
        JOIN t_usuario_musica um ON um.musica_id = m.id
        WHERE um.usuario_id = :userId
          AND (:inicio IS NULL OR um.ouvida_em >= :inicio)
          AND (:fim IS NULL OR um.ouvida_em <= :fim)
        """, nativeQuery = true)
    Double buscarMediaScore(@Param("userId") Long userId,
                            @Param("inicio") LocalDate inicio,
                            @Param("fim") LocalDate fim);


    @Query(value = """
        SELECT COUNT(*)
        FROM t_analise_musica a
        JOIN t_musica m ON a.musica_id = m.id
        JOIN t_usuario_musica um ON um.musica_id = m.id
        WHERE um.usuario_id = :userId
          AND (:inicio IS NULL OR um.ouvida_em >= :inicio)
          AND (:fim IS NULL OR um.ouvida_em <= :fim)
        """, nativeQuery = true)
    Integer contarMusicas(@Param("userId") Long userId,
                          @Param("inicio") LocalDate inicio,
                          @Param("fim") LocalDate fim);


    @Query(value = """
        SELECT polaridade FROM (
            SELECT a2.polaridade, COUNT(*) AS freq
            FROM t_analise_musica a2
            JOIN t_musica m2 ON a2.musica_id = m2.id
            JOIN t_usuario_musica um2 ON um2.musica_id = m2.id
            WHERE um2.usuario_id = :userId
              AND (:inicio IS NULL OR um2.ouvida_em >= :inicio)
              AND (:fim IS NULL OR um2.ouvida_em <= :fim)
            GROUP BY a2.polaridade
            ORDER BY COUNT(*) DESC
        ) WHERE ROWNUM = 1
        """, nativeQuery = true)
    String buscarPolaridadePredominante(@Param("userId") Long userId,
                                        @Param("inicio") LocalDate inicio,
                                        @Param("fim") LocalDate fim);


    @Query(value = """
        SELECT label FROM (
            SELECT a3.label, COUNT(*) AS freq
            FROM t_analise_musica a3
            JOIN t_musica m3 ON a3.musica_id = m3.id
            JOIN t_usuario_musica um3 ON um3.musica_id = m3.id
            WHERE um3.usuario_id = :userId
              AND (:inicio IS NULL OR um3.ouvida_em >= :inicio)
              AND (:fim IS NULL OR um3.ouvida_em <= :fim)
            GROUP BY a3.label
            ORDER BY COUNT(*) DESC
        ) WHERE ROWNUM = 1
        """, nativeQuery = true)
    String buscarSentimentoPredominante(@Param("userId") Long userId,
                                        @Param("inicio") LocalDate inicio,
                                        @Param("fim") LocalDate fim);


    @Query(value = """
        SELECT intensidade FROM (
            SELECT a4.intensidade, COUNT(*) AS freq
            FROM t_analise_musica a4
            JOIN t_musica m4 ON a4.musica_id = m4.id
            JOIN t_usuario_musica um4 ON um4.musica_id = m4.id
            WHERE um4.usuario_id = :userId
              AND (:inicio IS NULL OR um4.ouvida_em >= :inicio)
              AND (:fim IS NULL OR um4.ouvida_em <= :fim)
            GROUP BY a4.intensidade
            ORDER BY COUNT(*) DESC
        ) WHERE ROWNUM = 1
        """, nativeQuery = true)
    String buscarIntensidadePredominante(@Param("userId") Long userId,
                                         @Param("inicio") LocalDate inicio,
                                         @Param("fim") LocalDate fim);




    @Query(value = """
    SELECT 
        ROUND(SUM(CASE WHEN a.polaridade = 'POSITIVO' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 1) AS percentualPositivo,
        ROUND(SUM(CASE WHEN a.polaridade = 'NEGATIVO' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 1) AS percentualNegativo,
        ROUND(SUM(CASE WHEN a.polaridade = 'NEUTRO' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 1) AS percentualNeutro
    FROM t_analise_musica a
    JOIN t_musica m ON a.musica_id = m.id
    JOIN t_usuario_musica um ON um.musica_id = m.id
    WHERE um.usuario_id = :userId
      AND (:inicio IS NULL OR um.ouvida_em >= :inicio)
      AND (:fim IS NULL OR um.ouvida_em <= :fim)
""", nativeQuery = true)
    EstatisticaResponse.PolaridadePercentual calcularPercentualPolaridade(@Param("userId") Long userId,
                                                                          @Param("inicio") LocalDate inicio,
                                                                          @Param("fim") LocalDate fim);






}


