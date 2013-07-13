package br.com.votenofilme.controller;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.votenofilme.model.UsuarioTO;

@SessionScoped
@Component
public class SessionUser {

	private UsuarioTO usuario;

	public UsuarioTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}
}
