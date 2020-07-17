package com.smartinm.springboot.app.oauth.services;

import com.smartinm.springboot.app.usuarios.commons.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);
	
	public Usuario update(Usuario usuario, Long id);

}
