package org.example.emotiwave.application.dto.in;

import java.math.BigDecimal;

public record AnaliseEmocional(
         String emocaoPrincipal,
         String polaridade,
         String intensidade
) {
}
