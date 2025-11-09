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

import lombok.*;

@Entity
@Table(name = "T_USUARIO_MUSICA")
@Data
@NoArgsConstructor
@ToString(of = {"id", "selecionada", "ouvidaEm", "fonte"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioMusica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Musica musica;

    private boolean selecionada;

    private LocalDate ouvidaEm;

    @Enumerated(EnumType.STRING)
    private FonteMusica fonte;
}
