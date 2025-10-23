package org.example.emotiwave.domain.exceptions;



import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErros {
    public TratadorErros() {
    }

    @ExceptionHandler({UsuarioJaCadastrado.class})
    public ResponseEntity<Map<String, String>> UsuarioJaCadastrado(UsuarioJaCadastrado ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(this.detalhar(ex));
    }

    @ExceptionHandler({HuggingFaceException.class})
    public ResponseEntity<Map<String, String>> HuggingFaceException(HuggingFaceException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.detalhar(ex));
    }

    @ExceptionHandler({GeniusLyricsNaoEncontrada.class})
    public ResponseEntity<Map<String, String>> GeniusLyricsNaoEncontrada(GeniusLyricsNaoEncontrada ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.detalhar(ex));
    }

    @ExceptionHandler({FalhaAoPegarTokenAcess.class})
    public ResponseEntity<Map<String, String>> FalhaAoPegarTokenAcess(FalhaAoPegarTokenAcess ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(this.detalhar(ex));
    }

    @ExceptionHandler({MusicaSpotifyNaoEncontrada.class})
    public ResponseEntity<Map<String, String>> MusicaSpotifyNaoEncontrada(MusicaSpotifyNaoEncontrada ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.detalhar(ex));
    }

    @ExceptionHandler({MusicaNaoEcontrada.class})
    public ResponseEntity<Map<String, String>> MusicaNaoEcontrada(MusicaNaoEcontrada ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.detalhar(ex));
    }

    @ExceptionHandler({AutenticacaoFalhou.class})
    public ResponseEntity<Map<String, String>> AutenticacaoFalhou(AutenticacaoFalhou ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(this.detalhar(ex));
    }

    @ExceptionHandler({ErroAoDesvincularMusicaAoUsuario.class})
    public ResponseEntity<Map<String, String>> ErroAoDesvincularMusicaAoUsuario(ErroAoDesvincularMusicaAoUsuario ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(this.detalhar(ex));
    }

    @ExceptionHandler({LetraMusicaNaoEncontradaGenius.class})
    public ResponseEntity<Map<String,String>> LetraMusicaNaoEncontradaGenius(LetraMusicaNaoEncontradaGenius ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.detalhar(ex));
    }

    public Map<String, String> detalhar(Exception ex) {
        Map<String, String> map = new HashMap();
        map.put("erro", ex.getClass().getSimpleName());
        map.put("mensagem", ex.getMessage());
        return map;
    }
}
