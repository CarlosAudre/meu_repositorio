package com.example.login.controller;

import com.example.login.model.Usuario;
import com.example.login.model.UsuarioDTO;
import com.example.login.service.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class UsuarioController {
    @Autowired
    private UsuarioServico servico;

    @PostMapping("/registrar")
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioDTO usuarioDTO) {
        return servico.cadastrarUsuario(usuarioDTO.nome(), usuarioDTO.senha());
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, String>> fazerLogin(@RequestBody Usuario usuario) {
        return servico.autenticarUsuario(usuario.getNome(), usuario.getSenha());
    }

}
