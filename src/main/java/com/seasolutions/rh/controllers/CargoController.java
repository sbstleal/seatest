package com.seasolutions.rh.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.seasolutions.rh.dtos.CargoDTO;
import com.seasolutions.rh.forms.CargoForm;
import com.seasolutions.rh.models.Cargo;
import com.seasolutions.rh.models.Setor;
import com.seasolutions.rh.repositories.CargoRepository;
import com.seasolutions.rh.repositories.SetorRepository;
import com.seasolutions.rh.exceptions.CargoDuplicadoException;
import com.seasolutions.rh.exceptions.CargoNaoEncontradoException;
import com.seasolutions.rh.exceptions.SetorNaoEncontradoException;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cargo")
public class CargoController {

    private static final String MENSAGEM_CARGO_DUPLICADO = "Cargo com o mesmo nome já existe";
    private static final String MENSAGEM_SETOR_NAO_ENCONTRADO = "Setor não encontrado";
    private static final String MENSAGEM_CARGO_NAO_ENCONTRADO = "Cargo não encontrado";
    
    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private SetorRepository setorRepository;

    @GetMapping
    public List<CargoDTO> listar() {
        List<Cargo> cargos = cargoRepository.findAll();
        return CargoDTO.converter(cargos);
    }
    
    @GetMapping("/buscarPorNome/{nome}")
    public ResponseEntity<List<CargoDTO>> buscarPorNome(@PathVariable String nome) {
        List<Cargo> cargos = cargoRepository.findAllByNome(nome);
        if (cargos.isEmpty()) {
            throw new CargoNaoEncontradoException(MENSAGEM_CARGO_NAO_ENCONTRADO);
        }
        List<CargoDTO> cargoDTOs = CargoDTO.converter(cargos);
        return ResponseEntity.ok(cargoDTOs);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CargoForm form, UriComponentsBuilder uriBuilder) {
        Optional<Cargo> cargoBusca = cargoRepository.findByNome(form.getNome());
        if (cargoBusca.isPresent()) {
            throw new CargoDuplicadoException(MENSAGEM_CARGO_DUPLICADO);
        }
        Setor setor = setorRepository.findById(form.getSetor())
                .orElseThrow(() -> new SetorNaoEncontradoException(MENSAGEM_SETOR_NAO_ENCONTRADO));
        Cargo cargo = new Cargo(form.getNome(), form.getSalario(), setor);
        cargoRepository.save(cargo);
        URI uri = uriBuilder.path("/cargo/{id}").buildAndExpand(cargo.getId()).toUri();
        return ResponseEntity.created(uri).body(new CargoDTO(cargo));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CargoDTO> alterar(@PathVariable Long id, @RequestBody @Valid CargoForm form) {
        Optional<Cargo> cargoExistenteOptional = cargoRepository.findById(id);
        if (cargoExistenteOptional.isEmpty()) {
            throw new CargoNaoEncontradoException(MENSAGEM_CARGO_NAO_ENCONTRADO);
        }

        Optional<Cargo> cargoBusca = cargoRepository.findByNome(form.getNome());
        if (cargoBusca.isPresent() && !cargoBusca.get().getId().equals(id)) {
            throw new CargoDuplicadoException(MENSAGEM_CARGO_DUPLICADO);
        }

        Cargo cargo = form.atualizar(id, cargoRepository);
        return ResponseEntity.ok(new CargoDTO(cargo));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        cargoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
