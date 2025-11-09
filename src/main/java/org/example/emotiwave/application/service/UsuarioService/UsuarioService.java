package org.example.emotiwave.application.service.UsuarioService;




import org.example.emotiwave.application.dto.in.UsuarioCreateRequestDto;

import org.example.emotiwave.application.dto.out.UsuarioDetailResponseDto;

import org.example.emotiwave.application.dto.out.UsuarioListResponseDto;
import org.example.emotiwave.application.mapper.UsuarioMapper;
import org.example.emotiwave.domain.entities.Usuario;
import org.example.emotiwave.domain.exceptions.UsuarioJaCadastrado;
import org.example.emotiwave.domain.exceptions.UsuarioNaoEncontrado;
import org.example.emotiwave.infra.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;


    public UsuarioService(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }



}