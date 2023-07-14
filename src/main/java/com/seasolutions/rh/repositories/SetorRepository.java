package com.seasolutions.rh.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seasolutions.rh.models.Setor;

public interface SetorRepository extends JpaRepository<Setor, Long> {

	Optional<Setor> findByNome(String nome);

}
