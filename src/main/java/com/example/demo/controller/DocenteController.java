package com.example.demo.controller;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.dto.DocenteFormDTO;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private DiscenteRepository DiscenteRepository;



    @GetMapping("/lista")
    public ResponseEntity<List<DocenteDTO>> getAllDocenti() {
        List<DocenteDTO> lista = docenteService.getAllDocenti();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteFormDTO> getDocente(@PathVariable Long id) {
        DocenteFormDTO docente = docenteService.getDocenteFormById(id);
        return docente != null ? ResponseEntity.ok(docente) : ResponseEntity.notFound().build();
    }



    @PostMapping("/nuovo")
    public ResponseEntity<DocenteDTO> create(@RequestBody DocenteFormDTO docenteFormDTO) {
        DocenteDTO saved = docenteService.saveDocente(docenteFormDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DocenteDTO> updateDocente(@PathVariable Long id, @RequestBody DocenteFormDTO docenteFormDTO) {
        DocenteDTO updateDocente = docenteService.updateDocente(id, docenteFormDTO);
        return updateDocente != null ? ResponseEntity.ok(updateDocente) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        docenteService.deleteDocente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cerca")
    public ResponseEntity<DocenteDTO> cercaPerNomeECognome(@RequestParam String nome, @RequestParam String cognome) {
        DocenteDTO result = docenteService.cercaPerNomeECognome(nome, cognome);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }
}
