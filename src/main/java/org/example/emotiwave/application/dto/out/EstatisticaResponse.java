package org.example.emotiwave.application.dto.out;

import java.math.BigDecimal;
import java.util.List;

public record EstatisticaResponse(
        String polaridade_predominante,
        Double mediaScore,
        String sentimento_predominante,
        String intensidade_predominante,
        Integer total_musicas_analisadas,
        PolaridadePercentual polaridade
) {

    public record PolaridadePercentual(
            BigDecimal positivo,
            BigDecimal negativo,
            BigDecimal neutro
    ){


    }
}
