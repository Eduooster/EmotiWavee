package org.example.emotiwave.application.service;


import org.example.emotiwave.application.dto.in.MusicaSimplesDto;
import org.example.emotiwave.application.mapper.MusicaMapper;
import org.example.emotiwave.application.service.spotifyServices.SpotifyService;
import org.example.emotiwave.domain.entities.AnaliseMusica;
import org.example.emotiwave.domain.entities.Musica;
import org.example.emotiwave.domain.entities.Usuario;
import org.example.emotiwave.domain.entities.UsuarioMusica;
import org.example.emotiwave.domain.exceptions.MusicaSpotifyNaoEncontrada;
import org.example.emotiwave.infra.repository.MusicaRepository;
import org.example.emotiwave.infra.repository.UsuarioMusicaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MusicaService {
    private final MusicaRepository musicaRepository;
    private final MusicaMapper musicaMapper;
    private final UsuarioMusicaRepository usuarioMusicaRepository;
    private final SpotifyService spotifyService;
    private final HuggingFaceZeroShotService huggingFaceZeroShotService;
    String BUSCAR_MUSICA = "https://api.spotify.com/v1/search?q=";

    private MusicaService(MusicaRepository musicaRepository, MusicaMapper musicaMapper, UsuarioMusicaRepository usuarioMusicaRepository, SpotifyService spotifyService, HuggingFaceZeroShotService huggingFaceZeroShotService) {
        this.musicaRepository = musicaRepository;
        this.musicaMapper = musicaMapper;
        this.usuarioMusicaRepository = usuarioMusicaRepository;
        this.spotifyService = spotifyService;
        this.huggingFaceZeroShotService = huggingFaceZeroShotService;
    }

    public Page<MusicaSimplesDto> listarMusicasMaisOuvidas(Pageable paginacao) {
        return this.usuarioMusicaRepository.findMusicasMaisOuvidas(paginacao);
    }

    public Musica buscarMusica(String artista, String titulo) {
        Musica musicaRepo = this.musicaRepository.findByArtistaAndTitulo(artista, titulo);
        if (musicaRepo == null) {
            throw new MusicaSpotifyNaoEncontrada("Falha na busca de musica");
        } else {
            return musicaRepo;
        }
    }


}

