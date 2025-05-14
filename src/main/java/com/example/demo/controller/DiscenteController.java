package com.example.demo.controller;

import com.example.demo.converter.Converter;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/discenti")
public class DiscenteController {

    @Autowired
    private DiscenteService discenteService;

    @Autowired
    private CorsoService corsoService;

    @Autowired
    Converter converter;

    @GetMapping("/lista")
    public String list(Model model, @RequestParam(name = "filter", required = false) String filter) {
        List<Discente> discenti;
        if ("asc".equalsIgnoreCase(filter)) {
            discenti = discenteService.ord_nome_asc();
        } else if ("desc".equalsIgnoreCase(filter)) {
            discenti = discenteService.ord_nome_desc();
        } else {
            discenti = discenteService.findAll();
        }
        model.addAttribute("discenti", converter.discente_convertiti(discenti));
        return "list-discenti";
    }

    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("discente", new Discente());
        model.addAttribute("corsi", corsoService.findAll());
        return "form-discente";
    }

    @PostMapping("/salva")
    public String create(@ModelAttribute("discente") Discente discente,
                         @RequestParam("corsoId") Long corsoId,
                         BindingResult br) {
        if (br.hasErrors()) {
            return "form-discente";
        }
        Corso corso = corsoService.get(corsoId);
        discente.setCorsi(List.of(corso));
        discenteService.save(discente);
        return "redirect:/discenti/lista";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Discente discente = discenteService.get(id);
        model.addAttribute("discente", discente);
        model.addAttribute("corsi", corsoService.findAll());
        return "form-discente";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("discente") Discente discente,
                         @RequestParam("corsoId") Long corsoId,
                         BindingResult br) {
        if (br.hasErrors()) {
            return "form-discente";
        }
        discente.setId(id);
        Corso corso = corsoService.get(corsoId);
        discente.setCorsi(List.of(corso));
        discenteService.save(discente);
        return "redirect:/discenti/lista";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        discenteService.delete(id);
        return "redirect:/discenti/lista";
    }
}
