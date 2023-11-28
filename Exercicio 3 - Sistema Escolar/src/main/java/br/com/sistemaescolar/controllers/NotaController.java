package br.com.sistemaescolar.controllers;

import br.com.sistemaescolar.domain.nota.Nota;
import br.com.sistemaescolar.domain.nota.NotaRequestDTO;
import br.com.sistemaescolar.domain.nota.NotaResponseDTO;
import br.com.sistemaescolar.repositories.NotaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("nota")
public class NotaController {

    @Autowired
    NotaRepository repository;

    @PostMapping
    public ResponseEntity postNota(@RequestBody @Valid NotaRequestDTO body){
        Nota newNota = new Nota(body);

        this.repository.save(newNota);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity getTodasNotas() {

        List<NotaResponseDTO> notasList;
        notasList = this.repository.findAll().stream().map(NotaResponseDTO::new).toList();
        return ResponseEntity.ok(notasList);
    }

    @GetMapping
    public ResponseEntity getNota(@RequestParam(name = "disciplinaid") String disciplinaid) {
        List<NotaResponseDTO> notasList;

        if (disciplinaid != null) {
            notasList = this.repository.findByFiltroId(disciplinaid).stream().map(NotaResponseDTO::new).toList();
        } else {
            notasList = this.repository.findAll().stream().map(NotaResponseDTO::new).toList();
        }

        return ResponseEntity.ok(notasList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNota(@PathVariable String id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
