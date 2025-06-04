package com.example.demo.controller;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.dto.DocenteFormDTO;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping("/{id}")
    public ResponseEntity<DocenteFormDTO> getById(@PathVariable Long id) {
        DocenteFormDTO docente = docenteService.getDocenteFormById(id);
        if (docente != null) {
            return ResponseEntity.ok(docente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/nuovo")
    public ResponseEntity<DocenteDTO> create(@RequestBody DocenteFormDTO docenteDTO) {
        DocenteDTO saved = docenteService.saveDocente(docenteDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DocenteDTO> updateDocente(@PathVariable Long id, @RequestBody DocenteFormDTO docenteFormDTO) {
        DocenteDTO updateDocente = docenteService.updateDocente(id, docenteFormDTO);
        return ResponseEntity.ok(updateDocente);
    }



    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        docenteService.deleteDocente(id);
        return ResponseEntity.noContent().build();
    }
}
