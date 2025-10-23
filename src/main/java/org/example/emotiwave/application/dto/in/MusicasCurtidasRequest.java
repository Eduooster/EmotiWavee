package org.example.emotiwave.application.dto.in;



import java.util.Set;

public record MusicasCurtidasRequest(Long usuarioId, Set<Long> musicasCurtidas) {
    public MusicasCurtidasRequest(Long usuarioId, Set<Long> musicasCurtidas) {
        this.usuarioId = usuarioId;
        this.musicasCurtidas = musicasCurtidas;
    }

    public Long usuarioId() {
        return this.usuarioId;
    }

    public Set<Long> musicasCurtidas() {
        return this.musicasCurtidas;
    }
}
