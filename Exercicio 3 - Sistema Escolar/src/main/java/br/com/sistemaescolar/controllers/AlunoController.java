package br.com.sistemaescolar.controllers;

import br.com.sistemaescolar.domain.aluno.Aluno;
import br.com.sistemaescolar.domain.aluno.AlunoRequestDTO;
import br.com.sistemaescolar.domain.aluno.AlunoResponseDTO;
import br.com.sistemaescolar.repositories.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("aluno")
public class AlunoController {

    @Autowired
    AlunoRepository repository;

    @PostMapping
    public ResponseEntity postAluno(@RequestBody @Valid AlunoRequestDTO body){
        Aluno newAluno = new Aluno(body);

        this.repository.save(newAluno);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getTodosAlunos(){
        List<AlunoResponseDTO> alunosList = this.repository.findAll().stream().map(AlunoResponseDTO::new).toList();

        return ResponseEntity.ok(alunosList);
    }
}
