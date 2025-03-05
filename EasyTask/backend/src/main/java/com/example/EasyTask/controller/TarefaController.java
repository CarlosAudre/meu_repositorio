package com.example.EasyTask.controller;

import com.example.EasyTask.models.Tarefa;
import com.example.EasyTask.models.TarefaDTO;
import com.example.EasyTask.service.TarefaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefa")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class TarefaController {
    @Autowired
    TarefaService tarefaService;


    @PostMapping
    @Transactional
    public void armazenarDados(@RequestBody TarefaDTO dados){
        tarefaService.armazenarAnotacao(dados);

    }

    @GetMapping()
    public List<Tarefa> retornarDados(){
        return tarefaService.listarTarefas();
    }

    @PutMapping
    @Transactional
    public void atualizarDados(@RequestBody TarefaDTO dados){
        tarefaService.AtualizarAnotacao(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletarTarefa(@PathVariable Long id){
        tarefaService.deletarTarefa(id);
    }


}
