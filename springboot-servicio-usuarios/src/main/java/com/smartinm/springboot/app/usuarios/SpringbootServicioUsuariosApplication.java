package com.smartinm.springboot.app.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"com.smartinm.springboot.app.usuarios.commons.models.entity"})
@SpringBootApplication(scanBasePackages ="com.smartinm.springboot.app.usuarios.commons.models.entity")
public class SpringbootServicioUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioUsuariosApplication.class, args);
	}

}