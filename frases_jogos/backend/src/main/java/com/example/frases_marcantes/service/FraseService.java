package com.example.frases_marcantes.service;

import com.example.frases_marcantes.model.Frase;
import com.example.frases_marcantes.model.FraseDTO;
import com.example.frases_marcantes.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FraseService {
    @Autowired
    private Repository repositorio;

    public FraseDTO obterFrase(){
        Frase frase =repositorio.findRandomFrase();
        return new FraseDTO(frase.getTitulo(), frase.getPersonagem(),
                frase.getPost(), frase.getFrase());

    }
}
