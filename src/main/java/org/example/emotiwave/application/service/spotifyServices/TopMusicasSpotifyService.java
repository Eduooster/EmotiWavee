package org.example.emotiwave.application.service.spotifyServices;


import jakarta.transaction.Transactional;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.example.emotiwave.application.dto.in.GeneroMusicaSpotifyDto;
import org.example.emotiwave.application.dto.in.MusicaSimplesDto;
import org.example.emotiwave.application.dto.in.MusicasUsuarioSpotifyDto;
import org.example.emotiwave.application.mapper.MusicaMapper;
import org.example.emotiwave.application.service.GeniusLyricsService;
import org.example.emotiwave.application.service.HuggingFaceZeroShotService;
import org.example.emotiwave.domain.entities.AnaliseMusica;
import org.example.emotiwave.domain.entities.FonteMusica;
import org.example.emotiwave.domain.entities.Musica;
import org.example.emotiwave.domain.entities.Usuario;
import org.example.emotiwave.domain.entities.UsuarioMusica;
import org.example.emotiwave.infra.client.SpotifyClient;
import org.example.emotiwave.infra.repository.MusicaRepository;
import org.example.emotiwave.infra.repository.UsuarioMusicaRepository;
import org.example.emotiwave.infra.repository.UsuarioRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public class TopMusicasSpotifyService {
    private final MusicaRepository musicaRepository;
    private final MusicaMapper musicaMapper;
    private final SpotifyService spotifyService;
    private final GeniusLyricsService geniusLyricsService;
    String SPOTIFY_TOP_TRACKS_URL = "https://api.spotify.com/v1/me/top/tracks?time_range=medium_term&limit=3";
    String SPOTIFY_ARTIST_URL = "https://api.spotify.com/v1/artists/";
    private UsuarioMusicaRepository usuarioMusicaRepository;
    Instant now = Instant.now();

    public TopMusicasSpotifyService(MusicaRepository musicaRepository, MusicaMapper musicaMapper, SpotifyService spotifyService, GeniusLyricsService geniusLyricsService, UsuarioRepository usuarioRepository, SpotifyClient spotifyClient, UsuarioMusicaRepository usuarioMusicaRepository) {
        this.musicaRepository = musicaRepository;
        this.musicaMapper = musicaMapper;
        this.spotifyService = spotifyService;
        this.geniusLyricsService = geniusLyricsService;

        this.usuarioMusicaRepository = usuarioMusicaRepository;
    }

    public List<MusicaSimplesDto> buscarTopMusicasSpotify(Usuario usuario) {
        this.spotifyService.verificarExpiracaoToken(usuario);
        MusicasUsuarioSpotifyDto dtoSpotify = this.spotifyService.enviarRequisicaoSpotifyUtilsV2(usuario, this.SPOTIFY_TOP_TRACKS_URL, new ParameterizedTypeReference<MusicasUsuarioSpotifyDto>() {
        }, (Long)null);
        List<MusicaSimplesDto> topMusicas = this.converterTopMusicasParaDto(dtoSpotify, usuario);
        this.converterTopMusicasParaEntidade(topMusicas, usuario);
        return topMusicas;
    }

    private List<MusicaSimplesDto> converterTopMusicasParaDto(MusicasUsuarioSpotifyDto dtoSpotify, Usuario usuario) {
        List<MusicaSimplesDto> topMusicas = new ArrayList();
        if (dtoSpotify != null && dtoSpotify.getItems() != null) {
            for(MusicasUsuarioSpotifyDto.Track track : dtoSpotify.getItems()) {
                if (track.getArtists() != null && !track.getArtists().isEmpty()) {
                    String artistaId = ((MusicasUsuarioSpotifyDto.Track.Artist)track.getArtists().get(0)).getId();
                    GeneroMusicaSpotifyDto genero = this.spotifyService.getGeneros(track.getArtistsIds(), usuario, new ParameterizedTypeReference<GeneroMusicaSpotifyDto>() {
                    }, (Long)null, this.SPOTIFY_ARTIST_URL);
                    String generoString = genero != null && !genero.genres().isEmpty() ? (String)genero.genres().get(0) : "Desconhecido";

                    topMusicas.add(new MusicaSimplesDto(track.getName(), track.getArtistsNames(), track.getId(), artistaId, generoString,track.getAlbum().getImages().get(0).getUrl()));
                }
            }
        }

        return topMusicas;
    }

    @Transactional
    protected void converterTopMusicasParaEntidade(List<MusicaSimplesDto> topMusicas, Usuario usuario) {
        for(MusicaSimplesDto topMusicaDto : topMusicas) {
            if (this.musicaRepository.findBySpotifyTrackId(topMusicaDto.getSpotifyTrackId()) == null) {
                Musica musicaEntity = this.musicaMapper.toEntity(topMusicaDto);
                String letra = this.geniusLyricsService.buscarLyrics(topMusicaDto.getArtista(), topMusicaDto.getTitulo());
                musicaEntity.setLetra(letra);
                musicaEntity.setSpotifyTrackId(topMusicaDto.getSpotifyTrackId());
                musicaEntity.setGenero(String.valueOf(topMusicaDto.getGenero()));
                musicaRepository.save(musicaEntity);
                UsuarioMusica usuarioMusica = new UsuarioMusica();
                usuarioMusica.setMusica(musicaEntity);
                usuarioMusica.setUsuario(usuario);
                usuarioMusica.setFonte(FonteMusica.SPOTIFY);
                this.usuarioMusicaRepository.save(usuarioMusica);
            }
        }

    }
}
