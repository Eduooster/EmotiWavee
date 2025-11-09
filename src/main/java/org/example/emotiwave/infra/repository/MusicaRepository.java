package org.example.emotiwave.infra.repository;

import org.example.emotiwave.application.dto.in.MusicaSimplesDto;
import org.example.emotiwave.domain.entities.Musica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {
    Musica findBySpotifyTrackId(String spotifyTrackId);

    Musica findByArtistaAndTitulo(String artista, String titulo);

    @Query("SELECT m FROM Musica m WHERE m.analise.polaridade = :polaridade")
    Page<Musica> findByPolaridade(@Param("polaridade") String polaridade, Pageable paginacao);


}
