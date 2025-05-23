package com.example.demo.controller;

import com.example.demo.converter.Converter;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Docente;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    DocenteService docenteService;
    @Autowired
    Converter converter;

    @GetMapping("/lista")
    public String list(Model model, @RequestParam(name = "filter", required = false) String filter) {
        List<Docente> docenti = new ArrayList<>();
        if ("asc".equalsIgnoreCase(filter)) {
            docenti = docenteService.ord_nome_asc();
        } else if ("desc".equalsIgnoreCase(filter)) {
            docenti = docenteService.ord_nome_desc();
        } else {
            docenti = docenteService.findAll();
        }

        model.addAttribute("docenti", converter.docente_convertiti(docenti));
        return "list-docenti";
    }

    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("docente", new Docente());
        return "form-docente";
    }

    @PostMapping
    public String create(@ModelAttribute("docente") Docente docente,
                         BindingResult br) {
        if (br.hasErrors()) return "form-docente";
        docenteService.save(docente);
        return "redirect:/docenti/lista";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("docente", docenteService.get(id));
        return "form-docente";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("docente") Docente docente,
                         BindingResult br) {
        if (br.hasErrors()) return "form-docente";
        docente.setId(id);
        docenteService.save(docente);
        return "redirect:/docenti/lista";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        docenteService.delete(id);
        return "redirect:/docenti/lista";
    }







}