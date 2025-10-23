package org.example.emotiwave.application.service.spotifyServices;



import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import org.example.emotiwave.application.dto.in.GeneroMusicaSpotifyDto;
import org.example.emotiwave.application.dto.in.MusicaSimplesDto;
import org.example.emotiwave.application.dto.in.MusicasUsuarioSpotifyDto;
import org.example.emotiwave.application.dto.out.AccessTokenResponseDto;
import org.example.emotiwave.application.dto.out.MusicasDoDiaSpotifyDto;
import org.example.emotiwave.domain.entities.Usuario;
import org.example.emotiwave.domain.exceptions.FalhaAoPegarTokenAcess;
import org.example.emotiwave.infra.client.SpotifyClient;
import org.example.emotiwave.infra.repository.UsuarioRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Component
public class SpotifyService {
    private final SpotifyClient spotifyClient;
    private final UsuarioRepository usuarioRepository;
    private final SpotifyTokenService usuarioSpotifyTokenService;
    Instant now = Instant.now();

    public SpotifyService(SpotifyClient spotifyClient, UsuarioRepository usuarioRepository, SpotifyTokenService usuarioSpotifyTokenService) {
        this.spotifyClient = spotifyClient;
        this.usuarioRepository = usuarioRepository;
        this.usuarioSpotifyTokenService = usuarioSpotifyTokenService;
    }

    public String solicitarAutorizacao(String authHeader) {
        return this.spotifyClient.construirAutorizacao(authHeader);
    }

    public void exchangeCodeForTokens(String code, Usuario usuario) {
        try {
            AccessTokenResponseDto acessTokenResponse = this.spotifyClient.exchangeCodeForTokens(code);
            this.usuarioSpotifyTokenService.associarTokenAoUsuario(acessTokenResponse, usuario);
        } catch (FalhaAoPegarTokenAcess ex) {
            throw new FalhaAoPegarTokenAcess(ex.getMessage());
        }
    }

    public void verificarExpiracaoToken(Usuario usuario) {
        if (usuario.getSpotifyInfo().getExpiresIn().isBefore(this.now.plusSeconds(300L))) {
            AccessTokenResponseDto newAccessToken = this.spotifyClient.refreshAccessToken(usuario);
            usuario.getSpotifyInfo().setAccessToken(newAccessToken.accessToken());
            usuario.getSpotifyInfo().setExpiresIn(Instant.ofEpochSecond((long)newAccessToken.expiresIn()));
            this.usuarioRepository.save(usuario);
        }

    }

    public MusicasDoDiaSpotifyDto enviarRequisicaoSpotifyUtils(Usuario usuario, String url, ParameterizedTypeReference<MusicasDoDiaSpotifyDto> parameterizedTypeReference, Long after) {
        return (MusicasDoDiaSpotifyDto)this.spotifyClient.enviarRequisicaoSpotifyUtils(usuario, url, parameterizedTypeReference, after);
    }

    public MusicasUsuarioSpotifyDto enviarRequisicaoSpotifyUtilsV2(Usuario usuario, String url, ParameterizedTypeReference<MusicasUsuarioSpotifyDto> parameterizedTypeReference, Long after) {
        return (MusicasUsuarioSpotifyDto)this.spotifyClient.enviarRequisicaoSpotifyUtils(usuario, url, parameterizedTypeReference, after);
    }

    public GeneroMusicaSpotifyDto getGeneros(String artistaId, Usuario usuario, ParameterizedTypeReference<GeneroMusicaSpotifyDto> parameterizedTypeReference, Long after, String url) {
        String urlMontada = url + "/" + artistaId;
        return (GeneroMusicaSpotifyDto)this.spotifyClient.enviarRequisicaoSpotifyUtils(usuario, urlMontada, parameterizedTypeReference, after);
    }

    public MusicaSimplesDto buscarMusica(String artista, String titulo, Usuario usuario, ParameterizedTypeReference<MusicaSimplesDto> parameterizedTypeReference, String url) {
        String query = "artist:" + artista + " track:" + titulo;
        String urlMontada = "https://api.spotify.com/v1/search?q=" + URLEncoder.encode(query, StandardCharsets.UTF_8) + "&type=track&limit=1";
        return (MusicaSimplesDto)this.spotifyClient.enviarRequisicaoSpotifyUtils(usuario, urlMontada, parameterizedTypeReference, (Long)null);
    }
}
