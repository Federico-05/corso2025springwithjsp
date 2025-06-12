package com.example.demo.service;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DiscenteFormDTO;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscenteService {

    @Autowired
    private DiscenteRepository discenteRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Lista tutti i discenti come DTO
    public List<DiscenteDTO> getAllDiscenti() {
        return discenteRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Recupera un singolo discente tramite id come FormDTO
    public DiscenteFormDTO getDiscenteFormById(Long id) {
        return discenteRepository.findById(id)
                .map(discente -> modelMapper.map(discente, DiscenteFormDTO.class))
                .orElse(null);
    }

    // Salva un nuovo discente da FormDTO, ritorna DTO
    public DiscenteDTO saveDiscente(DiscenteFormDTO dto) {
        Discente discente = modelMapper.map(dto, Discente.class);
        Discente saved = discenteRepository.save(discente);
        return toDTO(saved);
    }

    // Aggiorna un discente esistente, ritorna DTO aggiornato
    public DiscenteDTO updateDiscente(Long id, DiscenteFormDTO dto) {
        Discente existing = discenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discente non trovato con id: " + id));

        existing.setNome(dto.getNome());
        existing.setCognome(dto.getCognome());
        existing.setMatricola(dto.getMatricola());
        existing.setEta(dto.getEta());
        existing.setCittaResidenza(dto.getCittaResidenza());

        Discente updated = discenteRepository.save(existing);
        return toDTO(updated);
    }

    // Cerca un discente per nome e cognome, ritorna DTO o null
    public DiscenteDTO cercaPerNomeECognome(String nome, String cognome) {
        Optional<Discente> discente = discenteRepository.findByNomeAndCognome(nome, cognome);
        return discente.map(this::toDTO).orElse(null);
    }

    // Cancella un discente per id
    public void deleteDiscente(Long id) {
        discenteRepository.deleteById(id);
    }

    // Cerca o crea un discente (solo nome e cognome)
    public DiscenteDTO searchOrCreate(String nome, String cognome) {
        DiscenteDTO existing = cercaPerNomeECognome(nome, cognome);
        if (existing != null) {
            return existing;
        }
        DiscenteFormDTO nuovo = new DiscenteFormDTO();
        nuovo.setNome(nome);
        nuovo.setCognome(cognome);
        return saveDiscente(nuovo);
    }

    // Gestisce lista di DTO, cerca o crea ciascun discente e ritorna lista di DTO risultanti
    public List<DiscenteDTO> getOrCreateDiscenti(List<DiscenteDTO> discenti) {
        List<DiscenteDTO> result = new ArrayList<>();
        for (DiscenteDTO d : discenti) {
            DiscenteDTO dto = getOrCreateDiscente(d.getNome(), d.getCognome(), d.getEta(), d.getCittaResidenza());
            if (dto != null) {
                result.add(dto);
            }
        }
        return result;
    }

    // Cerca o crea un singolo discente completo (nome, cognome, eta, città)
    public DiscenteDTO getOrCreateDiscente(String nome, String cognome, Integer eta, String cittaResidenza) {
        // Cerca prima per nome e cognome
        DiscenteDTO existing = cercaPerNomeECognome(nome, cognome);
        if (existing != null) {
            return existing;
        }

        // Se non esiste, crea nuovo DiscenteFormDTO
        DiscenteFormDTO nuovo = new DiscenteFormDTO();
        nuovo.setNome(nome);
        nuovo.setCognome(cognome);
        nuovo.setEta(eta);
        nuovo.setCittaResidenza(cittaResidenza);

        // Salva e ritorna DTO creato
        return saveDiscente(nuovo);
    }

    // Conversione entity -> DTO
    public DiscenteDTO toDTO(Discente entity) {
        if (entity == null) {
            return null;
        }
        DiscenteDTO dto = new DiscenteDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCognome(entity.getCognome());
        dto.setEta(entity.getEta());
        dto.setCittaResidenza(entity.getCittaResidenza());
        return dto;
    }
}
