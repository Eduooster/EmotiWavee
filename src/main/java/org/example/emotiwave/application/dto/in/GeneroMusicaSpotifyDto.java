package org.example.emotiwave.application.dto.in;

import java.util.List;

public record GeneroMusicaSpotifyDto(List<String> genres) {
    public GeneroMusicaSpotifyDto(List<String> genres) {
        this.genres = genres;
    }

    public List<String> genres() {
        return this.genres;
    }
}
