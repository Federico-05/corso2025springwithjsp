package com.example.demo.controller;

import com.example.demo.converter.Converter;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Docente;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    private CorsoService corsoService;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private com.example.demo.repository.DiscenteRepository discenteRepository;

    @Autowired
    Converter converter;

    @GetMapping("/lista")
    public String list(Model model, @RequestParam(name = "filter", required = false) String filter) {
        List<Corso> corsi;
        if ("asc".equalsIgnoreCase(filter)) {
            corsi = corsoService.ord_nome_asc();
        }else if ("desc".equalsIgnoreCase(filter)) {
            corsi = corsoService.ord_nome_desc();
        } else {
            corsi = corsoService.findAll();
        }
        model.addAttribute("corsi", converter.corso_convertiti(corsi));
        return "list-corsi";
    }

    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("corso", new Corso());
        model.addAttribute("docenti", docenteService.findAll());
        model.addAttribute("tuttiDiscenti", discenteRepository.findAll());
        return "form-corso";
    }

    @PostMapping("/salva")
    public String create(@ModelAttribute("corso") Corso corso,
                         @RequestParam("id_docente") Long docenteId,
                         @RequestParam(value = "discenteIds", required = false) List<Long> discenteIds,
                         BindingResult br) {
        if (br.hasErrors()) {
            return "form-corso";
        }
        Docente docente = docenteService.get(docenteId);
        corso.setDocente(docente);

        if (discenteIds != null) {
            corso.setDiscenti(discenteRepository.findAllById(discenteIds));
        } else {
            corso.setDiscenti(List.of());
        }

        corsoService.save(corso);
        return "redirect:/corsi/lista";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Corso corso = corsoService.get(id);
        model.addAttribute("corso", corso);
        model.addAttribute("docenti", docenteService.findAll());
        model.addAttribute("tuttiDiscenti", discenteRepository.findAll());
        return "form-corso";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("corso") Corso corso,
                         @RequestParam("id_docente") Long docenteId,
                         @RequestParam(value = "discenteIds", required = false) List<Long> discenteIds,
                         BindingResult br) {
        if (br.hasErrors()) {
            return "form-corso";
        }
        corso.setId(id);
        Docente docente = docenteService.get(docenteId);
        corso.setDocente(docente);

        if (discenteIds != null) {
            corso.setDiscenti(discenteRepository.findAllById(discenteIds));
        } else {
            corso.setDiscenti(List.of());
        }

        corsoService.save(corso);
        return "redirect:/corsi/lista";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        corsoService.delete(id);
        return "redirect:/corsi/lista";
    }
}
