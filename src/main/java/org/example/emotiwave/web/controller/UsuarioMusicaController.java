package org.example.emotiwave.web.controller;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;



import org.example.emotiwave.application.mapper.MusicaMapper;


import io.swagger.v3.oas.annotations.Operation;
import java.io.IOException;
import java.util.List;

import org.example.emotiwave.application.dto.in.MusicaSelecionadaDto;
import org.example.emotiwave.application.dto.in.MusicaSimplesDto;
import org.example.emotiwave.application.dto.in.MusicasSelecionadasDto;
import org.example.emotiwave.application.service.MusicaService;
import org.example.emotiwave.application.service.spotifyServices.SpotifyService;
import org.example.emotiwave.application.service.usuarioMusicaServices.AssociarMusicasAoUsuarioService;
import org.example.emotiwave.application.service.usuarioMusicaServices.UsuarioMusicaService;
import org.example.emotiwave.application.service.spotifyServices.SpotifyMusicasRecentesService;
import org.example.emotiwave.application.service.spotifyServices.TopMusicasSpotifyService;
import org.example.emotiwave.domain.entities.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/usuarios"})
public class UsuarioMusicaController {

    private final AssociarMusicasAoUsuarioService relacionarMusicasOuvidasAoUsuario;
    private final TopMusicasSpotifyService topMusicasSpotifyService;
    private final SpotifyMusicasRecentesService spotifyMusicasRecentesService;
    private final UsuarioMusicaService usuarioMusicaService;
    private final SpotifyService spotifyService;
    private final MusicaService musicaService;
    private final MusicaMapper musicaMapper;

    public UsuarioMusicaController(AssociarMusicasAoUsuarioService relacionarMusicasOuvidasAoUsuario1, TopMusicasSpotifyService topMusicasSpotifyService, SpotifyMusicasRecentesService spotifyMusicasRecentesService, UsuarioMusicaService usuarioMusicaService, SpotifyService spotifyService, MusicaService musicaService, MusicaMapper musicaMapper) {

        this.relacionarMusicasOuvidasAoUsuario = relacionarMusicasOuvidasAoUsuario1;
        this.topMusicasSpotifyService = topMusicasSpotifyService;
        this.spotifyMusicasRecentesService = spotifyMusicasRecentesService;
        this.usuarioMusicaService = usuarioMusicaService;
        this.spotifyService = spotifyService;
        this.musicaService = musicaService;
        this.musicaMapper = musicaMapper;
    }


    @Operation(
            summary = "Salvar preferências musicais do usuário",
            description = "Registra as músicas selecionadas pelo usuário sem spotify, criando ou atualizando os relacionamentos no banco de dados entre usuário e música."
    )
    @PostMapping({"/musicas/preferenciasv"})
    public ResponseEntity salvarPreferenciasMusicais(@AuthenticationPrincipal Usuario usuario, @RequestBody MusicasSelecionadasDto musicasSelecionadasDto) {
        this.relacionarMusicasOuvidasAoUsuario.processarRelacionamentos(musicasSelecionadasDto, usuario);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Listar top músicas do usuário via Spotify",
            description = "Busca as músicas mais ouvidas pelo usuário através da integração com Spotify e retorna a lista de forma paginada, registrando os dados no banco quando necessário."
    )
    @GetMapping({"/musicas/spotify/top"})
    public ResponseEntity userTopRead(@AuthenticationPrincipal Usuario usuario) {
        List<MusicaSimplesDto> response = this.topMusicasSpotifyService.buscarTopMusicasSpotify(usuario);
        return ResponseEntity.ok(response);
    }


