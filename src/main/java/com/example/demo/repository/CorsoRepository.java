package com.example.demo.repository;



import com.example.demo.entity.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CorsoRepository extends JpaRepository<Corso, Long> {
    @Query( value = "SELECT * FROM corso ORDER BY nome asc", nativeQuery = true  )
    List<Corso> ord_nome_asc();
    @Query( value = "SELECT * FROM corso ORDER BY nome desc", nativeQuery = true  )
    List<Corso> ord_nome_desc();
}
