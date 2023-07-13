package com.seasolutions.rh.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cargo {
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private BigDecimal salario;
	
	@ManyToOne
	@JoinColumn(name = "setor_id", unique = true)
	private Setor setor;
	
	@OneToMany(mappedBy = "cargo", fetch = FetchType.EAGER)
	private List<Trabalhador> trabalhador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public List<Trabalhador> getTrabalhador() {
		return trabalhador;
	}

	public void setTrabalhador(List<Trabalhador> trabalhador) {
		this.trabalhador = trabalhador;
	}
	
    public Cargo(String nome, BigDecimal salario, Setor setor) {
        this.nome = nome;
        this.salario = salario;
        this.setor = setor;
    }

}
