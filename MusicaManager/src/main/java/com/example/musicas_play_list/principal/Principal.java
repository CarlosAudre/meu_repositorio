package com.example.musicas_play_list.principal;


import com.example.musicas_play_list.model.Artista;
import com.example.musicas_play_list.model.ArtistaTipo;
import com.example.musicas_play_list.model.Musica;
import com.example.musicas_play_list.repository.ArtistaRepositorio;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private final ArtistaRepositorio repositorio;

    public Principal(ArtistaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void menu(){
        Scanner myScannner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("*** Bem vindo a sua playlist de músicas ***");
            System.out.println("Selecione uma opção abaixo:");
            System.out.println("""
                1- Registrar artista
                2- Registrar música
                3- Listar músicas de um artista
                4- Listar todas as músicas por artista
                0- Fechar
                """);
            String opcao = myScannner.nextLine();
            switch (opcao){
                case "1":
                    registrarArtista();
                    break;
                case "2":
                    registrarMusica();
                    break;
                case "3":
                    listarMusicasDeUmArtista();
                    break;
                case "4":
                    ListarTodasAsMusicasPorArtista();
                    break;
                case "0":
                    System.out.println("Encerrando o sistema...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private void registrarArtista() {
        Scanner myScanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("Digite o nome do artista");
            String nomeArtista = myScanner.nextLine();
            System.out.println("Digite o tipo desse artista(Solo, dupla ou banda)");

            try {
                String tipoArtista = myScanner.nextLine();
                ArtistaTipo tipoArtistaConvertido = ArtistaTipo.valueOf(tipoArtista.toUpperCase());
                Artista novoArtista = new Artista(nomeArtista, tipoArtistaConvertido);
                repositorio.save(novoArtista);
                System.out.println("Artista cadastrado com sucesso!");
            } catch (IllegalArgumentException e){
                System.out.println("Tipo de artista inválido");
            }
            System.out.println("Deseja cadastrar um outro artista?");
            System.out.println("""
                1- Sim
                2- Não
                """);
            String opcao = myScanner.nextLine();
            switch (opcao){
                case "1":
                    break;
                case "2":
                    loop = false;
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }


    }

    private void registrarMusica() {
        Scanner myScanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("Digite o nome da música que deseja cadastrar");
            String musicaNome = myScanner.nextLine();
            System.out.println("Essa música será cadastrada em que artista?");
            String artistaNome = myScanner.nextLine();
            Optional<Artista> artistaEncontrado = repositorio.findByNomeIgnoreCase(artistaNome);
            if (artistaEncontrado.isEmpty()){
                System.out.println("Artista não encontrado");
            }
            else {
                Musica musica = new Musica(musicaNome);
                musica.setArtista(artistaEncontrado.get());
                artistaEncontrado.get().getMusicas().add(musica);
                repositorio.save(artistaEncontrado.get());
            }

            System.out.println("Deseja cadastrar outra música?");
            System.out.println("""
                1- Sim
                2- Não
                """);
            String opcao = myScanner.nextLine();
            switch (opcao){
                case "1":
                    break;
                case "2":
                    loop = false;
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }

        }


    }

    private void listarMusicasDeUmArtista() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Digite o nome do artista");
        String nomeArtista = myScanner.nextLine();
        Optional<Artista> artistaEscontrado = repositorio.findByNomeIgnoreCase(nomeArtista);
        if (artistaEscontrado.isEmpty()) {
            System.out.println("Artista não encontrado");
        } else {
            List<Musica> musicas = repositorio.acharMusicas(artistaEscontrado.get().getNome());
            musicas.forEach(m -> System.out.println(m.toString()));
        }

    }

    private void ListarTodasAsMusicasPorArtista() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(a -> System.out.println(a.toString()));
    }


}
