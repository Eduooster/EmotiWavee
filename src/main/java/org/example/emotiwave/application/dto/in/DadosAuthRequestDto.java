package org.example.emotiwave.application.dto.in;

import java.io.Serializable;

public record DadosAuthRequestDto(String email, String password) implements Serializable {
    public DadosAuthRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String email() {
        return this.email;
    }

    public String password() {
        return this.password;
    }
}

