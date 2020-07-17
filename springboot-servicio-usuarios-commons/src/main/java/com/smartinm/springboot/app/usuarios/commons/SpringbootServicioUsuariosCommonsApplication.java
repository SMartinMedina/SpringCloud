package com.smartinm.springboot.app.usuarios.commons;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//LO QUITAMOS POR SER UN PROYECTO DE COMMONS, NO LO PRECISAMOS
//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootServicioUsuariosCommonsApplication {

	/*
	 * LO QUITAMOS POR SER UN PROYECTO DE COMMONS, NO LO PRECISAMOS 
	 * 
	public static void main(String[] args) {

		SpringApplication.run(SpringbootServicioUsuariosCommonsApplication.class, args);
	}
	 */

}
