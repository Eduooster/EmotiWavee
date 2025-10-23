package org.example.emotiwave.application.dto.out;


import java.util.List;

public record MusicasMaisOuvidasResponseDto(List<MusicaResponseDto> musicas) {
    public MusicasMaisOuvidasResponseDto(List<MusicaResponseDto> musicas) {
        this.musicas = musicas;
    }

    public List<MusicaResponseDto> musicas() {
        return this.musicas;
    }

    public static record MusicaResponseDto(Long id, String artista, String titulo, Integer qtdOuvintes) {
        public MusicaResponseDto(Long id, String artista, String titulo, Integer qtdOuvintes) {
            this.id = id;
            this.artista = artista;
            this.titulo = titulo;
            this.qtdOuvintes = qtdOuvintes;
        }

        public Long id() {
            return this.id;
        }

        public String artista() {
            return this.artista;
        }

        public String titulo() {
            return this.titulo;
        }

        public Integer qtdOuvintes() {
            return this.qtdOuvintes;
        }
    }
}
