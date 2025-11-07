package org.example.emotiwave.application.dto.in;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MusicaSimplesDto {

    private String titulo;
    private String artista;
    private String spotifyTrackId;
    private String artistaId;
    private String genero;
    private String urlImg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal score;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String polaridade;


    public MusicaSimplesDto(String titulo, String artista, String spotifyTrackId, String artistaId, String genero,String urlImg) {
        this.titulo = titulo;
        this.artista = artista;
        this.spotifyTrackId = spotifyTrackId;
        this.artistaId = artistaId;
        this.genero = genero;
        this.urlImg = urlImg;

    }

    // Construtor vazio necessário para desserialização JSON
    public MusicaSimplesDto() {}
}
