package com.example.demo.converter;

import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public class Converter {
    @Autowired
    private MappingCorso mappingCorso;
    public List<DocenteDTO> docente_convertiti(List<Docente> input) {
        List<DocenteDTO> convertiti = new ArrayList<>();
        for (int i=0; i < input.size(); i++){
            DocenteDTO docDTO = new DocenteDTO();
            docDTO.setId(input.get(i).getId());
            docDTO.setNome(input.get(i).getNome());
            docDTO.setCognome(input.get(i).getCognome());
            convertiti.add(docDTO);
        }
        return convertiti;

    }

    public List<DiscenteDTO> discente_convertiti(List<Discente> input) {
        List<DiscenteDTO> convertiti = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            DiscenteDTO disDTO = new DiscenteDTO();
            disDTO.setId(input.get(i).getId());
            disDTO.setNome(input.get(i).getNome());
            disDTO.setCognome(input.get(i).getCognome());
            disDTO.setEta(input.get(i).getEta());
            disDTO.setCitta_residenza(input.get(i).getCitta_residenza());
            disDTO.setCorsi(input.get(i).getCorsi());
            convertiti.add(disDTO);
        }
        return convertiti;
    }
    public CorsoDTO corso_convertiti(Corso corso) {
        return mappingCorso.toDto(corso);
    }

    public List<CorsoDTO> corso_convertiti(List<Corso> corsi) {
        List<CorsoDTO> corsiDTO = new ArrayList<>();
        for (Corso corso : corsi) {
            corsiDTO.add(mappingCorso.toDto(corso));
        }
        return corsiDTO;
    }
}
