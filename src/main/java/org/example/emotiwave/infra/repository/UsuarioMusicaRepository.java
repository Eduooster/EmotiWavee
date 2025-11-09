package org.example.emotiwave.infra.repository;

import java.time.LocalDate;
import java.util.List;

import io.micrometer.common.KeyValues;
import org.example.emotiwave.application.dto.in.MusicaSimplesDto;
import org.example.emotiwave.domain.entities.Musica;
import org.example.emotiwave.domain.entities.Usuario;
import org.example.emotiwave.domain.entities.UsuarioMusica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioMusicaRepository extends JpaRepository<UsuarioMusica, Long> {

    @Query("    SELECT\n       new org.example.emotiwave.application.dto.in.MusicaSimplesDto(m.urlImg ,\n       m.titulo,\n            m.artista,\n            m.spotifyTrackId,\n            m.artistaId,\n            m.genero\n \n        )\n    FROM UsuarioMusica um\n    JOIN um.musica m\n    GROUP BY\n        m.titulo, m.artista, m.spotifyTrackId,m.artistaId, m.genero\n    ORDER BY\n        COUNT(um.usuario.id) DESC\n")
    Page<MusicaSimplesDto> findMusicasMaisOuvidas(Pageable pageable);

    @Query("    SELECT\n   new org.example.emotiwave.application.dto.in.MusicaSimplesDto(\n        m.titulo,\n        m.artista,\n        m.spotifyTrackId,\n        m.artistaId,\n        m.genero,\n m.urlImg     )\nFROM UsuarioMusica um\nJOIN um.musica m\nWHERE um.usuario = :usuario and um.ouvidaEm = current_date\nORDER BY\n    um.ouvidaEm desc\n\n\n")
    List<MusicaSimplesDto> findMusicasOuvidasHoje(@Param("usuario") Usuario usuario, Pageable pageable);

    Page<UsuarioMusica> findByUsuarioAndOuvidaEmAndSelecionadaTrue(Usuario usuario, LocalDate ouvidaEm, Pageable paginacao);

    UsuarioMusica findByMusica_SpotifyTrackIdAndUsuarioId(String spotifyTrackId, Long usuarioId);

    UsuarioMusica findByMusicaId(Integer musicaId);

    UsuarioMusica findByUsuarioIdAndMusicaSpotifyTrackId(Long usuarioId, String spotifyTrackId);



    @Query("""
    SELECT um.musica 
    FROM UsuarioMusica um
    WHERE um.usuario.id = :usuarioId
      AND um.ouvidaEm = :dataHoje
""")
    List<Musica> findMusicasOuvidasHoje(
            @Param("usuarioId") Long usuarioId,
            @Param("dataHoje") LocalDate dataHoje,
            Pageable pageable
    );

    @Query("""
    SELECT um.musica 
    FROM UsuarioMusica um 
    WHERE um.usuario.id = :userId
""")
    Page<Musica> findMusicasByUsuarioId(@Param("userId") Long userId,Pageable pageable);

    @Query("""
    SELECT um.musica 
    FROM UsuarioMusica um 
    WHERE um.usuario.id = :userId
""")
    List<Musica> findMusicasByUsuarioIdv2(@Param("userId") Long userId);


}

