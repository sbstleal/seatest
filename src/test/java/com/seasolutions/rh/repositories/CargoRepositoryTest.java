package com.seasolutions.rh.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.seasolutions.rh.models.Cargo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) 
public class CargoRepositoryTest {
	
	@Autowired
	private CargoRepository cargoRepository;

	@Test
	void buscarPorNome() {
		String cargoNome = "Cargo 1";
		Optional<Cargo> cargo = cargoRepository.findByNome(cargoNome);
		assertNotNull(cargo);
		assertEquals(cargoNome, cargo.get().getNome());
		
	}

}
