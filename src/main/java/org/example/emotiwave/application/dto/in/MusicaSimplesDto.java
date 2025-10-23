package org.example.emotiwave.application.dto.in;
import lombok.Generated;
public class MusicaSimplesDto {
    private String titulo;
    private String artista;
    private String spotifyTrackId;
    private String artistaId;
    private String genero;

    @Generated
    public String getTitulo() {
        return this.titulo;
    }

    @Generated
    public String getArtista() {
        return this.artista;
    }

    @Generated
    public String getSpotifyTrackId() {
        return this.spotifyTrackId;
    }

    @Generated
    public String getArtistaId() {
        return this.artistaId;
    }

    @Generated
    public String getGenero() {
        return this.genero;
    }

    @Generated
    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    @Generated
    public void setArtista(final String artista) {
        this.artista = artista;
    }

    @Generated
    public void setSpotifyTrackId(final String spotifyTrackId) {
        this.spotifyTrackId = spotifyTrackId;
    }

    @Generated
    public void setArtistaId(final String artistaId) {
        this.artistaId = artistaId;
    }

    @Generated
    public void setGenero(final String genero) {
        this.genero = genero;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MusicaSimplesDto)) {
            return false;
        } else {
            MusicaSimplesDto other = (MusicaSimplesDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$titulo = this.getTitulo();
                Object other$titulo = other.getTitulo();
                if (this$titulo == null) {
                    if (other$titulo != null) {
                        return false;
                    }
                } else if (!this$titulo.equals(other$titulo)) {
                    return false;
                }

                Object this$artista = this.getArtista();
                Object other$artista = other.getArtista();
                if (this$artista == null) {
                    if (other$artista != null) {
                        return false;
                    }
                } else if (!this$artista.equals(other$artista)) {
                    return false;
                }

                Object this$spotifyTrackId = this.getSpotifyTrackId();
                Object other$spotifyTrackId = other.getSpotifyTrackId();
                if (this$spotifyTrackId == null) {
                    if (other$spotifyTrackId != null) {
                        return false;
                    }
                } else if (!this$spotifyTrackId.equals(other$spotifyTrackId)) {
                    return false;
                }

                Object this$artistaId = this.getArtistaId();
                Object other$artistaId = other.getArtistaId();
                if (this$artistaId == null) {
                    if (other$artistaId != null) {
                        return false;
                    }
                } else if (!this$artistaId.equals(other$artistaId)) {
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
        return other instanceof MusicaSimplesDto;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $titulo = this.getTitulo();
        result = result * 59 + ($titulo == null ? 43 : $titulo.hashCode());
        Object $artista = this.getArtista();
        result = result * 59 + ($artista == null ? 43 : $artista.hashCode());
        Object $spotifyTrackId = this.getSpotifyTrackId();
        result = result * 59 + ($spotifyTrackId == null ? 43 : $spotifyTrackId.hashCode());
        Object $artistaId = this.getArtistaId();
        result = result * 59 + ($artistaId == null ? 43 : $artistaId.hashCode());
        Object $genero = this.getGenero();
        result = result * 59 + ($genero == null ? 43 : $genero.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getTitulo();
        return "MusicaSimplesDto(titulo=" + var10000 + ", artista=" + this.getArtista() + ", spotifyTrackId=" + this.getSpotifyTrackId() + ", artistaId=" + this.getArtistaId() + ", genero=" + this.getGenero() + ")";
    }

    @Generated
    public MusicaSimplesDto(final String titulo, final String artista, final String spotifyTrackId, final String artistaId, final String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.spotifyTrackId = spotifyTrackId;
        this.artistaId = artistaId;
        this.genero = genero;
    }
}
