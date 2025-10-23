package org.example.emotiwave.application.dto.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioCreateRequestDto(@NotBlank String username, @NotBlank String password, @NotBlank @Email String email) {
    public UsuarioCreateRequestDto(@NotBlank String username, @NotBlank String password, @NotBlank @Email String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public @NotBlank String username() {
        return this.username;
    }

    public @NotBlank String password() {
        return this.password;
    }

    public @NotBlank @Email String email() {
        return this.email;
    }
}
