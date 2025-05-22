package com.example.demo.controller;

import com.example.demo.data.dto.*;
import com.example.demo.data.entity.Corso;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DiscenteService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    private CorsoService corsoService;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private DiscenteService discenteService;


    @GetMapping("/lista")
    public List<CorsoDTO> list(Model model) {
        return corsoService.getAllCorsiDTO();
    }

    @GetMapping("/nuovo")
    public CorsoFormDTO showAdd(Model model) {
        return new CorsoFormDTO();
    }

    @PostMapping("/nuovo")
    public void create(@RequestBody CorsoFormDTO corsoDTO) {
        corsoService.saveCorso(corsoDTO);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<CorsoDTO> updateCorso(@PathVariable Long id, @RequestBody CorsoFormDTO CorsoFormDTO) {
        CorsoDTO updateCorso = corsoService.updateCorso(id, CorsoFormDTO);
        return ResponseEntity.ok(updateCorso);
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        corsoService.deleteCorso(id);
        return "redirect:/corsi/lista";
    }
}