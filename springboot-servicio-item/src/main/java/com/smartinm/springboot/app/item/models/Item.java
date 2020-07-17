package com.smartinm.springboot.app.item.models;
//Comentamos la linea que utilizaba la Entity de manera interna del proyecto para usar la de commons
//import com.smartinm.springboot.app.productos.models.entity.Producto;
import com.smartinm.springboot.app.commons.models.entity.Producto;

public class Item {

	private Producto producto;
	private Integer cantidad;

	public Item() {
	}

	public Item(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getTotal() {
		return producto.getPrecio() * cantidad.doubleValue(); 
	}

}
