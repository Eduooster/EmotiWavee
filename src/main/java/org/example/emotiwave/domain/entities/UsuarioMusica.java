package org.example.emotiwave.domain.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Generated;
import org.hibernate.proxy.HibernateProxy;

@Entity
@Table(
        name = "T_USUARIO_MUSICA"
)
public class UsuarioMusica {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    private Usuario usuario;
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    private Musica musica;
    private Preferencia selecionada;
    private LocalDate ouvidaEm;
    @Enumerated(EnumType.STRING)
    private FonteMusica fonte;

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
                UsuarioMusica that = (UsuarioMusica)o;
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
    public Musica getMusica() {
        return this.musica;
    }

    @Generated
    public Preferencia getSelecionada() {
        return this.selecionada;
    }

    @Generated
    public LocalDate getOuvidaEm() {
        return this.ouvidaEm;
    }

    @Generated
    public FonteMusica getFonte() {
        return this.fonte;
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
    public void setMusica(final Musica musica) {
        this.musica = musica;
    }

    @Generated
    public void setSelecionada(final Preferencia selecionada) {
        this.selecionada = selecionada;
    }

    @Generated
    public void setOuvidaEm(final LocalDate ouvidaEm) {
        this.ouvidaEm = ouvidaEm;
    }

    @Generated
    public void setFonte(final FonteMusica fonte) {
        this.fonte = fonte;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "UsuarioMusica(id=" + var10000 + ", selecionada=" + String.valueOf(this.getSelecionada()) + ", ouvidaEm=" + String.valueOf(this.getOuvidaEm()) + ", fonte=" + String.valueOf(this.getFonte()) + ")";
    }

    @Generated
    public UsuarioMusica() {
    }
}

