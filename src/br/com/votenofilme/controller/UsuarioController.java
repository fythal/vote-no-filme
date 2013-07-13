package br.com.votenofilme.controller;

import java.io.Serializable;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class UsuarioController implements Serializable{
	private static final long serialVersionUID = 1L;

	private final SessionUser sessionUser;
	
	private Result result;
	
	
	public UsuarioController(Result result, SessionUser sessionUser){
		this.result = result;
		this.sessionUser = sessionUser;
	}
	
	@Path("usuario/user.json")
	public void getUsuario(){
		result.use(Results.json()).withoutRoot().from(sessionUser.getUsuario()).serialize();
	}
}
