package com.seasolutions.rh.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.seasolutions.rh.dtos.TrabalhadorDTO;
import com.seasolutions.rh.exceptions.CPFAtualizacaoException;
import com.seasolutions.rh.exceptions.CPFJaExistenteException;
import com.seasolutions.rh.exceptions.CargoNaoEncontradoException;
import com.seasolutions.rh.exceptions.ErroNoFormularioDTO;
import com.seasolutions.rh.exceptions.TrabalhadorNaoEncontradoException;
import com.seasolutions.rh.forms.TrabalhadorForm;
import com.seasolutions.rh.models.Cargo;
import com.seasolutions.rh.models.Trabalhador;
import com.seasolutions.rh.repositories.CargoRepository;
import com.seasolutions.rh.repositories.TrabalhadorRepository;

@RestController
@RequestMapping("/trabalhador")
public class TrabalhadorController {

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;

    @Autowired
    private CargoRepository cargoRepository;

    private static final String MENSAGEM_CPF_JA_EXISTENTE = "CPF já existe";
    private static final String MENSAGEM_CARGO_NAO_ENCONTRADO = "Cargo não encontrado";

    @GetMapping
    public List<TrabalhadorDTO> listar() {
        List<Trabalhador> trabalhadores = trabalhadorRepository.findAll();
        return TrabalhadorDTO.converter(trabalhadores);
    }
    
    @GetMapping("/buscarPorCpf/{cpf}")
    public ResponseEntity<TrabalhadorDTO> buscarPorCpf(@PathVariable String cpf) {
        Optional<Trabalhador> trabalhadorOptional = trabalhadorRepository.findByCpf(cpf);
        if (trabalhadorOptional.isEmpty()) {
            throw new TrabalhadorNaoEncontradoException("Trabalhador não encontrado");
        }
        Trabalhador trabalhador = trabalhadorOptional.get();
        return ResponseEntity.ok(new TrabalhadorDTO(trabalhador));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid TrabalhadorForm form, UriComponentsBuilder uriBuilder) {
        try {
            if (trabalhadorRepository.existsByCpf(form.getCpf())) {
                throw new CPFJaExistenteException(MENSAGEM_CPF_JA_EXISTENTE);
            }

            Cargo cargo = cargoRepository.findById(form.getCargo())
                    .orElseThrow(() -> new CargoNaoEncontradoException(MENSAGEM_CARGO_NAO_ENCONTRADO));

            Trabalhador trabalhador = new Trabalhador(form.getNome(), form.getCpf(), cargo);
            trabalhadorRepository.save(trabalhador);

            URI uri = uriBuilder.path("/trabalhador/{id}").buildAndExpand(trabalhador.getId()).toUri();
            return ResponseEntity.created(uri).body(new TrabalhadorDTO(trabalhador));
        } catch (CPFJaExistenteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (CargoNaoEncontradoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody @Valid TrabalhadorForm form) {
        try {
            Trabalhador trabalhador = form.atualizar(id, trabalhadorRepository);
            return ResponseEntity.ok(new TrabalhadorDTO(trabalhador));
        } catch (CargoNaoEncontradoException e) {
            ErroNoFormularioDTO erroDTO = new ErroNoFormularioDTO("cpf", e.getMessage());
            return ResponseEntity.badRequest().body(erroDTO);
        } catch (CPFAtualizacaoException e) {
            ErroNoFormularioDTO erroDTO = new ErroNoFormularioDTO("cpf", e.getMessage());
            return ResponseEntity.badRequest().body(erroDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        trabalhadorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({ CPFJaExistenteException.class, CargoNaoEncontradoException.class })
    public ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
