package com.example.demo.data.dto;

import com.example.demo.data.entity.Corso;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class DiscenteDTO {

    private Long id;

    private String nome;

    private String cognome;

    private int eta;

    private String citta_residenza;

    private List<Corso> corsi = new ArrayList<>();

    public DiscenteDTO() {}

    public DiscenteDTO(String nome, String cognome, int eta, String citta_residenza) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.citta_residenza = citta_residenza;
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getCitta_residenza() {
        return citta_residenza;
    }

    public void setCitta_residenza(String citta_residenza) {
        this.citta_residenza = citta_residenza;
    }

    public List<Corso> getCorsi() {
        return corsi;
    }

    public void setCorsi(List<Corso> corsi) {
        this.corsi = corsi;
    }
}
