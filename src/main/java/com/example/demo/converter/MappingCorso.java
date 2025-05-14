package com.example.demo.converter;

import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.entity.Corso;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MappingCorso{
    CorsoDTO toDto(Corso corso);
    Corso toEntity(CorsoDTO corsoDTO);
}
