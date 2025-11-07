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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "T_MUSICA")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "titulo", "artista", "spotifyTrackId", "genero", "deleted", "analise"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String titulo;
    private String artista;
    private String spotifyTrackId;

    @Lob
    private String letra;

    private String artistaId;
    private String genero;
    private boolean deleted;
    private String urlImg;

    @OneToMany(mappedBy = "musica")
    private List<UsuarioMusica> analises = new ArrayList<>();

    @OneToOne(mappedBy = "musica")
    private AnaliseMusica analise;
}
