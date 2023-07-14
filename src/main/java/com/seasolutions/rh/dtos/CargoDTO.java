package com.seasolutions.rh.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.seasolutions.rh.models.Cargo;

public class CargoDTO {

	private final String nomeDoCargo;
	private final String nomeDoSetor;
	private final BigDecimal salario;
	
	public CargoDTO(Cargo cargo) {
        this.nomeDoCargo 
        	= cargo.getNome() != null 
        	? cargo.getNome() : "Nome do cargo indisponível";
        this.salario 
        	= cargo.getSalario() != null 
        	? cargo.getSalario() : BigDecimal.ZERO;
        this.nomeDoSetor 
        = cargo.getSetor() != null 
        ? cargo.getSetor().getNome() : "Setor desconhecido";
    }

	public String getNome() {
		return nomeDoCargo;
	}
	
	public String getSetor() {
		return nomeDoSetor;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public static List<CargoDTO> converter(List<Cargo> setor) {
		return setor.stream().map(CargoDTO::new).collect(Collectors.toList());
	}

}
