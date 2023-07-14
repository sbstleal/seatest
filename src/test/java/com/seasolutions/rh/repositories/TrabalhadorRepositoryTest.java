package com.seasolutions.rh.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.seasolutions.rh.models.Trabalhador;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) 
public class TrabalhadorRepositoryTest {

	@Autowired
	private TrabalhadorRepository trabalhadorRepository;
	
	@Test
	void buscarPorCpf() {
		String cpf = "31319099009";
		Optional<Trabalhador> trabalhador = trabalhadorRepository.findByCpf(cpf);
		assertNotNull(cpf);
		assertEquals(cpf, trabalhador.get().getCpf());
		
	}



}
