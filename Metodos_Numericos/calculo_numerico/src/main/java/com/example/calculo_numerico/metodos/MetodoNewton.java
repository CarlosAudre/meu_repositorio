package com.example.calculo_numerico.metodos;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.Scanner;

public class MetodoNewton {
    Scanner myScanner = new Scanner(System.in);

    public void metodoNewton(double xi, double precisao) {
        System.out.println("Informe a equação \n f(x) = ");
        String fx = myScanner.nextLine();

        Expression expression = new ExpressionBuilder(fx)
                .variable("x")
                .build();

        double fxi = expression.setVariable("x", xi).evaluate();
        int contador = 1;

        while (true) {
            if (Math.abs(fxi) < precisao) {
                System.out.println("Raiz encontrada");
                System.out.println(xi);
                System.out.println("Iterações: " + contador);
                break;
            }

            // Cálculo da derivada numericamente
            double derivadaFxi = calculateNumericalDerivative(expression, xi);

            if (Math.abs(derivadaFxi) < 1e-10) {
                System.out.println("Derivada próxima de zero. Método pode não convergir.");
                break;
            }

            double xii = xi - (fxi / derivadaFxi);

            double fxii = expression.setVariable("x", xii).evaluate();

            if (Math.abs(fxii) < precisao || Math.abs(xii - xi) < precisao) {
                System.out.println("Raiz encontrada");
                System.out.println(xii);
                System.out.println("Iterações: " + contador);
                break;
            }

            xi = xii;
            fxi = fxii;
            contador++;
        }
    }

    private double calculateNumericalDerivative(Expression expression, double x) {
        double h = 1e-6;
        double fxh = expression.setVariable("x", x + h).evaluate();
        double fx = expression.setVariable("x", x).evaluate();
        return (fxh - fx) / h;
    }

    public static void main(String[] args) {
        MetodoNewton metodo = new MetodoNewton();
        metodo.metodoNewton(1.5, 0.00001);
    }
}
