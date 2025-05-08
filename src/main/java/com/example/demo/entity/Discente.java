package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "discenti")
public class Discente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nome")
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "matricola", nullable = false)
    private int matricola;

    @Column(name = "eta", nullable = false)
    private int eta;

    @Column(name = "CittaResidenza", nullable = false)
    private String cittaResidenza;




    /* costruttori */
    public Discente() {}
    public Discente(String nome, String cognome, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.eta = eta;
        this.cittaResidenza = cittaResidenza;
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

    public int getMatricola(){
        return matricola;
    }
    public void setMatricola(int matricola) {
        this.matricola = matricola;
    }

    public int getEta(){
        return eta;
    }
    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getCittaResidenza() {
        return cittaResidenza;
    }

    public void setCittaResidenza(String cittaResidenza) {
        this.cittaResidenza = cittaResidenza;
    }
}