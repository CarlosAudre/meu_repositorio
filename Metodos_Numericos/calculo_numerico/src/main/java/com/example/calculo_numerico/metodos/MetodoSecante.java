package com.example.calculo_numerico.metodos;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class MetodoSecante {
    public void metodoSecante(double xi, double xi_1, double precisao){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Informe a equação \n f(x) = ");
        String fx = myScanner.nextLine();

        Expression expression = new ExpressionBuilder(fx)
                .variable("x")
                .build()
                .setVariable("x", xi);
        double fxi = expression.evaluate();

        expression.setVariable("x", xi_1);
        double fxi_1 = expression.evaluate();
        int contador = 1;

        if (Math.abs(fxi) < precisao){
            System.out.println("Raiz encontrada");
            System.out.println("x = " + xi);
            System.out.println("Iterador:" + contador);

        }

        else if(Math.abs(fxi_1 )< precisao){
            System.out.println("Raiz encontrada");
            System.out.println("x = " + xi_1);
            System.out.println("Iterador:" + contador);
        }

        else {
            boolean loop = true;
            while (loop){
                double xi_1Novo = xi - (fxi * (xi_1 - xi)) / (fxi_1 - fxi);
                expression.setVariable("x", xi_1Novo);
               double fxi_1Novo = expression.evaluate();

                if (Math.abs(fxi_1Novo) < precisao){
                    System.out.println("Raiz encontrada");
                    System.out.println("x = " + xi_1Novo);
                    System.out.println("Iterador:" + contador);
                    break;
                }
                else {

                    xi = xi_1;
                    xi_1 = xi_1Novo;
                    fxi = fxi_1;
                    fxi_1 = fxi_1Novo;


                    contador++;
                }

            }
        }


    }
}
