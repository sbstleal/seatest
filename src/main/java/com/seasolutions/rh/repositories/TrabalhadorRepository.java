package com.seasolutions.rh.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seasolutions.rh.models.Trabalhador;

public interface TrabalhadorRepository extends JpaRepository<Trabalhador, Long> {

	Optional<Trabalhador> findByCpf(String cpf);
	
	boolean existsByCpf(String cpf);
	
}