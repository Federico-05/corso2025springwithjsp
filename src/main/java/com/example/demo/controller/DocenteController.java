package com.example.demo.controller;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.dto.DocenteFormDTO;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping("/lista")
    public List<DocenteDTO> list(Model model) {
        return docenteService.getAllDocenti();
    }

    @GetMapping("/nuovo")
    public DocenteFormDTO showAdd(Model model) {
        return new DocenteFormDTO();
    }

    @PostMapping("/nuovo")
    public void create(@RequestBody DocenteFormDTO docenteDTO) {
        docenteService.saveDocente(docenteDTO);
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
