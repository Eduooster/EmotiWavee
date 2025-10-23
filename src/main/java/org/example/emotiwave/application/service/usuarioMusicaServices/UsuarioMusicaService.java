package org.example.emotiwave.application.service.usuarioMusicaServices;



import java.time.LocalDate;
import org.example.emotiwave.application.dto.in.MusicaSelecionadaDto;
import org.example.emotiwave.domain.entities.Musica;
import org.example.emotiwave.domain.entities.Usuario;
import org.example.emotiwave.domain.entities.UsuarioMusica;
import org.example.emotiwave.domain.exceptions.MusicaNaoEcontrada;
import org.example.emotiwave.infra.repository.MusicaRepository;
import org.example.emotiwave.infra.repository.UsuarioMusicaRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMusicaService {
    private final UsuarioMusicaRepository usuarioMusicaRepository;
    private final MusicaRepository musicaRepository;

    public UsuarioMusicaService(UsuarioMusicaRepository usuarioMusicaRepository, MusicaRepository musicaRepository) {
        this.usuarioMusicaRepository = usuarioMusicaRepository;
        this.musicaRepository = musicaRepository;
    }

    public void desvincular(Usuario usuario, String musicaId) {
        UsuarioMusica musicaRepo = this.usuarioMusicaRepository.findByMusica_SpotifyTrackIdAndUsuarioId(musicaId, usuario.getId());
        if (musicaRepo == null) {
            throw new MusicaNaoEcontrada("Musica vinculada ao usuario nao encontrada!");
        }
    }

    public void marcarComoSelecionada(Usuario usuario, String spotifyTrackId, MusicaSelecionadaDto musicaSelecionadaDto) {
        UsuarioMusica usuarioMusicaRepo = this.usuarioMusicaRepository.findByUsuarioIdAndMusicaSpotifyTrackId(usuario.getId(), spotifyTrackId);
        Musica musica = this.musicaRepository.findBySpotifyTrackId(spotifyTrackId);
        if (usuarioMusicaRepo == null) {
            UsuarioMusica usuarioMusica = new UsuarioMusica();
            usuarioMusica.setUsuario(usuario);
            usuarioMusica.setMusica(musica);
            usuarioMusica.setOuvidaEm(musicaSelecionadaDto.ouvidaHoje() ? LocalDate.now() : null);
            this.usuarioMusicaRepository.save(usuarioMusica);
        } else {
            usuarioMusicaRepo.setOuvidaEm(musicaSelecionadaDto.ouvidaHoje() ? LocalDate.now() : null);
        }

    }
}
