package com.creditocliente.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.creditocliente.domain.Cliente;


@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {

	Optional<Cliente> findByCpf(String cpf);
	
	Optional<Cliente> findByCelular(String celular);


}