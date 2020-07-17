package com.smartinm.springboot.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;//LO QUITAMOS A PARTIR DE EUREKA SERVER
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker //Habilitamos Hystrix para detección y manejo de errores
@EnableEurekaClient
//@RibbonClient(name="servicio-productos") //LO QUITAMOS A PARTIR DE EUREKA SERVER
@EnableFeignClients
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootServicioItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioItemApplication.class, args);
	}

}
