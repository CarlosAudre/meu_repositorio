package com.example.MetodosNumericos.metodos;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class MetodoSimpson {

    public void simpson(double a, double b, int n) {
        if (n % 2 != 0) {
            n++;
        }

        System.out.println("Digite a expressão para a função f(x): ");
        Scanner myScanner = new Scanner(System.in);
        String expressao = myScanner.nextLine();

        Expression exp = new ExpressionBuilder(expressao)
                .variable("x")
                .build();

        double h = (b - a) / n;

        double soma = exp.setVariable("x", a).evaluate() + exp.setVariable("x", b).evaluate();

        for (int i = 1; i < n; i++) {
            double x_i = a + i * h;
            double coeficiente = (i % 2 == 0) ? 2 : 4;
            soma += coeficiente * exp.setVariable("x", x_i).evaluate();
        }
        double resultado = (h / 3) * soma;
        System.out.println("Resultado da integração pelo método de Simpson: " + resultado);
    }
}
