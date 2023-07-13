package com.seasolutions.rh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seasolutions.rh.model.Setor;

public interface SetorRepository extends JpaRepository<Setor, Long> {

	Optional<Setor> findByNome(String nome);

}
