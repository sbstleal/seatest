package com.seasolutions.rh.forms;

import org.hibernate.validator.constraints.br.CPF;

import com.seasolutions.rh.exceptions.CPFAtualizacaoException;
import com.seasolutions.rh.models.Trabalhador;
import com.seasolutions.rh.repositories.TrabalhadorRepository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TrabalhadorForm {
	
	private static final String MENSAGEM_CPF_NAO_ATUALIZAVEL = "O CPF não pode ser atualizado";

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull(message = "O cargo é obrigatório")
    private Long cargo;

    public TrabalhadorForm() {
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

    public Long getCargo() {
        return cargo;
    }

    public void setCargo(Long cargo) {
        this.cargo = cargo;
    }

    public Trabalhador atualizar(Long id, TrabalhadorRepository trabalhadorRepository) {
        Trabalhador trabalhador = trabalhadorRepository.getReferenceById(id);

        // Verifica se o CPF é diferente do CPF atual do trabalhador
        if (!this.cpf.equals(trabalhador.getCpf())) {
            throw new CPFAtualizacaoException(MENSAGEM_CPF_NAO_ATUALIZAVEL);
        }

        // Atualiza os outros campos normalmente
        trabalhador.setNome(this.nome);
        // ...

        return trabalhador;
    }
}
