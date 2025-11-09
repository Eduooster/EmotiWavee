package org.example.emotiwave.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.emotiwave.application.dto.in.DadosAuthRequestDto;
import org.example.emotiwave.application.dto.in.UsuarioCreateRequestDto;
import org.example.emotiwave.application.dto.out.DadosTokenJwtResponseDto;
import org.example.emotiwave.application.dto.out.UsuarioDetailResponseDto;
import org.example.emotiwave.application.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping({"/auth"})
@Tag(
        name = "Autenticação",
        description = "Gerenciamento de autenticações"
)
public class AutenticacaoController {
    @Autowired
    private AutenticacaoService autenticacaoService;

    public AutenticacaoController() {
    }

    @Operation(
            summary = "Autenticação do usuario no Sistema",
            description = "Endpoint responsavel por verificar autenticidade do usuario no sistema",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Autenticação bem-sucedida e token JWT retornado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = DadosTokenJwtResponseDto.class
                            )
                    )}
            ), @ApiResponse(
                    responseCode = "401",
                    description = "Credenciais inválidass"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida"
            )}
    )
    @ApiResponses
    @PostMapping
    public ResponseEntity efetuarAutenticacao(@RequestBody @Valid DadosAuthRequestDto dados) {
        String tokenJWT = this.autenticacaoService.autenticar(dados);
        return ResponseEntity.ok(new DadosTokenJwtResponseDto(tokenJWT));
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDetailResponseDto> criar(@RequestBody @Valid UsuarioCreateRequestDto dto, UriComponentsBuilder uriBuilder) {
        UsuarioDetailResponseDto cadastroNovoUsuario = autenticacaoService.registrar(dto);
        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(new Object[]{cadastroNovoUsuario.id()}).toUri();
        return ResponseEntity.created(uri).body(cadastroNovoUsuario);
    }
}
