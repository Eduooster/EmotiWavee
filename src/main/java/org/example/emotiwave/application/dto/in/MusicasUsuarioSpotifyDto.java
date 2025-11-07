package org.example.emotiwave.application.dto.in;

import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MusicasUsuarioSpotifyDto {
    private List<Track> items;

    @Data
    public static class Track {
        private String name;
        private List<Artist> artists;
        private String id;
        private String genero;
        private Album album;

        @Data
        public static class Album {
            private List<Image> images;
        }

        @Data
        public static class Image {
            private String url;
        }


        public String getArtistsNames() {
            return (artists != null && !artists.isEmpty())
                    ? artists.stream().map(Artist::getName).collect(Collectors.joining(", "))
                    : "Desconhecido";
        }

        // Retorna ids dos artistas concatenados
        public String getArtistsIds() {
            return (artists != null && !artists.isEmpty())
                    ? artists.stream().map(Artist::getId).collect(Collectors.joining(","))
                    : "";
        }

        @Data
        public static class Artist {
            private String name;
            private String id;
        }
    }
}
