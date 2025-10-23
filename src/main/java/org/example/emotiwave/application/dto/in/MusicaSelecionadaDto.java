package org.example.emotiwave.application.dto.in;

public record MusicaSelecionadaDto(boolean ouvidaHoje) {
    public MusicaSelecionadaDto(boolean ouvidaHoje) {
        this.ouvidaHoje = ouvidaHoje;
    }

    public boolean ouvidaHoje() {
        return this.ouvidaHoje;
    }
}