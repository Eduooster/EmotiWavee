//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.emotiwave.application.dto.in;

import java.util.List;
import lombok.Generated;

public class MusicasSelecionadasDto {
    private List<Item> items;

    @Generated
    public MusicasSelecionadasDto() {
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
        } else if (!(o instanceof MusicasSelecionadasDto)) {
            return false;
        } else {
            MusicasSelecionadasDto other = (MusicasSelecionadasDto)o;
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
        return other instanceof MusicasSelecionadasDto;
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
        return "MusicasSelecionadasDto(items=" + String.valueOf(this.getItems()) + ")";
    }

    public static class Item {
        private String spotifyTrackId;
        private boolean ouvidaHoje;
        private boolean selecionada;

        @Generated
        public Item() {
        }

        @Generated
        public String getSpotifyTrackId() {
            return this.spotifyTrackId;
        }

        @Generated
        public boolean isOuvidaHoje() {
            return this.ouvidaHoje;
        }

        @Generated
        public boolean isSelecionada() {
            return this.selecionada;
        }

        @Generated
        public void setSpotifyTrackId(final String spotifyTrackId) {
            this.spotifyTrackId = spotifyTrackId;
        }

        @Generated
        public void setOuvidaHoje(final boolean ouvidaHoje) {
            this.ouvidaHoje = ouvidaHoje;
        }

        @Generated
        public void setSelecionada(final boolean selecionada) {
            this.selecionada = selecionada;
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
                } else if (this.isOuvidaHoje() != other.isOuvidaHoje()) {
                    return false;
                } else if (this.isSelecionada() != other.isSelecionada()) {
                    return false;
                } else {
                    Object this$spotifyTrackId = this.getSpotifyTrackId();
                    Object other$spotifyTrackId = other.getSpotifyTrackId();
                    if (this$spotifyTrackId == null) {
                        if (other$spotifyTrackId != null) {
                            return false;
                        }
                    } else if (!this$spotifyTrackId.equals(other$spotifyTrackId)) {
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
            result = result * 59 + (this.isOuvidaHoje() ? 79 : 97);
            result = result * 59 + (this.isSelecionada() ? 79 : 97);
            Object $spotifyTrackId = this.getSpotifyTrackId();
            result = result * 59 + ($spotifyTrackId == null ? 43 : $spotifyTrackId.hashCode());
            return result;
        }

        @Generated
        public String toString() {
            String var10000 = this.getSpotifyTrackId();
            return "MusicasSelecionadasDto.Item(spotifyTrackId=" + var10000 + ", ouvidaHoje=" + this.isOuvidaHoje() + ", selecionada=" + this.isSelecionada() + ")";
        }
    }
}
