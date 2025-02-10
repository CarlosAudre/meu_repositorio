package com.example.frases_marcantes.controller;

import com.example.frases_marcantes.model.FraseDTO;
import com.example.frases_marcantes.service.FraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FraseController {
    @Autowired
    private FraseService servico;

    @GetMapping("/jogos/frases")
    public FraseDTO obterFrase(){
        return servico.obterFrase();
    }
}
