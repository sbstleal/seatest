package com.seasolutions.rh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seasolutions.rh.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

	Optional<Cargo> findByNome(String nome);
	
}

