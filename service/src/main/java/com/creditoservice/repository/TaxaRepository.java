package com.creditoservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.creditoservice.domain.Taxa;

@Repository
public interface TaxaRepository extends MongoRepository<Taxa, String> {
	Optional<Taxa> findByTipo(String tipo);
	 void deleteByTipo(String tipo);

}