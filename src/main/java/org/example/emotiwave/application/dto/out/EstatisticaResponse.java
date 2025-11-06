package org.example.emotiwave.application.dto.out;

import java.util.List;

public record EstatisticaResponse(
        String polaridade_predominante,
        Double mediaScore,
        String sentimento_predominante,
        String intensidade_predominante,
        Integer total_musicas_analisadas) {
}
