package com.example.demo.controller;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.dto.DocenteFormDTO;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping("/lista")
    public String list(Model model) {
        List<DocenteDTO> docenti = docenteService.getAllDocenti();
        model.addAttribute("docenti", docenti);
        return "list-docenti";
    }

    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("docente", new DocenteFormDTO());
        return "form-docente";
    }

    @PostMapping("/nuovo")
    public String create(@ModelAttribute("docente") DocenteFormDTO docenteDTO, BindingResult result) {
        docenteService.saveDocente(docenteDTO);
        return "redirect:/docenti/lista";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        DocenteFormDTO docenteDTO = docenteService.getDocenteFormById(id);
        model.addAttribute("docente", docenteDTO);
        return "form-docente";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute("docente") DocenteFormDTO docenteDTO, BindingResult result) {
        docenteService.updateDocente(id, docenteDTO);
        return "redirect:/docenti/lista";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        docenteService.deleteDocente(id);
        return "redirect:/docenti/lista";
    }
}
