package com.example.MetodosNumericos.metodos;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MetodoGaussSeidel {
    private final Scanner myScanner = new Scanner(System.in);

    public void GaussSeidel(int numVariaveis, double precisao) {
        System.out.println("Calculadora do método de Gauss-Seidel");

        List<String> expressoes = new ArrayList<>();
        List<Double> variaveis = new ArrayList<>();
        List<Double> variaveisAnt = new ArrayList<>();

        for (int i = 0; i < numVariaveis; i++) {
            System.out.println("Digite a expressão para a variável x" + (i + 1) + ":");
            String expressao = myScanner.nextLine();
            expressoes.add(expressao);
            variaveis.add(1.0);
            variaveisAnt.add(Double.MAX_VALUE);
        }

        boolean converge = false;
        int iteracao = 0;
        int maxIteracoes = 1000;

        while (!converge && iteracao < maxIteracoes) {
            converge = true;
            for (int i = 0; i < numVariaveis; i++) {
                Expression exp = new ExpressionBuilder(expressoes.get(i))
                        .variables(getVariableNames(numVariaveis))
                        .build();

                for (int j = 0; j < numVariaveis; j++) {
                    exp.setVariable("x" + (j + 1), variaveis.get(j));
                }

                double novoValor = exp.evaluate();


                if (Double.isInfinite(novoValor) || Double.isNaN(novoValor)) {
                    System.out.println("Erro: o valor calculado é inválido. Iteração interrompida.");
                    return; 
                }

                double erro = Math.abs(novoValor - variaveisAnt.get(i));

                variaveisAnt.set(i, variaveis.get(i));
                variaveis.set(i, novoValor);

                if (erro > precisao) {
                    converge = false;
                }
            }
            iteracao++;
            System.out.println("Iteração " + iteracao + ": " + variaveis);

            if (iteracao >= maxIteracoes) {
                System.out.println("Número máximo de iterações atingido.");
                break;
            }
        }

        System.out.println("Solução encontrada com precisão " + precisao + ":");
        for (int i = 0; i < numVariaveis; i++) {
            System.out.println("x" + (i + 1) + " = " + variaveis.get(i));
        }
    }

    private String[] getVariableNames(int numVariaveis) {
        String[] variableNames = new String[numVariaveis];
        for (int i = 0; i < numVariaveis; i++) {
            variableNames[i] = "x" + (i + 1);
        }
        return variableNames;
    }
}





