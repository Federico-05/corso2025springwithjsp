
package com.example.demo.repository;



import com.example.demo.data.entity.Discente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiscenteRepository extends JpaRepository<Discente, Long> {
    @Query("SELECT d.nome, d.cognome, d.eta, d.cittaResidenza FROM Discente d ORDER BY d.id ")
    List<Discente> findAllByOrderByIdAsc();

    @Query("SELECT d FROM Discente d ORDER BY d.nome ASC")
    List<Discente> findAllOrderByNomeAsc();

    @Query("SELECT d FROM Discente d ORDER BY d.nome  DESC")
    List<Discente> findAllOrderByNomeDesc();

    Optional<Discente> findByNomeAndCognome(String nome, String cognome);


}
