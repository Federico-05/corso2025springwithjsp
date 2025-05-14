package com.example.demo.repository;


import com.example.demo.data.entity.Discente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscenteRepository extends JpaRepository<Discente, Long> {
    @Query( value = "SELECT * FROM discenti ORDER BY nome asc", nativeQuery = true  )
    List<Discente> ord_nome_asc();
    @Query( value = "SELECT * FROM discenti ORDER BY nome desc", nativeQuery = true  )
    List<Discente> ord_nome_desc();
}
