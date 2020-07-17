package com.smartinm.springboot.app.productos.models.service;

import java.util.List;

//Comentamos la linea que utilizaba la Entity de manera interna del proyecto para usar la de commons
//import com.smartinm.springboot.app.productos.models.entity.Producto;
import com.smartinm.springboot.app.commons.models.entity.Producto;

public interface IProductoService {
	public List<Producto> findAll();
	public Producto findById(Long id);
	
	public Producto save(Producto producto);
	public void delete(Long id);

}
