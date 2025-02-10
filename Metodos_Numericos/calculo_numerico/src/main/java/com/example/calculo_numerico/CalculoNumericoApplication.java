package com.example.calculo_numerico;

import com.example.calculo_numerico.metodos.MetodoZeroDeFuncoes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculoNumericoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculoNumericoApplication.class, args);

		MetodoZeroDeFuncoes metodoZeroDeFuncoes = new MetodoZeroDeFuncoes();
		metodoZeroDeFuncoes.ZeroDeFuncoes();

	}



}
