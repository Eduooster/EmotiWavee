package org.example.emotiwave.web.controller;



import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;

import org.example.emotiwave.application.dto.out.EstatisticaResponse;
import org.example.emotiwave.application.service.AnaliseMusicaService;
import org.example.emotiwave.application.service.UsuarioService.UsuarioService;
import org.example.emotiwave.domain.entities.Usuario;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/usuarios"})
@Tag(
        name = "Usuario",
        description = "Gerenciamento de usuarios"
)
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioService usuarioService;

    private final AnaliseMusicaService analiseMusicaService;


    public UsuarioController(UsuarioService service, UsuarioService usuarioService, AnaliseMusicaService analiseMusicaService) {
        this.service = service;

        this.usuarioService = usuarioService;

        this.analiseMusicaService = analiseMusicaService;
    }


    @GetMapping("/estatisticas")
    public ResponseEntity<EstatisticaResponse> getEstatisticas(
            @AuthenticationPrincipal Usuario usuario,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        EstatisticaResponse response = analiseMusicaService.gerarEstatistica(usuario, inicio, fim);
        return ResponseEntity.ok(response);
    }


}
