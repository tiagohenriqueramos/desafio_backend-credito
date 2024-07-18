package com.creditoservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.creditoservice.domain.Usuario;


@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<UserDetails> findByEmail(String email);
}