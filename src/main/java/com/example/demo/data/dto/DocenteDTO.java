
package com.example.demo.data.dto;

import com.example.demo.data.entity.Docente;

public class DocenteDTO {
    private String nome;
    private String cognome;

    public DocenteDTO() {
    }
    public DocenteDTO(Docente docente) {

        this.nome = docente.getNome();
        this.cognome = docente.getCognome();
    }

    public DocenteDTO(String nome, String cognome) {

        this.nome = nome;
        this.cognome = cognome;
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

}
