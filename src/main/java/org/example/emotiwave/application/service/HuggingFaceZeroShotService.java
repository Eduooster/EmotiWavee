package org.example.emotiwave.application.service;


import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import org.example.emotiwave.domain.entities.AnaliseMusica;
import org.example.emotiwave.domain.entities.Musica;
import org.example.emotiwave.domain.exceptions.HuggingFaceException;
import org.example.emotiwave.infra.client.HuggingFaceZeroShotClient;
import org.example.emotiwave.infra.repository.AnaliseMusicaRepository;
import org.springframework.stereotype.Service;

@Service
public class HuggingFaceZeroShotService {
    private final AnaliseMusicaRepository analiseMusicaRepository;
    private final HuggingFaceZeroShotClient huggingFaceZeroShotClient;

    public HuggingFaceZeroShotService(AnaliseMusicaRepository analiseMusicaRepository, HuggingFaceZeroShotClient huggingFaceZeroShotClient) {
        this.analiseMusicaRepository = analiseMusicaRepository;
        this.huggingFaceZeroShotClient = huggingFaceZeroShotClient;
    }

    public AnaliseMusica analisarMusica(Musica musica) throws IOException, HuggingFaceException {

        ArrayList<Serializable> responseParseado = this.huggingFaceZeroShotClient.obterAnalise(musica);


        if (responseParseado == null || responseParseado.size() < 2) {
            throw new HuggingFaceException("Resposta inválida do HuggingFace para a música"
                    + ", resposta: " + responseParseado);
        }
        Object labelObj = responseParseado.get(0);
        Object scoreObj = responseParseado.get(1);

        String label = (labelObj instanceof String) ? (String) labelObj : labelObj.toString();
        BigDecimal score;
        try {
            score = (scoreObj instanceof BigDecimal) ? (BigDecimal) scoreObj : new BigDecimal(scoreObj.toString());
        } catch (NumberFormatException e) {
            throw new HuggingFaceException("Score inválido recebido do HuggingFace: " + scoreObj);
        }


        AnaliseMusica analise = new AnaliseMusica();
        analise.setLabel(label);
        analise.setScore(score);
        analise.setAnalisado_em(LocalDate.now(ZoneId.of("America/Sao_Paulo")));
        analise.setMusica(musica);




        return analise;
    }

}
