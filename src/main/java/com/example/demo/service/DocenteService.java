package com.example.demo.service;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.dto.DocenteFormDTO;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<DocenteDTO> getAllDocenti() {
        return docenteRepository.findAll().stream()
                .map(docente -> modelMapper.map(docente, DocenteDTO.class))
                .collect(Collectors.toList());
    }

    public DocenteFormDTO getDocenteFormById(Long id) {
        Docente docente = docenteRepository.findById(id).orElse(null);
        return modelMapper.map(docente, DocenteFormDTO.class);
    }

    public void saveDocente(DocenteFormDTO dto) {
        Docente docente = modelMapper.map(dto, Docente.class);
        docenteRepository.save(docente);
    }

    public void updateDocente(Long id, DocenteFormDTO dto) {
        if (docenteRepository.existsById(id)) {
            Docente docente = modelMapper.map(dto, Docente.class);
            docente.setId(id);
            docenteRepository.save(docente);
        }
    }

    public void deleteDocente(Long id) {
        docenteRepository.deleteById(id);
    }
}
