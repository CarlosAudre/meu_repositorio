package com.example.musicas_play_list.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artista")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String nome;
    @Enumerated(EnumType.STRING)
    private ArtistaTipo tipo;
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch =  FetchType.EAGER)
    private List<Musica> musicas;

    public Artista(){};

    public Artista(String nome, ArtistaTipo tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArtistaTipo getTipo() {
        return tipo;
    }

    public void setTipo(ArtistaTipo tipo) {
        this.tipo = tipo;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public String toString() {
        return musicas.toString();

    }
}
