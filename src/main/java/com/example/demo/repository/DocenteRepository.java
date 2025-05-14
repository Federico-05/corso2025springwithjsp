package com.example.demo.repository;



import com.example.demo.data.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
    @Query( value = "SELECT * FROM docenti ORDER BY nome asc", nativeQuery = true  )
    List<Docente> ord_nome_asc();
    @Query( value = "SELECT * FROM docenti ORDER BY nome desc", nativeQuery = true  )
    List<Docente> ord_nome_desc();
}
