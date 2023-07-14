package com.seasolutions.rh.forms;

import java.util.List;

import com.seasolutions.rh.models.Cargo;
import com.seasolutions.rh.models.Setor;
import com.seasolutions.rh.repositories.SetorRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SetorForm {
	
	@NotNull
	private String nome;
	
	@NotEmpty
	private List<Cargo> cargos;
	
	public SetorForm(@NotNull String nome, @NotEmpty List<Cargo> cargos) {
		this.nome = nome;
		this.cargos = cargos;
	}

	public Setor converter() {
		return new Setor(nome, cargos);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public Setor atualizar(Long id, SetorRepository setorRepository) {
		Setor setor = setorRepository.getReferenceById(id);
		setor.setNome(this.nome);
		return setor;
	}
	
}
