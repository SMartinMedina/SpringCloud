package com.smartinm.springboot.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

//Comentamos la linea que utilizaba la Entity de manera interna del proyecto para usar la de commons
//import com.smartinm.springboot.app.productos.models.entity.Producto;
import com.smartinm.springboot.app.commons.models.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long>{

}
