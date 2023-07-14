package com.seasolutions.rh.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.seasolutions.rh.models.Trabalhador;

public class TrabalhadorDTO {

    private String nome;
    private String cpf;
    private String cargoNome;
    private String setorNome;

    public TrabalhadorDTO(Trabalhador trabalhador) {
        if (trabalhador != null) {
            this.nome = trabalhador.getNome();
            this.cpf = trabalhador.getCpf();
            if (trabalhador.getCargo() != null) {
                this.cargoNome = trabalhador.getCargo().getNome();
                if (trabalhador.getCargo().getSetor() != null) {
                    this.setorNome = trabalhador.getCargo().getSetor().getNome();
                }
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargoNome() {
        return cargoNome;
    }

    public void setCargoNome(String cargoNome) {
        this.cargoNome = cargoNome;
    }

    public String getSetorNome() {
        return setorNome;
    }

    public void setSetorNome(String setorNome) {
        this.setorNome = setorNome;
    }

    public static List<TrabalhadorDTO> converter(List<Trabalhador> trabalhadores) {
        return trabalhadores.stream().map(TrabalhadorDTO::new).collect(Collectors.toList());
    }
}
