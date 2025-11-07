package org.example.emotiwave.application.dto.out;

import lombok.Data;
import java.util.List;

@Data
public class MusicasDoDiaSpotifyDto {
    private List<Item> items;

    @Data
    public static class Item {
        private Track track;
        private String played_at;
    }

    @Data
    public static class Track {
        private String id;
        private String name;
        private List<Artist> artists;
        private Album album;
    }

    @Data
    public static class Artist {
        private String id;
        private String name;
    }

    @Data
    public static class Album {
        private String id;
        private String name;
        private List<Image> images;
    }

    @Data
    public static class Image {
        private String url;
        private Integer height;
        private Integer width;
    }
}
