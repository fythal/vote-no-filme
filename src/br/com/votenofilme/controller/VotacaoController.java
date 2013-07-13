package br.com.votenofilme.controller;

import java.io.Serializable;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.votenofilme.exception.VotacaoException;
import br.com.votenofilme.facade.UsuarioFacade;
import br.com.votenofilme.facade.VotoFacade;
import br.com.votenofilme.json.VotacaoWrapper;
import br.com.votenofilme.model.UsuarioTO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Resource
public class VotacaoController implements Serializable{
	private static final long serialVersionUID = 1L;

	private final SessionUser sessionUser;
	
	private Result result;
	private VotoFacade votoFacade;
	private UsuarioFacade usuarioFacade;
	
	public VotacaoController(Result result, VotoFacade votoFacade, UsuarioFacade usuarioFacade, SessionUser sessionUser){
		this.result = result;
		this.votoFacade = votoFacade;
		this.usuarioFacade = usuarioFacade;
		this.sessionUser = sessionUser;
	}
	
	@Post
	@Path("/filmes/persistVotos")
	public void persistVotos(String data){
		System.out.println(data);
		
		Gson gson = new GsonBuilder().create();
		VotacaoWrapper votacao = gson.fromJson(data, VotacaoWrapper.class);
			
		System.out.println(votacao.getNome());
		
		try{
			UsuarioTO usuario = new UsuarioTO();
			usuario.setNome(votacao.getNome());
			usuario.setEmail(votacao.getEmail());
			usuario = usuarioFacade.create(usuario);
			sessionUser.setUsuario(usuario);
			
			votoFacade.createVotos(votacao, usuario);
			result.use(Results.json()).from("REDIRECT").serialize();
			//result.redirectTo(RelatorioController.class).relatorio(votacao.getEmail());
		}catch(Exception e){
			if(e instanceof VotacaoException){
				result.use(Results.json()).from(e.getMessage()).serialize();
			}else{
				e.printStackTrace();
			}
		}
	}
}
