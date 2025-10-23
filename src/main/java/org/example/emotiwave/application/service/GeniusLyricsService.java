package org.example.emotiwave.application.service;

import java.io.IOException;
import org.example.emotiwave.domain.exceptions.GeniusLyricsNaoEncontrada;
import org.example.emotiwave.infra.client.GeniusLyricsClient;
import org.springframework.stereotype.Service;

@Service
public class GeniusLyricsService {
    private final GeniusLyricsClient geniusLyricsClient;

    public GeniusLyricsService(GeniusLyricsClient geniusLyricsClient) {
        this.geniusLyricsClient = geniusLyricsClient;
    }

    public String buscarLyrics(String artist, String title) {
        try {
            String letra = this.geniusLyricsClient.fetchLyrics(artist, title);
            return letra != null && !letra.isBlank() ? letra.trim() : "Letra não disponível";
        } catch (IOException e) {
            throw new GeniusLyricsNaoEncontrada(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
