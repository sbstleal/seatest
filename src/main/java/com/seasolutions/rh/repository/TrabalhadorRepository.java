package com.seasolutions.rh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seasolutions.rh.model.Trabalhador;

public interface TrabalhadorRepository extends JpaRepository<Trabalhador, Long> {

	Optional<Trabalhador> findByCpf(String cpf);
	
}