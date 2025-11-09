package org.example.emotiwave.application.service.usuarioMusicaServices;



import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.example.emotiwave.application.dto.in.MusicaSelecionadaDto;
import org.example.emotiwave.application.dto.in.MusicaSimplesDto;
import org.example.emotiwave.application.mapper.MusicaMapper;
import org.example.emotiwave.domain.entities.Musica;
import org.example.emotiwave.domain.entities.Usuario;
import org.example.emotiwave.domain.entities.UsuarioMusica;
import org.example.emotiwave.domain.exceptions.MusicaNaoEcontrada;
import org.example.emotiwave.infra.repository.MusicaRepository;
import org.example.emotiwave.infra.repository.UsuarioMusicaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMusicaService {
    private final UsuarioMusicaRepository usuarioMusicaRepository;
    private final MusicaRepository musicaRepository;
    private final MusicaMapper musicaMapper;

    public UsuarioMusicaService(UsuarioMusicaRepository usuarioMusicaRepository, MusicaRepository musicaRepository, MusicaMapper musicaMapper) {
        this.usuarioMusicaRepository = usuarioMusicaRepository;
        this.musicaRepository = musicaRepository;
        this.musicaMapper = musicaMapper;
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

    public List<MusicaSimplesDto> pegarMusicasRecentes(Usuario usuario) {
        return usuarioMusicaRepository.findMusicasOuvidasHoje(usuario.getId(),LocalDate.now()).stream().map(
                musicaMapper::toDto).collect(Collectors.toList());

    }

    public List<MusicaSimplesDto> musicasRecemOuvidas(Pageable paginacao, Usuario usuario) {
        return (List)this.usuarioMusicaRepository.findByUsuarioAndOuvidaEmAndSelecionadaTrue(usuario, LocalDate.now()).stream().map((um) -> new MusicaSimplesDto(um.getMusica().getTitulo(), um.getMusica().getArtista(), um.getMusica().getSpotifyTrackId(), um.getMusica().getArtistaId(), um.getMusica().getGenero(),um.getMusica().getUrlImg())).collect(Collectors.toList());
    }
}
