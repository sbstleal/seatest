package com.seasolutions.rh.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.seasolutions.rh.dtos.SetorDTO;
import com.seasolutions.rh.exceptions.SetorDuplicadoException;
import com.seasolutions.rh.exceptions.SetorNaoEncontradoException;
import com.seasolutions.rh.forms.SetorForm;
import com.seasolutions.rh.models.Setor;
import com.seasolutions.rh.repositories.SetorRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/setor")
public class SetorController {

    private static final String MENSAGEM_SETOR_DUPLICADO = "Setor com o mesmo nome já existe";
    private static final String MENSAGEM_SETOR_NAO_ENCONTRADO = "Setor não encontrado";

    @Autowired
    private SetorRepository setorRepository;

    @GetMapping
    public List<SetorDTO> listar() {
        List<Setor> setores = setorRepository.findAll();
        return SetorDTO.converter(setores);
    }

    @GetMapping("/buscarPorNome/{nome}")
    public ResponseEntity<SetorDTO> buscarPorNome(@PathVariable String nome) {
        Optional<Setor> setorOptional = setorRepository.findByNome(nome);
        if (setorOptional.isEmpty()) {
            throw new SetorNaoEncontradoException(MENSAGEM_SETOR_NAO_ENCONTRADO);
        }
        Setor setor = setorOptional.get();
        return ResponseEntity.ok(new SetorDTO(setor));
    }

    @PostMapping
    public ResponseEntity<SetorDTO> cadastrar(@RequestBody @Valid SetorForm form) {
        Optional<Setor> setorBusca = setorRepository.findByNome(form.getNome());
        if (setorBusca.isPresent()) {
            throw new SetorDuplicadoException(MENSAGEM_SETOR_DUPLICADO);
        }
        Setor setor = form.converter();
        setorRepository.save(setor);
        URI uri = URI.create("/setor/" + setor.getId());
        return ResponseEntity.created(uri).body(new SetorDTO(setor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SetorDTO> alterar(@PathVariable Long id, @RequestBody @Valid SetorForm form) {
        Optional<Setor> setorExistenteOptional = setorRepository.findByNome(form.getNome());
        if (setorExistenteOptional.isPresent() && !setorExistenteOptional.get().getId().equals(id)) {
            throw new SetorDuplicadoException(MENSAGEM_SETOR_DUPLICADO);
        }

        Setor setor = form.atualizar(id, setorRepository);
        return ResponseEntity.ok(new SetorDTO(setor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        setorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
 