
package com.example.demo.data.dto;

import java.util.List;

public class DiscenteDTO {

    private String nome;
    private String cognome;
    private Integer eta;
    private String cittaResidenza;

    /* Costruttori */
    public DiscenteDTO() {}

    public DiscenteDTO( String nome, String cognome, Integer eta, String cittaResidenza) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.cittaResidenza = cittaResidenza;
    }

    /* Getters e Setters */

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

    public Integer getEta() {
        return eta;
    }

    public void setEta(Integer eta) {
        this.eta = eta;
    }

    public String getCittaResidenza() {
        return cittaResidenza;
    }

    public void setCittaResidenza(String cittaResidenza) {
        this.cittaResidenza = cittaResidenza;
    }


    @Override
    public String toString() {
        return "DiscenteDTO{" +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", eta=" + eta +
                ", cittaResidenza='" + cittaResidenza + '\'' +
                '}';
    }
}
