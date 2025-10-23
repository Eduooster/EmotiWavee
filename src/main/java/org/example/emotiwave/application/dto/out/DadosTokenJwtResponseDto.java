package org.example.emotiwave.application.dto.out;

public record DadosTokenJwtResponseDto(String tokenJWT) {
    public DadosTokenJwtResponseDto(String tokenJWT) {
        this.tokenJWT = tokenJWT;
    }

    public String tokenJWT() {
        return this.tokenJWT;
    }
}
