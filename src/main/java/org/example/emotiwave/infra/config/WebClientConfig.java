package org.example.emotiwave.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    public WebClientConfig() {
    }

    @Bean
    public WebClient spotifyWebClient() {
        return WebClient.builder().baseUrl("https://api.spotify.com/v1").defaultHeader("Content-Type", new String[]{"application/json"}).build();
    }
}