    @Operation(
            summary = "Listar músicas recentemente tocadas via Spotify",
            description = "Retorna as músicas que o usuário escutou recentemente no Spotify, chamando a API do Spotify e registrando os dados no banco se necessário."
    )
    @GetMapping({"/musicas/spotify/recentesv1"})
    public ResponseEntity buscaMusicasRecentesSpotifyv1(@AuthenticationPrincipal Usuario usuario) throws IOException, InterruptedException {
        ResponseEntity<List<MusicaSimplesDto>> response = this.spotifyMusicasRecentesService.buscarMusicasOuvidasRecentes(usuario);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/musicas/spotify/recentes")
    public ResponseEntity<CollectionModel<EntityModel<MusicaSimplesDto>>> buscaMusicasRecentesSpotify(
            @AuthenticationPrincipal Usuario usuario) throws IOException, InterruptedException {

        List<MusicaSimplesDto> musicas = spotifyMusicasRecentesService.buscarMusicasOuvidasRecentes(usuario).getBody();

        if (musicas == null || musicas.isEmpty()) {
            return ResponseEntity.ok(CollectionModel.empty());
        }
        List<EntityModel<MusicaSimplesDto>> musicasModel = musicas.stream()
                .map(musica -> {
                    try {
                        return EntityModel.of(
                                musica,


                                linkTo(methodOn(UsuarioMusicaController.class)
                                        .buscaMusicasRecentesSpotify(usuario))
                                        .slash(musica.getSpotifyTrackId())
                                        .withSelfRel(),


                                linkTo(methodOn(UsuarioMusicaController.class)
                                        .salvarPreferenciasMusicais(usuario, null))
                                        .withRel("salvar-preferencias"),


                                linkTo(methodOn(UsuarioMusicaController.class)
                                        .selecionarMusica(usuario, musica.getSpotifyTrackId(), null))
                                        .withRel("selecionar"),


                                linkTo(methodOn(UsuarioMusicaController.class)
                                        .userTopRead(usuario))
                                        .withRel("spotify-top"),


                                linkTo(methodOn(UsuarioMusicaController.class)
                                        .musicasRelaxantes(null, usuario))
                                        .withRel("musicas-relaxantes"),


                                linkTo(methodOn(MusicaController.class)
                                        .analise(null,usuario))
                                        .withRel("analisar")
                        );
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();

        // Adiciona links de coleção (nível superior)
        CollectionModel<EntityModel<MusicaSimplesDto>> collectionModel = CollectionModel.of(
                musicasModel,

                // Self link
                linkTo(methodOn(UsuarioMusicaController.class)
                        .buscaMusicasRecentesSpotify(usuario))
                        .withSelfRel(),

                // Link para top músicas Spotify
                linkTo(methodOn(UsuarioMusicaController.class)
                        .userTopRead(usuario))
                        .withRel("spotify-top"),

                // Link para músicas relaxantes
                linkTo(methodOn(UsuarioMusicaController.class)
                        .musicasRelaxantes(null, usuario))
                        .withRel("musicas-relaxantes"),

                // Link para preferências do usuário
                linkTo(methodOn(UsuarioMusicaController.class)
                        .salvarPreferenciasMusicais(usuario, null))
                        .withRel("preferencias")
        );

        return ResponseEntity.ok(collectionModel);
    }



    @Operation(
            summary = "Listar músicas recentes do usuário sem Spotify",
            description = "Retorna as músicas que o usuário escutou hoje ou recentemente, para usuários que não possuem Spotify associado."
    )
    @GetMapping("/musicas/recentes")
    public ResponseEntity<CollectionModel<EntityModel<MusicaSimplesDto>>> recentes(
            @AuthenticationPrincipal Usuario usuario) throws IOException, InterruptedException {

        List<MusicaSimplesDto> musicas = usuarioMusicaService.pegarMusicasRecentes(usuario);


        List<EntityModel<MusicaSimplesDto>> musicasModel = musicas.stream()
                .map(musica -> {
                    try {
                        return EntityModel.of(
                                musica,

                                linkTo(methodOn(UsuarioMusicaController.class)
                                        .recentes(usuario)).withSelfRel(),


                                linkTo(methodOn(UsuarioMusicaController.class)
                                        .salvarPreferenciasMusicais(null, null))
                                        .withRel("salvar-preferencias")
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();


        CollectionModel<EntityModel<MusicaSimplesDto>> collectionModel = CollectionModel.of(
                musicasModel,
                linkTo(methodOn(UsuarioMusicaController.class).recentes(usuario)).withSelfRel(),
                linkTo(methodOn(UsuarioMusicaController.class)
                        .musicasOuvidasRecentemente(null, usuario))
                        .withRel("musicas-recentes-global")
        );

        return ResponseEntity.ok(collectionModel);
    }



    @Operation(
            summary = "Listar músicas recentes do usuário sem Spotify",
            description = "Retorna as músicas que o usuário escutou hoje ou recentemente, para usuários que não possuem Spotify associado."
    )
    @GetMapping({"/musicas"})
    public ResponseEntity<CollectionModel<EntityModel<MusicaSimplesDto>>> musicasOuvidasRecentemente(
            @PageableDefault(size = 10) Pageable paginacao,
            @AuthenticationPrincipal Usuario usuario) throws IOException, InterruptedException {


        List<MusicaSimplesDto> musicas = usuarioMusicaService.musicasRecemOuvidas(paginacao, usuario);


        List<EntityModel<MusicaSimplesDto>> musicasModel = musicas.stream()
                .map(musica -> {
                    try {
                        return EntityModel.of(
                                musica,

                                // Link para este recurso (self)
                                linkTo(methodOn(UsuarioMusicaController.class)
                                        .musicasOuvidasRecentemente(paginacao, usuario))
                                        .slash(musica.getSpotifyTrackId())
                                        .withSelfRel(),

                                // Link para marcar música como selecionada
                                linkTo(methodOn(UsuarioMusicaController.class)
                                        .selecionarMusica(usuario, musica.getSpotifyTrackId(), null))
                                        .withRel("selecionar"),

                                // Link para desvincular música
                                linkTo(methodOn(UsuarioMusicaController.class)
                                        .delete(usuario, musica.getSpotifyTrackId()))
                                        .withRel("desvincular"),

                                // Link para salvar preferências musicais (post múltiplo)
                                linkTo(methodOn(UsuarioMusicaController.class)
                                        .salvarPreferenciasMusicais(usuario, null))
                                        .withRel("salvar-preferencias")
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();

        // Adiciona link de coleção (self)
        CollectionModel<EntityModel<MusicaSimplesDto>> collectionModel = CollectionModel.of(
                musicasModel,
                linkTo(methodOn(UsuarioMusicaController.class)
                        .musicasOuvidasRecentemente(paginacao, usuario))
                        .withSelfRel(),

                // Link adicional para ver músicas recentes via Spotify
                linkTo(methodOn(UsuarioMusicaController.class)
                        .buscaMusicasRecentesSpotify(usuario))
                        .withRel("spotify-recentes"),


                linkTo(methodOn(UsuarioMusicaController.class)
                        .userTopRead(usuario))
                        .withRel("spotify-top")
        );

        return ResponseEntity.ok(
                CollectionModel.of(
                        musicasModel,
                        linkTo(methodOn(UsuarioMusicaController.class)
                                .musicasOuvidasRecentemente(paginacao, usuario)).withSelfRel(),
                        linkTo(methodOn(UsuarioMusicaController.class)
                                .buscaMusicasRecentesSpotify(usuario)).withRel("spotify-recentes"),
                        linkTo(methodOn(UsuarioMusicaController.class)
                                .userTopRead(usuario)).withRel("spotify-top")
                )
        );
    }


    @Operation(
            summary = "Listar músicas relaxantes",
            description = "Retorna uma lista de músicas relaxantes recomendadas, com links HATEOAS para ações relacionadas."
    )
    @GetMapping("/musicas/relaxantes")
    public ResponseEntity<CollectionModel<EntityModel<MusicaSimplesDto>>> musicasRelaxantes(
            @PageableDefault(size = 10) Pageable paginacao,
            @AuthenticationPrincipal Usuario usuario) throws IOException, InterruptedException {

        Page<MusicaSimplesDto> musicasPage = musicaService.listarMusicasRelaxantes(paginacao);
        List<MusicaSimplesDto> musicas = musicasPage.getContent();

        // Mapeia cada música para EntityModel com links úteis
        List<EntityModel<MusicaSimplesDto>> musicasModel = musicas.stream()
                .map(musica -> {
                    try {
                        return EntityModel.of(
                                musica,

                                // Self link (para a própria música relaxante)
                                linkTo(methodOn(UsuarioMusicaController.class)
                                        .musicasRelaxantes(paginacao, usuario))
                                        .slash(musica.getSpotifyTrackId())
                                        .withSelfRel(),

                                // Link para salvar nas preferências
                                linkTo(methodOn(UsuarioMusicaController.class)
                                        .salvarPreferenciasMusicais(usuario, null))
                                        .withRel("salvar-preferencias"),

                                // Link para marcar como selecionada
                                linkTo(methodOn(UsuarioMusicaController.class)
                                        .selecionarMusica(usuario, musica.getSpotifyTrackId(), null))
                                        .withRel("selecionar"),

                                // Link para ouvir via Spotify
                                linkTo(methodOn(UsuarioMusicaController.class)
                                        .buscaMusicasRecentesSpotify(usuario))
                                        .withRel("spotify-recentes"),

                                // Link para análise emocional
                                linkTo(methodOn(MusicaController.class)
                                        .analise(null,usuario))
                                        .withRel("analisar")
                        );
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();

        // Adiciona links de navegação da coleção
        CollectionModel<EntityModel<MusicaSimplesDto>> collectionModel = CollectionModel.of(
                musicasModel,

                // Self link principal
                linkTo(methodOn(UsuarioMusicaController.class)
                        .musicasRelaxantes(paginacao, usuario))
                        .withSelfRel(),

                // Link para top músicas via Spotify
                linkTo(methodOn(UsuarioMusicaController.class)
                        .userTopRead(usuario))
                        .withRel("spotify-top"),

                // Link para músicas recentes
                linkTo(methodOn(UsuarioMusicaController.class)
                        .musicasOuvidasRecentemente(paginacao, usuario))
                        .withRel("musicas-recentes"),

                // Link para preferências musicais
                linkTo(methodOn(UsuarioMusicaController.class)
                        .salvarPreferenciasMusicais(usuario, null))
                        .withRel("preferencias")
        );

        return ResponseEntity.ok(collectionModel);
    }




//    @PatchMapping({"/musicas/{id}/acao"})
//    public ResponseEntity favoritar() {
//        return ResponseEntity.ok().build();
//    }
//


    @Operation(
            summary = "Desvincular música do usuário",
            description = "Remove a associação de uma música específica com o usuário, sem deletar a música do sistema global."
    )
    @DeleteMapping({"/musicas/preferencias/{musicaId}"})
    public ResponseEntity delete(@AuthenticationPrincipal Usuario usuario, @PathVariable String musicaId) {
        this.usuarioMusicaService.desvincular(usuario, musicaId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Marcar música como selecionada pelo usuário",
            description = "Atualiza o relacionamento entre o usuário e a música, permitindo marcar ou desmarcar como selecionada."
    )
    @PatchMapping({"/musicas/preferencias/musica/{id}"})
    public ResponseEntity selecionarMusica(@AuthenticationPrincipal Usuario usuario, @PathVariable String id, @RequestBody MusicaSelecionadaDto musicaSelecionadaDto) {
        this.usuarioMusicaService.marcarComoSelecionada(usuario, id, musicaSelecionadaDto);
        return ResponseEntity.noContent().build();
    }
}

