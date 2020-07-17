package com.smartinm.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//Comentamos la linea que utilizaba la Entity de manera interna del proyecto para usar la de commons
//import com.smartinm.springboot.app.productos.models.entity.Producto;
import com.smartinm.springboot.app.commons.models.entity.Producto;
import com.smartinm.springboot.app.productos.models.service.IProductoService;

@RestController
public class ProductoController {
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer port;
	

	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll().stream().map(producto -> {
			//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) throws Exception {
		Producto producto = productoService.findById(id);
		//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		producto.setPort(port);
		
		
		/*	LANZAMIENTO DE EXCEPCION PARA LA PRUEBA DE HYSTRIX EN SERVICIO CLIENTE
		boolean x = false;
		if(x == false) {
			throw new Exception("Error de Prueba para probar metodo alternativo con Hystrix.");
		}
		*/
		
		/*	
		 * LANZAMIENTO DE EXCEPCION POR TIMEOUT DE HYSTRIX Y RIBBON, PARA PROBAR CONFIGURACIÓN DE TIMEOT EN PROTECTO CLIENTE
		 * 
		 */
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/**/
		
		return producto;
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return productoService.save(producto);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoDB = productoService.findById(id);
		
		productoDB.setNombre(producto.getNombre());
		productoDB.setPrecio(producto.getPrecio());
		productoService.save(productoDB);
		return productoDB;
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoService.delete(id);
	}
	
	
	
	
	
}
