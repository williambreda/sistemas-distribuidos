package br.com.sistemaescolar.controllers;

import br.com.sistemaescolar.domain.disciplina.Disciplina;
import br.com.sistemaescolar.domain.disciplina.DisciplinaRequestDTO;
import br.com.sistemaescolar.domain.disciplina.DisciplinaResponseDTO;
import br.com.sistemaescolar.repositories.DisciplinaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("disciplina")
public class DisciplinaController {

    @Autowired
    DisciplinaRepository repository;

    @PostMapping
    public ResponseEntity postDisciplina(@RequestBody @Valid DisciplinaRequestDTO body){
        Disciplina newDisciplina = new Disciplina(body);

        this.repository.save(newDisciplina);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getTodasDisciplinas(){
        List<DisciplinaResponseDTO> disciplinaList = this.repository.findAll().stream().map(DisciplinaResponseDTO::new).toList();

        return ResponseEntity.ok(disciplinaList);
    }
}
