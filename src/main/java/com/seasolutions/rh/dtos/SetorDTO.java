package com.seasolutions.rh.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.seasolutions.rh.models.Setor;

public class SetorDTO {

	private String nome;
	private Long cargos;
	
	public SetorDTO(Setor setor) {
		this.nome = setor.getNome();
		// Realizar a contagem dos cargos pertencentes ao setor
		this.cargos = (long) setor.getCargos().size();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCargos() {
		return cargos;
	}

	public void setCargos(Long cargos) {
		this.cargos = cargos;
	}

	public static List<SetorDTO> converter(List<Setor> setor) {
		return setor.stream().map(SetorDTO::new).collect(Collectors.toList());
	}

}
