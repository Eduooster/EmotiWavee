package org.example.emotiwave.domain.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.Objects;
import lombok.Generated;
import org.hibernate.proxy.HibernateProxy;

@Entity
@Table(
        name = "T_SPOTIFY_TOKENS"
)
public class SpotifyToken {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @OneToOne
    private Usuario usuario;
    @Column(
            length = 500
    )
    private String accessToken;
    @Column(
            length = 500
    )
    private String refreshToken;
    private Instant expiresIn;

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
                SpotifyToken that = (SpotifyToken)o;
                return this.getId() != null && Objects.equals(this.getId(), that.getId());
            }
        }
    }

    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy)this).getHibernateLazyInitializer().getPersistentClass().hashCode() : this.getClass().hashCode();
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Usuario getUsuario() {
        return this.usuario;
    }

    @Generated
    public String getAccessToken() {
        return this.accessToken;
    }

    @Generated
    public String getRefreshToken() {
        return this.refreshToken;
    }

    @Generated
    public Instant getExpiresIn() {
        return this.expiresIn;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setUsuario(final Usuario usuario) {
        this.usuario = usuario;
    }

    @Generated
    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    @Generated
    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Generated
    public void setExpiresIn(final Instant expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "SpotifyToken(id=" + var10000 + ", accessToken=" + this.getAccessToken() + ", refreshToken=" + this.getRefreshToken() + ", expiresIn=" + String.valueOf(this.getExpiresIn()) + ")";
    }

    @Generated
    public SpotifyToken() {
    }
}
