package org.example.emotiwave.application.dto.out;



public record UsuarioListResponseDto(String email, String username) {
    public UsuarioListResponseDto(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public String email() {
        return this.email;
    }

    public String username() {
        return this.username;
    }
}
