package org.example.emotiwave.application.service;


import jakarta.transaction.Transactional;
import org.example.emotiwave.application.dto.in.AnaliseEmocional;
import org.example.emotiwave.domain.entities.AnaliseMusica;
import org.example.emotiwave.domain.entities.Musica;
import org.example.emotiwave.domain.entities.Usuario;
import org.example.emotiwave.infra.repository.AnaliseMusicaRepository;
import org.example.emotiwave.infra.repository.MusicaRepository;
import org.example.emotiwave.infra.repository.UsuarioMusicaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class AnaliseMusicaService {

    private final UsuarioMusicaRepository usuarioMusicaRepository;
    private final HuggingFaceZeroShotService huggingFaceZeroShotService;
    private final MusicaRepository musicaRepository;

    private static final Set<String> SENTIMENTOS_POSITIVOS = Set.of(
            "happy",
            "love",
            "romantic",
            "hopefull",
            "energetic",
            "calm"
    );

    private static final Set<String> SENTIMENTOS_NEGATIVOS = Set.of(
            "sad",
            "heartbreak",
            "lonely",
            "angry",
            "nostalgic"
    );
    private final AnaliseMusicaRepository analiseMusicaRepository;

    public AnaliseMusicaService(UsuarioMusicaRepository usuarioMusicaRepository, HuggingFaceZeroShotService huggingFaceZeroShotService, MusicaRepository musicaRepository,
                                AnaliseMusicaRepository analiseMusicaRepository) {
        this.usuarioMusicaRepository = usuarioMusicaRepository;
        this.huggingFaceZeroShotService = huggingFaceZeroShotService;
        this.musicaRepository = musicaRepository;
        this.analiseMusicaRepository = analiseMusicaRepository;
    }

    @Transactional
    public void analisarMusicas(Usuario usuario) {
        List<Musica> musicas = usuarioMusicaRepository.findMusicasByUsuarioId(usuario.getId());

        musicas.forEach(musica -> {
            if (musica.getAnalise() == null) {
                try {
                    AnaliseMusica analise = huggingFaceZeroShotService.analisarMusica(musica);

                    interpretarAnalise(analise);

                    analiseMusicaRepository.save(analise);

                    musica.setAnalise(analise);


                    musicaRepository.save(musica);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void interpretarAnalise(AnaliseMusica analise) {

        if (SENTIMENTOS_POSITIVOS.contains(analise.getLabel())) {
            analise.setPolaridade("POSITIVO");
        } else if (SENTIMENTOS_NEGATIVOS.contains(analise.getLabel())) {
            analise.setPolaridade("NEGATIVO");
        } else {
            analise.setPolaridade("NEUTRO");
        }

        if (analise.getScore().compareTo(new BigDecimal("0.60")) >= 0) {
            analise.setIntensidade("ALTA");
        } else if (analise.getScore().compareTo(new BigDecimal("0.30")) >= 0) {
            analise.setIntensidade("MEDIA");
        } else {
            analise.setIntensidade("BAIXA");
        }
    }

}
