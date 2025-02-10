package com.example.calculo_numerico.metodos;

import java.util.Scanner;

public class MetodoZeroDeFuncoes {
    public void ZeroDeFuncoes(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Escolha um dos algoritimos a baixo:");
        System.out.println("""
                (a) Algoritmo da Bissecção
                (b) Algoritmo de Newton
                (c) Algoritmo da Secante
                """);
        String opcao = myScanner.nextLine();
        if (opcao.equalsIgnoreCase("a")){
            MetodoBissecao metodoBissecao = new MetodoBissecao();
            System.out.println("Qual será o valor de xa?");
            double xa = myScanner.nextDouble();
            myScanner.nextLine();

            System.out.println("Qual será o valor de xb?");
            double xb = myScanner.nextDouble();
            myScanner.nextLine();

            System.out.println("Qual será o valor de precisão?");
            double precisao = myScanner.nextDouble();
            myScanner.nextLine();
            metodoBissecao.metodoBissecao(xa, xb, precisao);

        } else if (opcao.equalsIgnoreCase("b")) {
            MetodoNewton metodoNewton = new MetodoNewton();
            System.out.println("Digite o valor do x0");
            double x0 = myScanner.nextDouble();
            myScanner.nextLine();

            System.out.println("Digite o valor da precisão");
            double precisao = myScanner.nextDouble();
            myScanner.nextLine();

            metodoNewton.metodoNewton(x0, precisao);

        } else if (opcao.equalsIgnoreCase("c")) {
            MetodoSecante metodoSecante = new MetodoSecante();
            System.out.println("Digite o valor de xi");
            double xi = myScanner.nextDouble();
            myScanner.nextLine();

            System.out.println("Digite o valor de xi-1");
            double xi_1 = myScanner.nextDouble();
            myScanner.nextLine();

            System.out.println("Digite o valor da precisão");
            double precisao = myScanner.nextDouble();
            myScanner.nextLine();

            metodoSecante.metodoSecante(xi, xi_1, precisao);

        }
        else {
            System.out.println("Opção inválida");
        }
    }
}
