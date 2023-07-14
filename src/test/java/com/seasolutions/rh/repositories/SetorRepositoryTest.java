package com.seasolutions.rh.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.seasolutions.rh.models.Setor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) 
public class SetorRepositoryTest {
	
	@Autowired
	private SetorRepository setorRepository;

	@Test
	void buscarPorNome() {
		String setorNome = "Setor 1";
		Optional<Setor> setor = setorRepository.findByNome(setorNome);
		assertNotNull(setor);
		assertEquals(setorNome, setor.get().getNome());
		
	}



}
