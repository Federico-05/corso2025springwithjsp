
package com.example.demo.repository;



import com.example.demo.data.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
    Optional<Docente> findByNomeAndCognome(String nome, String cognome);
}
