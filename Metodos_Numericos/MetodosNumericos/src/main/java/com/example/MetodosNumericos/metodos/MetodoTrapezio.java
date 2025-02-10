package com.example.MetodosNumericos.metodos;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class MetodoTrapezio {

    public void trapezio(double a, double b, int n) {
        System.out.println("Digite a expressão para a função f(x): ");
        Scanner myScanner = new Scanner(System.in);
        String expressao = myScanner.nextLine();

        Expression exp = new ExpressionBuilder(expressao)
                .variable("x")
                .build();

        double h = (b - a) / n;

        double soma = 0.5 * (exp.setVariable("x", a).evaluate() + exp.setVariable("x", b).evaluate());

        for (int i = 1; i < n; i++) {
            double x_i = a + i * h;
            soma += exp.setVariable("x", x_i).evaluate();
        }

        double resultado = h * soma;
        System.out.println("Resultado da integração pelo método do trapézio: " + resultado);
    }
}
