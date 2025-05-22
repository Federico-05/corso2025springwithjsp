package com.example.demo.service;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DiscenteFormDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.dto.DocenteFormDTO;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
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

    public DiscenteDTO updateDiscente(Long id, DiscenteFormDTO discenteFormDTO) {
        Discente existingDiscente = discenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discente non trovato"));

        existingDiscente.setNome(discenteFormDTO.getNome());
        existingDiscente.setCognome(discenteFormDTO.getCognome());
        existingDiscente.setEta(discenteFormDTO.getEta());
        existingDiscente.setCittaResidenza(discenteFormDTO.getCittaResidenza());
        existingDiscente.setMatricola(discenteFormDTO.getMatricola());

        Discente updatedDiscente = discenteRepository.save(existingDiscente);

        return modelMapper.map(updatedDiscente, DiscenteDTO.class);
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
