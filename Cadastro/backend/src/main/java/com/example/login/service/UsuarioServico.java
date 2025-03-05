package com.example.login.service;

import com.example.login.model.Usuario;
import com.example.login.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioServico {
    @Autowired
    private UsuarioRepositorio repositorio;

    @Autowired private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<?> cadastrarUsuario(String nome, String senha) { //ResponseEntity é um tipo de retorno usado para representar a resposta HTTP, o <?> significa que o tipo de resposta é genérico, ou seja, pode ser uma string, int, json, etc...
        if (repositorio.findByNomeIgnoreCase(nome).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST) //Aqui retorna um stats de bad_request, ou seja, algo de errado
                    .body(Map.of("message", "Nome de usuário já cadastrado")); //.body(map.of("mensage", "usuário já cadastrado")). Essa é a resposta que aparece no corpo quando um nome de usuário já foi cadastrado. Essa resposta é definida por uma map<String, String>, sendo respectivamente a chave e a resposta
        }
        if (nome.length() < 5){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "O nome do usuário deve ter pelo menos 5 caracteres"));
        }

        if (senha.length() < 8){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "A senha deve ter pelo menos 8 caracteres"));
        }

        String senhaCriptografada = passwordEncoder.encode(senha);
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setSenha(senhaCriptografada);

        repositorio.save(novoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED) //Retorna um HttpRequest informado que a conta foi criada
                .body(Map.of("message", "Usuário cadastrado com sucesso")); // mapeia novamente com um map<String String>, respectivamente, chave e mensagem
    }


    public ResponseEntity<Map<String, String>> autenticarUsuario(String nome, String senha) { // Eu também poderia deixar como ResponseEntety<?>, mas por segurança, é bom deixar <map<String,String>>
        nome = nome.trim(); //Remove espaços antes e depois do nome do usuário
        Optional<Usuario> usuarioPresente = repositorio.findByNomeIgnoreCase(nome);

        if (usuarioPresente.isEmpty()) {
            return criarResposta(HttpStatus.UNAUTHORIZED, "Usuário não encontrado.");
        }

        Usuario usuarioEncontrado = usuarioPresente.get();

        if (!passwordEncoder.matches(senha, usuarioEncontrado.getSenha())) { //Verifica se a senha do usuário fornecida corresponde a criptografada no banco de dados
            return criarResposta(HttpStatus.UNAUTHORIZED, "Senha incorreta.");
        }

        return criarResposta(HttpStatus.OK, "Login bem-sucedido!");
    }

    private ResponseEntity<Map<String, String>> criarResposta(HttpStatus status, String mensagem) {
        Map<String, String> resposta = new HashMap<>(); // HashMap é uma estrutura de dados que armazena pares chave-valor
        resposta.put("message", mensagem);
        return ResponseEntity.status(status).body(resposta);

        /*Ex de HashMap
        * Map<String,String> mapa = new HashMap<>()
        * Se eu fizer um mapa.put("carro", "autumóvel de 4 rodas"), sendo a chave e a frase armazenada, respectivamente
        * e depois usar um mapa.get("carro"), ele irá me retornar : "automóvel de 4 rodas" */

    }
}



