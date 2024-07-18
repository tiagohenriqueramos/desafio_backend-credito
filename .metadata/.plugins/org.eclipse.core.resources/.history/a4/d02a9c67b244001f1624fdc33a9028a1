package com.umpar.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.umpar.domain.Usuario;
import com.umpar.dto.UsuarioRegisterDTO;
import com.umpar.dto.UsuarioResponseDTO;
import com.umpar.exception.EmailIndisponivelException;
import com.umpar.exception.InvalidParameterException;
import com.umpar.exception.NotFoundException;
import com.umpar.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void criarNovoUsuario(UsuarioRegisterDTO usuarioRegisterDTO) {
        if (usuarioRepository.findByEmail(usuarioRegisterDTO.email()).isPresent()) {
            throw new EmailIndisponivelException("Email já cadastrado!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioRegisterDTO.senha());
        Usuario usuario = new Usuario(usuarioRegisterDTO.nome(), usuarioRegisterDTO.email(), encryptedPassword, usuarioRegisterDTO.role());

        usuarioRepository.save(usuario);
    }

    public UsuarioResponseDTO buscarUsuarioPorId(String id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        return new UsuarioResponseDTO(usuario);
    }

    public void alterarUsuario(String id, UsuarioRegisterDTO usuarioRegisterDTO) {
        if (id != null) {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

            if (usuarioRegisterDTO.email() != null) {
                if (usuarioRepository.findByEmail(usuarioRegisterDTO.email()).isPresent()) {
                    throw new EmailIndisponivelException("Email já cadastrado");
                }

                usuario.setEmail(usuarioRegisterDTO.email());
            }

            if (usuarioRegisterDTO.nome() != null) {
                usuario.setNome(usuarioRegisterDTO.nome());
            }

            if (usuarioRegisterDTO.senha() != null) {
                usuario.setSenha(usuarioRegisterDTO.senha());
            }

            if (usuarioRegisterDTO.role() != null) {
                usuario.setRole(usuarioRegisterDTO.role());
            } else {
                throw new InvalidParameterException("Role não pode ser nulo");
            }

            usuarioRepository.save(usuario);
        } else {
            throw new InvalidParameterException("Id está nulo");
        }
    }

    public void deletarUsuario(String id) {
        if (id != null) {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

            usuarioRepository.delete(usuario);
        } else {
            throw new InvalidParameterException("Id está nulo");
        }
    }

    public List<UsuarioResponseDTO> buscarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponseDTO::new)
                .toList();
    }
}