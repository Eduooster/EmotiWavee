package org.example.emotiwave.domain.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Generated;
import org.hibernate.proxy.HibernateProxy;

@Entity
@Table(
        name = "T_MUSICA"
)
public class Musica {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String titulo;
    private String artista;
    private String spotifyTrackId;
    @Lob
    private String letra;
    private String artistaId;
    private String genero;
    private boolean deleted;
    @OneToMany(
            mappedBy = "musica"
    )
    private List<UsuarioMusica> analises = new ArrayList();
    @OneToOne(
            mappedBy = "musica"
    )
    private AnaliseMusica analise;

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else {
            Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy)o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
            Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy)this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
            if (thisEffectiveClass != oEffectiveClass) {
                return false;
            } else {
                Musica musica = (Musica)o;
                return this.getId() != null && Objects.equals(this.getId(), musica.getId());
            }
        }
    }

    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy)this).getHibernateLazyInitializer().getPersistentClass().hashCode() : this.getClass().hashCode();
    }

    public Musica orElseThrow(Object asd) {
        return null;
    }

    @Generated
    public Long getId() {
        return this.id;
    }

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
    public String getLetra() {
        return this.letra;
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
    public boolean isDeleted() {
        return this.deleted;
    }

    @Generated
    public List<UsuarioMusica> getAnalises() {
        return this.analises;
    }

    @Generated
    public AnaliseMusica getAnalise() {
        return this.analise;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
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
    public void setLetra(final String letra) {
        this.letra = letra;
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
    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }

    @Generated
    public void setAnalises(final List<UsuarioMusica> analises) {
        this.analises = analises;
    }

    @Generated
    public void setAnalise(final AnaliseMusica analise) {
        this.analise = analise;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "Musica(id=" + var10000 + ", titulo=" + this.getTitulo() + ", artista=" + this.getArtista() + ", spotifyTrackId=" + this.getSpotifyTrackId() + ", letra=" + this.getLetra() + ", artistaId=" + this.getArtistaId() + ", genero=" + this.getGenero() + ", deleted=" + this.isDeleted() + ", analise=" + String.valueOf(this.getAnalise()) + ")";
    }

    @Generated
    public Musica() {
    }
}

