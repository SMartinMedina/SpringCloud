package com.smartinm.springboot.app.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.smartinm.springboot.app.oauth.services.IUsuarioService;
import com.smartinm.springboot.app.usuarios.commons.models.entity.Usuario;

import brave.Tracer;
import feign.FeignException;

@Component
public class AuthenticatorSuccessErrorHandler implements AuthenticationEventPublisher{

	private Logger log = LoggerFactory.getLogger(AuthenticatorSuccessErrorHandler.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private Tracer tracer;
	
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		if(authentication.getName().equalsIgnoreCase("frontendapp")){
		    return; // si es igual a frontendapp se salen del método!
		}
		
		
		UserDetails user = (UserDetails)authentication.getPrincipal();
		String mensaje = "Success de login: " + user.getUsername();
		System.out.println(mensaje);
		log.info(mensaje);
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		if(usuario.getIntentos() != null && usuario.getIntentos() > 0) {
			usuario.setIntentos(0);
			usuarioService.update(usuario, usuario.getId());
		}
		
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String mensaje = "Error de login: " + exception.getMessage();
		System.out.println(mensaje);
		log.info(mensaje);
		StringBuilder errors = new StringBuilder();		
		errors.append(mensaje);
		
		if(authentication.getName().equalsIgnoreCase("frontendapp")){
		    return; // si es igual a frontendapp se salen del método!
		}
		
		try {
			
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			if(usuario.getIntentos() == null) {
				usuario.setIntentos(0);
			}
			usuario.setIntentos(usuario.getIntentos() + 1);
			log.info("Intento actual: " + usuario.getIntentos());
			errors.append(" - Intentos de Login" + usuario.getIntentos());
			if(usuario.getIntentos() >= 3) {
				String errorMsg = "El usuario %s deshabilitado por maximos intentos";
				log.error(String.format(errorMsg, authentication.getName()));
				
				errors.append(errorMsg);
				usuario.setEnabled(false);
			}
			usuarioService.update(usuario, usuario.getId());		
			tracer.currentSpan().tag("error.mensaje", errors.toString());
			
		} catch (FeignException e) {
			log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
		}
		
	}

}
