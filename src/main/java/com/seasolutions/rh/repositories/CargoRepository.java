package com.seasolutions.rh.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seasolutions.rh.models.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

	Optional<Cargo> findByNome(String nome);

	List<Cargo> findAllByNome(String nome);
	
}

