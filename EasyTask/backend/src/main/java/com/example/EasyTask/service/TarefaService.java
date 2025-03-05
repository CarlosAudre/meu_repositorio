package com.example.EasyTask.service;

import com.example.EasyTask.models.Tarefa;
import com.example.EasyTask.models.TarefaDTO;
import com.example.EasyTask.repository.TarefaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class TarefaService {
    @Autowired
    TarefaRepository tarefaRepository;

    public void armazenarAnotacao(TarefaDTO dados){
        tarefaRepository.save(new Tarefa(dados));
    }

    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public void AtualizarAnotacao(TarefaDTO dados) {
        if (dados.id() == null) {
            throw new IllegalArgumentException("ID da tarefa não pode ser nulo para atualização.");
        }

        Optional<Tarefa> anotacaoResgatada = tarefaRepository.findById(dados.id());
        if (anotacaoResgatada.isPresent()) {
            Tarefa tarefa = anotacaoResgatada.get();
            tarefa.atualizarInformacoes(dados);
            tarefaRepository.save(tarefa);
        } else {
            throw new RuntimeException("Tarefa com ID " + dados.id() + " não encontrada.");
        }
    }


    public void deletarTarefa(Long id) {
        System.out.println("Deletando tarefa com id: " + id);
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isPresent()) {
            tarefaRepository.delete(tarefa.get()); // Remove a tarefa do banco
        } else {
            throw new RuntimeException("Tarefa com ID " + id + " não encontrada.");
        }
    }


}

