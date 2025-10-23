package org.example.emotiwave.application.service.usuarioMusicaServices;



import java.time.LocalDate;
import org.example.emotiwave.application.dto.in.MusicasSelecionadasDto;
import org.example.emotiwave.domain.entities.FonteMusica;
import org.example.emotiwave.domain.entities.Musica;
import org.example.emotiwave.domain.entities.Usuario;
import org.example.emotiwave.domain.entities.UsuarioMusica;
import org.example.emotiwave.infra.repository.MusicaRepository;
import org.example.emotiwave.infra.repository.UsuarioMusicaRepository;
import org.springframework.stereotype.Service;

@Service
public class AssociarMusicasAoUsuarioService {
    private final UsuarioMusicaRepository usuarioMusicaRepository;
    private final MusicaRepository musicaRepository;

    public AssociarMusicasAoUsuarioService(UsuarioMusicaRepository usuarioMusicaRepository, MusicaRepository musicaRepository) {
        this.usuarioMusicaRepository = usuarioMusicaRepository;
        this.musicaRepository = musicaRepository;
    }

    public void processarRelacionamentos(MusicasSelecionadasDto musicasSelecionadas, Usuario usuario) {
        for(MusicasSelecionadasDto.Item item : musicasSelecionadas.getItems()) {
            Musica musica = this.musicaRepository.findBySpotifyTrackId(item.getSpotifyTrackId());
            if (musica != null) {
                UsuarioMusica relacao = this.usuarioMusicaRepository.findByMusica_SpotifyTrackIdAndUsuarioId(item.getSpotifyTrackId(), usuario.getId());
                if (relacao == null) {
                    UsuarioMusica usuarioMusica = new UsuarioMusica();
                    usuarioMusica.setMusica(musica);
                    usuarioMusica.setUsuario(usuario);
                    usuarioMusica.setOuvidaEm(item.isOuvidaHoje() ? LocalDate.now() : null);
                    usuarioMusica.setFonte(FonteMusica.MANUAL);
                    this.usuarioMusicaRepository.save(usuarioMusica);
                }
            }
        }

    }
}
