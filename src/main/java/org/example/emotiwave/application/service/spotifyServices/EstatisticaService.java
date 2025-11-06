package org.example.emotiwave.application.service.spotifyServices;

import org.example.emotiwave.application.dto.out.EstatisticaResponse;
import org.example.emotiwave.domain.entities.Usuario;
import org.example.emotiwave.infra.repository.AnaliseMusicaRepository;
import org.example.emotiwave.infra.repository.UsuarioMusicaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EstatisticaService {

    private final UsuarioMusicaRepository usuarioMusicaRepository;
    private final AnaliseMusicaRepository analiseMusicaRepository;

    public EstatisticaService(UsuarioMusicaRepository usuarioMusicaRepository, AnaliseMusicaRepository analiseMusicaRepository) {
        this.usuarioMusicaRepository = usuarioMusicaRepository;
        this.analiseMusicaRepository = analiseMusicaRepository;
    }

    public EstatisticaResponse gerarEstatistica(Usuario usuario, LocalDate inicio, LocalDate fim) {
        return analiseMusicaRepository.gerarEstatisticas(usuario.getId(),inicio,fim);

    }
}
