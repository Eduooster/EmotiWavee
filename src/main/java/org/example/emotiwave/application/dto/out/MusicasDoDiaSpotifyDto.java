package org.example.emotiwave.application.dto.out;


import java.util.List;
import lombok.Generated;

public class MusicasDoDiaSpotifyDto {
    private List<Item> items;

    @Generated
    public MusicasDoDiaSpotifyDto() {
    }

    @Generated
    public List<Item> getItems() {
        return this.items;
    }

    @Generated
    public void setItems(final List<Item> items) {
        this.items = items;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MusicasDoDiaSpotifyDto)) {
            return false;
        } else {
            MusicasDoDiaSpotifyDto other = (MusicasDoDiaSpotifyDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$items = this.getItems();
                Object other$items = other.getItems();
                if (this$items == null) {
                    if (other$items != null) {
                        return false;
                    }
                } else if (!this$items.equals(other$items)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof MusicasDoDiaSpotifyDto;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $items = this.getItems();
        result = result * 59 + ($items == null ? 43 : $items.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "MusicasDoDiaSpotifyDto(items=" + String.valueOf(this.getItems()) + ")";
    }

    public static class Item {
        private Track track;
        private String played_at;

        @Generated
        public Item() {
        }

        @Generated
        public Track getTrack() {
            return this.track;
        }

        @Generated
        public String getPlayed_at() {
            return this.played_at;
        }

        @Generated
        public void setTrack(final Track track) {
            this.track = track;
        }

        @Generated
        public void setPlayed_at(final String played_at) {
            this.played_at = played_at;
        }

        @Generated
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Item)) {
                return false;
            } else {
                Item other = (Item)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    Object this$track = this.getTrack();
                    Object other$track = other.getTrack();
                    if (this$track == null) {
                        if (other$track != null) {
                            return false;
                        }
                    } else if (!this$track.equals(other$track)) {
                        return false;
                    }

                    Object this$played_at = this.getPlayed_at();
                    Object other$played_at = other.getPlayed_at();
                    if (this$played_at == null) {
                        if (other$played_at != null) {
                            return false;
                        }
                    } else if (!this$played_at.equals(other$played_at)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        @Generated
        protected boolean canEqual(final Object other) {
            return other instanceof Item;
        }

        @Generated
        public int hashCode() {
            int PRIME = 59;
            int result = 1;
            Object $track = this.getTrack();
            result = result * 59 + ($track == null ? 43 : $track.hashCode());
            Object $played_at = this.getPlayed_at();
            result = result * 59 + ($played_at == null ? 43 : $played_at.hashCode());
            return result;
        }

        @Generated
        public String toString() {
            String var10000 = String.valueOf(this.getTrack());
            return "MusicasDoDiaSpotifyDto.Item(track=" + var10000 + ", played_at=" + this.getPlayed_at() + ")";
        }
    }

    public static class Track {
        private String id;
        private String name;
        private List<Artist> artists;

        @Generated
        public Track() {
        }

        @Generated
        public String getId() {
            return this.id;
        }

        @Generated
        public String getName() {
            return this.name;
        }

        @Generated
        public List<Artist> getArtists() {
            return this.artists;
        }

        @Generated
        public void setId(final String id) {
            this.id = id;
        }

        @Generated
        public void setName(final String name) {
            this.name = name;
        }

        @Generated
        public void setArtists(final List<Artist> artists) {
            this.artists = artists;
        }

        @Generated
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Track)) {
                return false;
            } else {
                Track other = (Track)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id != null) {
                            return false;
                        }
                    } else if (!this$id.equals(other$id)) {
                        return false;
                    }

                    Object this$name = this.getName();
                    Object other$name = other.getName();
                    if (this$name == null) {
                        if (other$name != null) {
                            return false;
                        }
                    } else if (!this$name.equals(other$name)) {
                        return false;
                    }

                    Object this$artists = this.getArtists();
                    Object other$artists = other.getArtists();
                    if (this$artists == null) {
                        if (other$artists != null) {
                            return false;
                        }
                    } else if (!this$artists.equals(other$artists)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        @Generated
        protected boolean canEqual(final Object other) {
            return other instanceof Track;
        }

        @Generated
        public int hashCode() {
            int PRIME = 59;
            int result = 1;
            Object $id = this.getId();
            result = result * 59 + ($id == null ? 43 : $id.hashCode());
            Object $name = this.getName();
            result = result * 59 + ($name == null ? 43 : $name.hashCode());
            Object $artists = this.getArtists();
            result = result * 59 + ($artists == null ? 43 : $artists.hashCode());
            return result;
        }

        @Generated
        public String toString() {
            String var10000 = this.getId();
            return "MusicasDoDiaSpotifyDto.Track(id=" + var10000 + ", name=" + this.getName() + ", artists=" + String.valueOf(this.getArtists()) + ")";
        }
    }

    public static class Artist {
        private String name;
        private String id;

        @Generated
        public Artist() {
        }

        @Generated
        public String getName() {
            return this.name;
        }

        @Generated
        public String getId() {
            return this.id;
        }

        @Generated
        public void setName(final String name) {
            this.name = name;
        }

        @Generated
        public void setId(final String id) {
            this.id = id;
        }

        @Generated
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Artist)) {
                return false;
            } else {
                Artist other = (Artist)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    Object this$name = this.getName();
                    Object other$name = other.getName();
                    if (this$name == null) {
                        if (other$name != null) {
                            return false;
                        }
                    } else if (!this$name.equals(other$name)) {
                        return false;
                    }

                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id != null) {
                            return false;
                        }
                    } else if (!this$id.equals(other$id)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        @Generated
        protected boolean canEqual(final Object other) {
            return other instanceof Artist;
        }

        @Generated
        public int hashCode() {
            int PRIME = 59;
            int result = 1;
            Object $name = this.getName();
            result = result * 59 + ($name == null ? 43 : $name.hashCode());
            Object $id = this.getId();
            result = result * 59 + ($id == null ? 43 : $id.hashCode());
            return result;
        }

        @Generated
        public String toString() {
            String var10000 = this.getName();
            return "MusicasDoDiaSpotifyDto.Artist(name=" + var10000 + ", id=" + this.getId() + ")";
        }
    }
}
