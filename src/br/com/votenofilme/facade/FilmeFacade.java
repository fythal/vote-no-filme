package br.com.votenofilme.facade;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.votenofilme.dao.FilmeDAO;
import br.com.votenofilme.model.FilmeTO;

@Component
public class FilmeFacade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private FilmeDAO dao;
	
	public FilmeFacade(FilmeDAO dao){
		this.dao = dao;
	}
	
	public void create(FilmeTO filme){
		dao.save(filme);		
	}
	
	public FilmeTO find(int id){
		return dao.find(id);
	}
	
	public List<FilmeTO> listAll(){
		List<FilmeTO> result = dao.findAll();
		
		return result;
	}	

}
