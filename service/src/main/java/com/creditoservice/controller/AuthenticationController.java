package com.creditoservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditoservice.config.security.TokenService;
import com.creditoservice.domain.Usuario;
import com.creditoservice.dto.AuthenticationDTO;
import com.creditoservice.dto.LoginResponseDTO;
import com.creditoservice.dto.UsuarioRegisterDTO;
import com.creditoservice.exception.ApiResponse;
import com.creditoservice.service.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioService usuarioService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(emailPassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody @Valid UsuarioRegisterDTO usuarioRegisterDTO) {
        usuarioService.criarNovoUsuario(usuarioRegisterDTO);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Usuário registrado com sucesso!"));
    }
}