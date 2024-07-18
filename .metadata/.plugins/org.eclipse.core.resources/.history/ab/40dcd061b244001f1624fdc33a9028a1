package com.umpar.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.umpar.domain.Usuario;


@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<UserDetails> findByEmail(String email);
}