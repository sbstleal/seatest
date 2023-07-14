package com.seasolutions.rh.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.seasolutions.rh.models.Trabalhador;
import com.seasolutions.rh.repositories.TrabalhadorRepository;

@SpringBootTest
@AutoConfigureMockMvc
class TrabalhadorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TrabalhadorRepository trabalhadorRepository;

	@Test
	void deveriaRetornar400CasoOCpfJaExista() throws Exception {
		URI uri = new URI("/trabalhador");
		String json = "{\"nome\":\"Trabalhador 1\",\"cpf\":\"24026494020\",\"setor\": 1}";
		
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void deveriaRetornarUmaListaDeCargos() {
		List<Trabalhador> trabalhador = trabalhadorRepository.findAll();
		assertNotNull(trabalhador);
		assertThat(trabalhador).asList();
	}
	
	@Test
	void deveriaRetornar200AoDeletarUmTrabalhador() throws Exception {
		URI uri = new URI("/trabalhador/1");
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete(uri)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	void deveriaRetornar200AoAlterarUmTrabalhador() throws Exception {
		URI uri = new URI("/cargo/1");
		String json = "{\"nome\":\"Cargo 6\",\"salario\": 2500,\"setor\": 1}";
		
		mockMvc.perform(MockMvcRequestBuilders
				.put(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}

}
