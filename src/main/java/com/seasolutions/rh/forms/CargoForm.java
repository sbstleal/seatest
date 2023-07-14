package com.seasolutions.rh.forms;

import java.math.BigDecimal;

import com.seasolutions.rh.models.Cargo;
import com.seasolutions.rh.repositories.CargoRepository;

import jakarta.validation.constraints.NotNull;

public class CargoForm {

    @NotNull
    private String nome;

    @NotNull
    private BigDecimal salario;

    @NotNull
    private Long setor;

    public CargoForm() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getSetor() {
        return setor;
    }

    public void setSetor(Long setor) {
        this.setor = setor;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Cargo atualizar(Long id, CargoRepository cargoRepository) {
        Cargo cargo = cargoRepository.getReferenceById(id);
        cargo.setNome(this.nome);
        cargo.setSalario(this.salario);
        return cargo;
    }

}
