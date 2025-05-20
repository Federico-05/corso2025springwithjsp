package com.example.demo.service;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DiscenteFormDTO;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscenteService {

    @Autowired
    private DiscenteRepository discenteRepository;

    @Autowired
    private ModelMapper modelMapper;

    private DiscenteDTO convertToDTO(Discente discente) {
        return modelMapper.map(discente, DiscenteDTO.class);
    }

    public DiscenteFormDTO getDiscenteFormById(Long id) {
        Optional<Discente> discente = discenteRepository.findById(id);
        return discente.map(d -> modelMapper.map(d, DiscenteFormDTO.class)).orElse(null);
    }

    public void saveDiscente(DiscenteFormDTO formDTO) {
        Discente discente = modelMapper.map(formDTO, Discente.class);
        discenteRepository.save(discente);
    }

    public void updateDiscente(Long id, DiscenteFormDTO formDTO) {
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discente non trovato con ID: " + id));

        discente.setNome(formDTO.getNome());
        discente.setCognome(formDTO.getCognome());
        discente.setMatricola(formDTO.getMatricola());
        discente.setEta(formDTO.getEta());
        discente.setCittaResidenza(formDTO.getCittaResidenza());

        discenteRepository.save(discente);
    }

    public void deleteDiscente(Long id) {
        if (!discenteRepository.existsById(id)) {
            throw new IllegalArgumentException("Discente non trovato con ID: " + id);
        }
        discenteRepository.deleteById(id);
    }

    public List<DiscenteDTO> getAllDiscenti() {
        return discenteRepository.findAll(Sort.by("id"))
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<Discente> ordinaPerNomeAsc() {
        return discenteRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    public List<Discente> ordinaPerNomeDesc() {
        return discenteRepository.findAll(Sort.by(Sort.Direction.DESC, "nome"));
    }

}
