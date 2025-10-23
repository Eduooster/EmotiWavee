package org.example.emotiwave.application.dto.in;


import java.util.List;
import java.util.stream.Collectors;
import lombok.Generated;

public class MusicasUsuarioSpotifyDto {
    private List<Track> items;

    @Generated
    public MusicasUsuarioSpotifyDto() {
    }

    @Generated
    public List<Track> getItems() {
        return this.items;
    }

    @Generated
    public void setItems(final List<Track> items) {
        this.items = items;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MusicasUsuarioSpotifyDto)) {
            return false;
        } else {
            MusicasUsuarioSpotifyDto other = (MusicasUsuarioSpotifyDto)o;
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
        return other instanceof MusicasUsuarioSpotifyDto;
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
        return "MusicasUsuarioSpotifyDto(items=" + String.valueOf(this.getItems()) + ")";
    }

    public static class Track {
        private String name;
        private List<Artist> artists;
        private String id;
        private String genero;

        public String getArtistsNames() {
            return this.artists != null && !this.artists.isEmpty() ? (String)this.artists.stream().map(Artist::getName).collect(Collectors.joining(", ")) : "Desconhecido";
        }

        public String getArtistsIds() {
            return this.artists != null && !this.artists.isEmpty() ? (String)this.artists.stream().map(Artist::getId).collect(Collectors.joining(",")) : "";
        }

        @Generated
        public Track() {
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
        public String getId() {
            return this.id;
        }

        @Generated
        public String getGenero() {
            return this.genero;
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
        public void setId(final String id) {
            this.id = id;
        }

        @Generated
        public void setGenero(final String genero) {
            this.genero = genero;
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

                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id != null) {
                            return false;
                        }
                    } else if (!this$id.equals(other$id)) {
                        return false;
                    }

                    Object this$genero = this.getGenero();
                    Object other$genero = other.getGenero();
                    if (this$genero == null) {
                        if (other$genero != null) {
                            return false;
                        }
                    } else if (!this$genero.equals(other$genero)) {
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
            Object $name = this.getName();
            result = result * 59 + ($name == null ? 43 : $name.hashCode());
            Object $artists = this.getArtists();
            result = result * 59 + ($artists == null ? 43 : $artists.hashCode());
            Object $id = this.getId();
            result = result * 59 + ($id == null ? 43 : $id.hashCode());
            Object $genero = this.getGenero();
            result = result * 59 + ($genero == null ? 43 : $genero.hashCode());
            return result;
        }

        @Generated
        public String toString() {
            String var10000 = this.getName();
            return "MusicasUsuarioSpotifyDto.Track(name=" + var10000 + ", artists=" + String.valueOf(this.getArtists()) + ", id=" + this.getId() + ", genero=" + this.getGenero() + ")";
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
                return "MusicasUsuarioSpotifyDto.Track.Artist(name=" + var10000 + ", id=" + this.getId() + ")";
            }
        }
    }
}
