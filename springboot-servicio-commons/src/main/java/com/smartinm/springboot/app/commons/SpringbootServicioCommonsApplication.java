package com.smartinm.springboot.app.commons;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
/* QUITAMOS LA DEPENDENCIA DE SPRING APPLICATION*/
//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootServicioCommonsApplication {

	/*
	 * LO COMENTAMOS PORQUE EL OBJETIVO DE ESTE PROYECTO ES CREAR UNA LIBRERÍA
	 * NO LEVANTAMOS ESTE PROYECTO. 
	 * 
	 * 
	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioCommonsApplication.class, args);
	}
	 */
}
