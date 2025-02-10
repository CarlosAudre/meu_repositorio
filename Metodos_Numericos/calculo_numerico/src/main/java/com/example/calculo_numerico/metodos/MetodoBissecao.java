package com.example.calculo_numerico.metodos;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class MetodoBissecao {
    Scanner myScanner = new Scanner(System.in);

    public void metodoBissecao(double xa, double xb, double precisao){
        int contagem = 0;
        System.out.println("Informe a equação \n f(x) = ");
        String expressao = myScanner.nextLine();

        Expression expression = new ExpressionBuilder(expressao)
                .variables("x")
                .build()
                .setVariable("x", xa);
        double fxa = expression.evaluate();

        expression.setVariable("x", xb);
        double fxb = expression.evaluate();
        double fxa_fxb = fxa * fxb;

        if(fxa_fxb < 0){
            double xm = (xa + xb) / 2;
            expression.setVariable("x", xm);
            double fxm = expression.evaluate();
            boolean loop = true;

            while (loop){

                if (Math.abs(fxm) < precisao || Math.abs(xb - xa) < precisao){
                    break;
                } else {
                    double fxa_fxm = fxa * fxm;
                    if (fxa_fxm < 0){
                        xb = xm;
                    } else {
                        xa = xm;
                    }
                    xm = (xa + xb) / 2;

                    expression.setVariable("x", xm);
                    fxm = expression.evaluate();
                    contagem++;
                }
            }

            System.out.println("Raiz = " + xm + " com precisão de " + precisao);
            System.out.println("Iterações: " + contagem);

        } else {
            System.out.println("Não tem raiz no intervalo");
        }
    }
}
