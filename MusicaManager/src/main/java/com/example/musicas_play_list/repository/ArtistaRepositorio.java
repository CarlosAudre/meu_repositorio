package com.example.musicas_play_list.repository;
import com.example.musicas_play_list.model.Artista;
import com.example.musicas_play_list.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistaRepositorio extends JpaRepository<Artista, Long> {

    Optional<Artista> findByNomeIgnoreCase(String nome);

    @Query("Select m from Artista a join a.musicas m where a.nome ILIKE %:nome")
    List<Musica> acharMusicas(String nome);



}