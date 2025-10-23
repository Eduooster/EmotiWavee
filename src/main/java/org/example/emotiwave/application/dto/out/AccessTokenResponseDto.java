package org.example.emotiwave.application.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccessTokenResponseDto(String accessToken, String refreshToken, Integer expiresIn, String tokenType, String scope) {
    public AccessTokenResponseDto(@JsonProperty("access_token") String accessToken, @JsonProperty("refresh_token") String refreshToken, @JsonProperty("expires_in") Integer expiresIn, @JsonProperty("token_type") String tokenType, @JsonProperty("scope") String scope) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.tokenType = tokenType;
        this.scope = scope;
    }

    @JsonProperty("access_token")
    public String accessToken() {
        return this.accessToken;
    }

    @JsonProperty("refresh_token")
    public String refreshToken() {
        return this.refreshToken;
    }

    @JsonProperty("expires_in")
    public Integer expiresIn() {
        return this.expiresIn;
    }

    @JsonProperty("token_type")
    public String tokenType() {
        return this.tokenType;
    }

    @JsonProperty("scope")
    public String scope() {
        return this.scope;
    }
}
