package com.example.frases_marcantes.repository;

import com.example.frases_marcantes.model.Frase;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Repository extends JpaRepository<Frase, Id> {
    @Query(("SELECT f FROM Frase f order by function('RANDOM') LIMIT 1"))
    Frase findRandomFrase();
}
