package com.example.demo.controller;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DiscenteFormDTO;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discenti")
public class DiscenteController {

    @Autowired
    private DiscenteService discenteService;

    @GetMapping("/search-or-create")
    public ResponseEntity<DiscenteDTO> searchOrCreate(@RequestParam String nome, @RequestParam String cognome) {
        DiscenteDTO result = discenteService.searchOrCreate(nome, cognome);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/lista")
    public ResponseEntity<List<DiscenteDTO>> getAllDiscenti() {
        List<DiscenteDTO> lista = discenteService.getAllDiscenti();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscenteFormDTO> getDiscente(@PathVariable Long id) {
        DiscenteFormDTO discente = discenteService.getDiscenteFormById(id);
        return discente != null ? ResponseEntity.ok(discente) : ResponseEntity.notFound().build();
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<DiscenteDTO>> getOrCreateDiscenti(@RequestBody List<DiscenteDTO> discenti) {
        List<DiscenteDTO> risultati = discenteService.getOrCreateDiscenti(discenti);
        return ResponseEntity.ok(risultati);
    }

    @PostMapping("/nuovo")
    public ResponseEntity<DiscenteDTO> create(@RequestBody DiscenteFormDTO discenteFormDTO) {
        DiscenteDTO saved = discenteService.saveDiscente(discenteFormDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DiscenteDTO> updateDiscente(@PathVariable Long id, @RequestBody DiscenteFormDTO discenteFormDTO) {
        DiscenteDTO updated = discenteService.updateDiscente(id, discenteFormDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        discenteService.deleteDiscente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cerca")
    public ResponseEntity<DiscenteDTO> cercaPerNomeECognome(@RequestParam String nome, @RequestParam String cognome) {
        DiscenteDTO result = discenteService.cercaPerNomeECognome(nome, cognome);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }
}
