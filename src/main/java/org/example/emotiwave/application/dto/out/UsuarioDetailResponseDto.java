package org.example.emotiwave.application.dto.out;


import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;
import org.example.emotiwave.domain.entities.Role;

public record UsuarioDetailResponseDto(Long id, @NotBlank String username, @NotBlank String email, @NotBlank LocalDate criadoEm, @NotBlank Set<Role> roles) {
    public UsuarioDetailResponseDto(Long id, @NotBlank String username, @NotBlank String email, @NotBlank LocalDate criadoEm, @NotBlank Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.criadoEm = criadoEm;
        this.roles = roles;
    }

    public Long id() {
        return this.id;
    }

    public @NotBlank String username() {
        return this.username;
    }

    public @NotBlank String email() {
        return this.email;
    }

    public @NotBlank LocalDate criadoEm() {
        return this.criadoEm;
    }

    public @NotBlank Set<Role> roles() {
        return this.roles;
    }
}
