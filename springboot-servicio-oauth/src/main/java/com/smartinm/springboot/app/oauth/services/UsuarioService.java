package com.smartinm.springboot.app.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartinm.springboot.app.oauth.clients.UsuarioFeignClient;
import com.smartinm.springboot.app.usuarios.commons.models.entity.Usuario;

import brave.Tracer;
import feign.FeignException;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService{
	
	@Autowired
	private UsuarioFeignClient client;
	
	@Autowired
	private Tracer tracer;
	
	
	private Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		try {
			Usuario usuario = client.findByUsername(username);
			//GrantedAuthority, roles de SpringSecurity 
			List<GrantedAuthority> authorities = usuario.getRoles()
					.stream()
					.map(role -> new SimpleGrantedAuthority(role.getNombre()))
					.peek(authority -> log.info("Role: "+ authority.getAuthority()))
					.collect(Collectors.toList());
			
			log.info("Usuario autentificado: "+usuario.getUsername());
			return new User(usuario.getUsername(),usuario.getPassword(),usuario.getEnabled(),true, true,true, authorities);
		} catch (FeignException e) {
			String error ="No existe el usuario:'"+username+"'"; 
			log.error(error);
			tracer.currentSpan().tag("error.mensaje", error + ":" + e.getMessage());
			
			throw new UsernameNotFoundException("No existe el usuario:'" + username + 	"'");

		}
	}

	@Override
	public Usuario findByUsername(String username) {
		return client.findByUsername(username);
	}

	@Override
	public Usuario update(Usuario usuario, Long id) {
		return client.update(usuario, id);
	}

}
