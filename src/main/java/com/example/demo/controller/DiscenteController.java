package com.example.demo.controller;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DiscenteFormDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.dto.DocenteFormDTO;
import com.example.demo.data.entity.Discente;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discenti")
public class DiscenteController {

    @Autowired
    private DiscenteService discenteService;

    @GetMapping("/lista")
    public List<DiscenteDTO> list(Model model) {
        return discenteService.getAllDiscenti();
    }

    @GetMapping("/nuovo")
    public DiscenteFormDTO showAdd(Model model) {
        return new DiscenteFormDTO();
    }

    @PostMapping("/nuovo")
    public void create(@RequestBody DiscenteFormDTO discenteDTO) {
        discenteService.saveDiscente(discenteDTO);
    }


    @PutMapping("/{id}/edit")
    public ResponseEntity<DiscenteDTO> updateDiscente(@PathVariable Long id, @RequestBody DiscenteFormDTO discenteFormDTO) {
        DiscenteDTO updateDiscente = discenteService.updateDiscente(id, discenteFormDTO);
        return ResponseEntity.ok(updateDiscente);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        discenteService.deleteDiscente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/asc")
    public String ordinaPerNomeAsc(Model model) {
        List<Discente> discentiOrdinati = discenteService.ordinaPerNomeAsc();
        model.addAttribute("discenti", discentiOrdinati);
        return "list-discenti";
    }

    @GetMapping("/desc")
    public String ordinaPerNomeDesc(Model model) {
        List<Discente> discentiOrdinati = discenteService.ordinaPerNomeDesc();
        model.addAttribute("discenti", discentiOrdinati);
        return "list-discenti";
    }
}
