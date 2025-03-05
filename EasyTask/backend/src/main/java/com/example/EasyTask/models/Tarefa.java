package com.example.EasyTask.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tarefa")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String anotacao;
    private String dia;
    private boolean ativo;
    private boolean grinfar;

    public Tarefa(){}

    public Tarefa(TarefaDTO dados) {
        this.id = dados.id();
        this.anotacao = dados.anotacao();
        this.ativo = true;
        this.dia = dados.dia();
        this.grinfar = dados.grinfar();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public boolean isGrinfar() {
        return grinfar;
    }

    public void setGrinfar(boolean grinfar) {
        this.grinfar = grinfar;
    }

    public void atualizarInformacoes(TarefaDTO dados) {
        if (dados.anotacao() != null) {
            this.anotacao = dados.anotacao();
        }

        this.grinfar = dados.grinfar();
    }


    public void excluir(){
        this.ativo = false;
    }
}