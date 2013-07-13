package br.com.votenofilme.controller;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.votenofilme.facade.FilmeRankingFacade;
import br.com.votenofilme.facade.UsuarioFacade;
import br.com.votenofilme.model.FilmeRankingTO;
import br.com.votenofilme.model.UsuarioTO;

@Resource
public class RelatorioController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private final SessionUser sessionUser;
	
	private Result result;
	private FilmeRankingFacade rankingFacade;
	private UsuarioFacade usuarioFacade;
	
	public RelatorioController(Result result, FilmeRankingFacade rankingFacade, UsuarioFacade usuarioFacade, SessionUser sessionUser){
		this.result = result;
		this.rankingFacade = rankingFacade;
		this.usuarioFacade = usuarioFacade;
		this.sessionUser = sessionUser;
	}
	
	@Path("/filmes/ranking/person.json")
	public void rankingPerson(){
		List<FilmeRankingTO> ranking = rankingFacade.rankingGeneralByEmail(sessionUser.getUsuario().getEmail());
		
		result.use(Results.json()).withoutRoot().from(ranking).include("filme").serialize();
	}
	
	@Path("/filmes/ranking/general.json")
	public void rankingGeneral(){
		List<FilmeRankingTO> ranking = rankingFacade.rankingGeneral();
		
		result.use(Results.json()).withoutRoot().from(ranking).include("filme").serialize();
	}
	
	@Path("/filmes/relatorio/{email}")
	public void relatorio(String email){
		UsuarioTO usuario = usuarioFacade.findByEmail(email);
		
		sessionUser.setUsuario(usuario);
	}
	
}
