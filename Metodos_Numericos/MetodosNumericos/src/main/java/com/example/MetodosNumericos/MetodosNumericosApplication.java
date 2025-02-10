package com.example.MetodosNumericos;

import com.example.MetodosNumericos.metodos.MetodoGaussSeidel;
import com.example.MetodosNumericos.metodos.MetodoSimpson;
import com.example.MetodosNumericos.metodos.MetodoTrapezio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MetodosNumericosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetodosNumericosApplication.class, args);

		MetodoGaussSeidel metodoGaussSeidel = new MetodoGaussSeidel();
		metodoGaussSeidel.GaussSeidel(5, 0.0000000001);

//		MetodoTrapezio metodoTrapezio = new MetodoTrapezio();
//		metodoTrapezio.trapezio(0,2,1);
//
//		MetodoSimpson metodoSimpson = new MetodoSimpson();
//		metodoSimpson.simpson(0,2,1);

	}

}
