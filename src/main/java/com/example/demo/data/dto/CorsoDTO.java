package com.example.demo.data.dto;

import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


public class CorsoDTO {
    private Long id;

    private String nome;

    private Docente docente;

    private List<Discente> discenti = new ArrayList<>();

    public List<Discente> getDiscenti() {
        return discenti;
    }

    public void setDiscenti(List<Discente> discenti) {
        this.discenti = discenti;
    }

    public CorsoDTO() {
    }

    public CorsoDTO(String nome, Docente docente) {
        this.nome = nome;
        this.docente = docente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
}