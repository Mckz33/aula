package com.video_aula.aula.controllers;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.video_aula.aula.models.Aluno;
import com.video_aula.aula.services.AlunoService;

@RestController
@RequestMapping("api/aluno")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody Aluno aluno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.save(aluno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluno>> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Aluno> alunoOptional = alunoService.findById(id);
        if (alunoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }
        alunoService.delete(alunoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Aluno aluno) {
        Optional<Aluno> alunoOptional = alunoService.findById(id);
        if (alunoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }
        aluno.setId(alunoOptional.get().getId());
        BeanUtils.copyProperties(alunoOptional, aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.save(aluno));
    }
}
