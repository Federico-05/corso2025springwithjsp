package com.example.demo.service;

import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService {


    @Autowired
    DocenteRepository docenteRepository;

    public List<Docente> findAll() {
        return docenteRepository.findAll();
    }

    public Docente get(Long id) {
        return docenteRepository.findById(id).orElseThrow();
    }

    public Docente save(Docente d) {
        return docenteRepository.save(d);
    }

    public void delete(Long id) {
        docenteRepository.deleteById(id);
    }

    public List<Docente> ord_nome_asc(){
        return docenteRepository.ord_nome_asc();
    }
    public List<Docente> ord_nome_desc() {
        return docenteRepository.ord_nome_desc();
    }
}