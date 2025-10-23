package org.example.emotiwave.application.service.UsuarioService;



import org.example.emotiwave.infra.repository.AnaliseMusicaRepository;
import org.example.emotiwave.infra.repository.MusicaRepository;
import org.example.emotiwave.infra.repository.UsuarioMusicaRepository;
import org.springframework.stereotype.Service;

@Service
public class AnaliseHumorService {
    private final UsuarioMusicaRepository usuarioMusicaRepository;

    public AnaliseHumorService(UsuarioMusicaRepository usuarioMusicaRepository, MusicaRepository musicaRepository, AnaliseMusicaRepository analiseMusicaRepository) {
        this.usuarioMusicaRepository = usuarioMusicaRepository;
    }
}
