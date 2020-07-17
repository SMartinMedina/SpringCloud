package com.smartinm.springboot.app.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.smartinm.springboot.app.usuarios.commons.models.entity.Usuario;

// PagingAndSorting hereda de CrudRepository y nos brinda más funciones de Paginado y Ordenamiento
@RepositoryRestResource(path="usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long>{
	
	
	//Con esta configuracion lo llamariamos http://localhost:8090/api/usuarios/usuarios/search/buscar-username?nombre=usuario1
	@RestResource(path="buscar-username")
	public Usuario findByUsername(@Param("username") String username);

	//Buscando a través de más de un parametro con beneficios de JPA, automaticamente.
	//public String findByUsernameAndEmail(String username, String email);
	
	//Buscar mediante consultas con Entity propias de Clase (Usuario)
	@Query("select u from Usuario u where u.username = ?1")
	public Usuario findByConsultaPersonalizada(String username);	

	
	//Buscar mediante consultas nativas sql
	@Query(value = "select * from usuarios where usuarios.username = ?1", nativeQuery = true)
	public Usuario findByConsultaPersonalizadaSQL(@Param("username") String username);	

}
