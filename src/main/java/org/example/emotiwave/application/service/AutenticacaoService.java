package org.example.emotiwave.application.service;


import jakarta.validation.Valid;
import org.example.emotiwave.application.dto.in.DadosAuthRequestDto;
import org.example.emotiwave.application.dto.in.UsuarioCreateRequestDto;
import org.example.emotiwave.application.dto.out.UsuarioDetailResponseDto;
import org.example.emotiwave.application.mapper.UsuarioMapper;
import org.example.emotiwave.domain.entities.Usuario;
import org.example.emotiwave.domain.exceptions.AutenticacaoFalhou;
import org.example.emotiwave.domain.exceptions.UsuarioJaCadastrado;
import org.example.emotiwave.infra.repository.UsuarioRepository;
import org.example.emotiwave.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {


    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;



    public AutenticacaoService(TokenService tokenService, AuthenticationManager authenticationManager, UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String autenticar(@Valid DadosAuthRequestDto dados) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.password());
            UserDetails userDetails = (UserDetails)this.authenticationManager.authenticate(authenticationToken).getPrincipal();
            String tokenJwt = this.tokenService.gerarToken(userDetails);
            return tokenJwt;
        } catch (AuthenticationException var5) {
            throw new AutenticacaoFalhou("Usuário ou senha inválidos");
        }
    }

    public UsuarioDetailResponseDto registrar(UsuarioCreateRequestDto dto) {

        Usuario usuario = usuarioMapper.toUsuario(dto);

        usuarioRepository.findByEmail(usuario.getEmail())
                .ifPresent(u -> { throw new UsuarioJaCadastrado("Email em uso!"); });


        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);

        return usuarioMapper.toUsuarioDetailsReponseDto(usuario);

    }
}
