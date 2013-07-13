package br.com.votenofilme.controller;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.votenofilme.facade.FilmeFacade;
import br.com.votenofilme.model.FilmeTO;

@Resource
public class FilmeController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Result result;
	private FilmeFacade filmeFacade;
	
	public FilmeController(Result result, FilmeFacade filmeFacade){
		this.result = result;
		this.filmeFacade = filmeFacade;		
	}

	@Path("/filmes/list.json")
	public void listFilmes() {
		List<FilmeTO> filmes = filmeFacade.listAll();
		
		result.use(Results.json()).withoutRoot().from(filmes).serialize();
	}
	
}

