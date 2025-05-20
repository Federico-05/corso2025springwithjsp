package com.example.demo.data.dto;

import com.example.demo.data.entity.Discente;

import java.util.List;
import java.util.stream.Collectors;

public class DiscenteFormDTO {
    private Long id;
    private String nome;
    private Integer eta;
    private String cognome;
    private Integer matricola;
    private String cittaResidenza;
    private List<CorsoDTO> corsi;

    public DiscenteFormDTO() {}

    public DiscenteFormDTO(Discente discente) {
        this.id = discente.getId();
        this.nome = discente.getNome();
        this.eta =discente.getEta();
        this.cognome = discente.getCognome();
        this.matricola = discente.getMatricola();
        this.cittaResidenza = discente.getCittaResidenza();
        if (discente.getCorsi() != null) {
            this.corsi = discente.getCorsi().stream()
                    .map(CorsoDTO::new)
                    .collect(Collectors.toList());
        }
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getEta() { return eta; }
    public void setEta(Integer eta) { this.eta = eta; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public Integer getMatricola() { return matricola; }
    public void setMatricola(Integer matricola) { this.matricola = matricola; }

    public String getCittaResidenza() { return cittaResidenza; }
    public void setCittaResidenza(String cittaResidenza) { this.cittaResidenza = cittaResidenza; }

    public List<CorsoDTO> getCorsi() { return corsi; }
    public void setCorsi(List<CorsoDTO> corsi) { this.corsi = corsi; }
}
