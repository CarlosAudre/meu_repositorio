package com.example.musicas_play_list;

import com.example.musicas_play_list.principal.Principal;
import com.example.musicas_play_list.repository.ArtistaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicasPlayListApplication implements CommandLineRunner {
	@Autowired
	private ArtistaRepositorio repositorio;

	public static void main(String[] args) {
		SpringApplication.run(MusicasPlayListApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.menu();
	}
}
