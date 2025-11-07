package org.example.emotiwave.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "T_SPOTIFY_TOKENS")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "accessToken", "refreshToken", "expiresIn"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SpotifyToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne
    private Usuario usuario;

    @Column(length = 500)
    private String accessToken;

    @Column(length = 500)
    private String refreshToken;

    private Instant expiresIn;
}
