package com.example.demo.controller;

import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.dto.CorsoFormDTO;
import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DiscenteService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    private CorsoService corsoService;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private DiscenteService discenteService;

    // Mostra lista corsi (usa DTO)
    @GetMapping("/lista")
    public String listaCorsi(Model model) {
        List<CorsoDTO> corsi = corsoService.getAllCorsiDTO();

        Map<Long, DocenteDTO> docentiMap = docenteService.getAllDocenti().stream()
                .collect(Collectors.toMap(DocenteDTO::getId, d -> d));
        Map<Long, DiscenteDTO> discentiMap = discenteService.getAllDiscenti().stream()
                .collect(Collectors.toMap(DiscenteDTO::getId, d -> d));

        model.addAttribute("corsi", corsi);
        model.addAttribute("docentiMap", docentiMap);
        model.addAttribute("discentiMap", discentiMap);

        return "list-corsi";
    }

    // Form nuovo corso
    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("corso", new CorsoFormDTO());
        model.addAttribute("docenti", docenteService.getAllDocenti());
        model.addAttribute("discenti", discenteService.getAllDiscenti());
        return "form-corso";
    }

    // Salva nuovo corso
    @PostMapping("/salva")
    public String salvaNuovo(@ModelAttribute("corso") CorsoFormDTO corsoDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("docenti", docenteService.getAllDocenti());
            model.addAttribute("discenti", discenteService.getAllDiscenti());
            return "form-corso";
        }
        corsoService.saveCorso(corsoDTO);
        return "redirect:/corsi/lista";
    }

    // Form modifica corso
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        CorsoFormDTO corsoDTO = corsoService.getCorsoById(id);
        if (corsoDTO == null) {
            return "redirect:/corsi/lista";
        }
        model.addAttribute("corso", corsoDTO);
        model.addAttribute("docenti", docenteService.getAllDocenti());
        model.addAttribute("discenti", discenteService.getAllDiscenti());
        return "form-corso";
    }

    // Salva aggiornamento corso
    @PostMapping("/{id}/salva")
    public String updateCorso(@PathVariable Long id, @ModelAttribute("corso") CorsoFormDTO corsoDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("docenti", docenteService.getAllDocenti());
            model.addAttribute("discenti", discenteService.getAllDiscenti());
            return "form-corso";
        }
        corsoService.updateCorso(id, corsoDTO);
        return "redirect:/corsi/lista";
    }

    // Elimina corso
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        corsoService.deleteCorso(id);
        return "redirect:/corsi/lista";
    }
}