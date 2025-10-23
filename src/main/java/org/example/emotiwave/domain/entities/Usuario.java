package org.example.emotiwave.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Generated;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(
        name = "T_USUARIO"
)
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String username;
    private String password;
    private String email;
    @ElementCollection(
            fetch = FetchType.EAGER
    )
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    private Boolean deleted;
    @OneToOne(
            cascade = {CascadeType.PERSIST},
            orphanRemoval = true
    )
    @JoinColumn(
            name = "spotify_info_id"
    )
    private SpotifyToken spotifyInfo;
    @Column(
            name = "criado_em"
    )
    private LocalDate criadoEm;
    @OneToMany(
            mappedBy = "usuario",
            cascade = {CascadeType.ALL}
    )
    private List<UsuarioMusica> musicas;
    @OneToMany(
            mappedBy = "usuario",
            cascade = {CascadeType.ALL}
    )
    private List<UsuarioMusica> usuarioMusica;

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
                Usuario usuario = (Usuario)o;
                return this.getId() != null && Objects.equals(this.getId(), usuario.getId());
            }
        }
    }

    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy)this).getHibernateLazyInitializer().getPersistentClass().hashCode() : this.getClass().hashCode();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection)this.roles.stream().map((role) -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.email;
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public Set<Role> getRoles() {
        return this.roles;
    }

    @Generated
    public Boolean getDeleted() {
        return this.deleted;
    }

    @Generated
    public SpotifyToken getSpotifyInfo() {
        return this.spotifyInfo;
    }

    @Generated
    public LocalDate getCriadoEm() {
        return this.criadoEm;
    }

    @Generated
    public List<UsuarioMusica> getMusicas() {
        return this.musicas;
    }

    @Generated
    public List<UsuarioMusica> getUsuarioMusica() {
        return this.usuarioMusica;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setUsername(final String username) {
        this.username = username;
    }

    @Generated
    public void setPassword(final String password) {
        this.password = password;
    }

    @Generated
    public void setEmail(final String email) {
        this.email = email;
    }

    @Generated
    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    @Generated
    public void setDeleted(final Boolean deleted) {
        this.deleted = deleted;
    }

    @Generated
    public void setSpotifyInfo(final SpotifyToken spotifyInfo) {
        this.spotifyInfo = spotifyInfo;
    }

    @Generated
    public void setCriadoEm(final LocalDate criadoEm) {
        this.criadoEm = criadoEm;
    }

    @Generated
    public void setMusicas(final List<UsuarioMusica> musicas) {
        this.musicas = musicas;
    }

    @Generated
    public void setUsuarioMusica(final List<UsuarioMusica> usuarioMusica) {
        this.usuarioMusica = usuarioMusica;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "Usuario(id=" + var10000 + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", email=" + this.getEmail() + ", roles=" + String.valueOf(this.getRoles()) + ", deleted=" + this.getDeleted() + ", criadoEm=" + String.valueOf(this.getCriadoEm()) + ", usuarioMusica=" + String.valueOf(this.getUsuarioMusica()) + ")";
    }

    @Generated
    public Usuario() {
        this.roles = new HashSet(Set.of(Role.ROLE_USER));
        this.deleted = false;
        this.criadoEm = LocalDate.now();
        this.musicas = new ArrayList();
        this.usuarioMusica = new ArrayList();
    }
}
