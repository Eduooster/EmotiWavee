package org.example.emotiwave.application.dto.in;


public record ArtistDto(String name) {
    public ArtistDto(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }
}