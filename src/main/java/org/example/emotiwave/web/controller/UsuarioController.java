package org.example.emotiwave.web.controller;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

import org.example.emotiwave.application.dto.in.UsuarioCreateRequestDto;
import org.example.emotiwave.application.dto.out.EstatisticaResponse;
import org.example.emotiwave.application.dto.out.UsuarioDetailResponseDto;
import org.example.emotiwave.application.service.UsuarioService.AnaliseHumorService;
import org.example.emotiwave.application.service.UsuarioService.HumorSemanalService;
import org.example.emotiwave.application.service.UsuarioService.RecomendacaoMusicaHumorService;
import org.example.emotiwave.application.service.UsuarioService.UsuarioService;
import org.example.emotiwave.application.service.spotifyServices.EstatisticaService;
import org.example.emotiwave.domain.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping({"/usuarios"})
@Tag(
        name = "Usuario",
        description = "Gerenciamento de usuarios"
)
public class UsuarioController {

    private final UsuarioService service;
    private final AnaliseHumorService analiseService;
    private final RecomendacaoMusicaHumorService recomendacaoService;
    private final HumorSemanalService humorService;
    private final UsuarioService usuarioService;
    private final EstatisticaService estatisticaService;


    public UsuarioController(UsuarioService service, AnaliseHumorService analiseService, RecomendacaoMusicaHumorService recomendacaoService, HumorSemanalService humorService, UsuarioService usuarioService, EstatisticaService estatisticaService) {
        this.service = service;
        this.analiseService = analiseService;
        this.recomendacaoService = recomendacaoService;
        this.humorService = humorService;
        this.usuarioService = usuarioService;
        this.estatisticaService = estatisticaService;
    }


    @GetMapping("/estatisticas")
    public ResponseEntity<EstatisticaResponse> getEstatisticas(
            @AuthenticationPrincipal Usuario usuario,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        EstatisticaResponse response = estatisticaService.gerarEstatistica(usuario, inicio, fim);
        return ResponseEntity.ok(response);
    }



//    @Operation(
//            summary = "Listar usuários",
//            description = "Retorna uma lista paginada de usuários do sistema."
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Lista de usuários retornada com sucesso"
//    )
//    @GetMapping
//    public ResponseEntity<Page<UsuarioDetailResponseDto>> listar(@PageableDefault(size = 10,sort = {"username"}) Pageable paginacao) {
//        Page<UsuarioDetailResponseDto> page = this.usuarioService.listar(paginacao);
//        return ResponseEntity.ok(page);
//    }
//
//    @Operation(
//            summary = "Excluir usuário",
//            description = "Exclui um usuário existente pelo ID e retorna os dados do usuário excluído."
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Usuário excluído com sucesso"
//    )
//    @DeleteMapping({"/{id}"})
//    public ResponseEntity<UsuarioDetailResponseDto> excluir(@PathVariable Long id) {
//        UsuarioDetailResponseDto usuarioDeletado = this.usuarioService.excluir(id);
//        return ResponseEntity.ok(usuarioDeletado);
//    }
//
//    @Operation(
//            summary = "Detalhar usuário",
//            description = "Retorna os detalhes de um usuário pelo ID."
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Detalhes do usuário retornados com sucesso"
//    )
//    @GetMapping({"/{id}"})
//    public ResponseEntity<UsuarioDetailResponseDto> detalhar(@PathVariable Long id) {
//        UsuarioDetailResponseDto dto = this.usuarioService.buscarPorId(id);
//        return ResponseEntity.ok(dto);
//    }
}
