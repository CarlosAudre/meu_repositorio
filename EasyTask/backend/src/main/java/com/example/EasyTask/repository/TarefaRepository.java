package com.example.EasyTask.repository;

import com.example.EasyTask.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    Optional<Tarefa> findById(Long Id);
    List<Tarefa> findAll();


}
