package br.com.votenofilme.facade;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.votenofilme.dao.FilmeRankingDAO;
import br.com.votenofilme.model.FilmeRankingTO;

@Component
public class FilmeRankingFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private FilmeRankingDAO dao;
	
	public FilmeRankingFacade(FilmeRankingDAO dao){
		this.dao = dao;
	}
	
	public List<FilmeRankingTO> rankingGeneral(){
		return dao.rankingGeneral();
	}
	
	public List<FilmeRankingTO> rankingGeneralByEmail(String email){
		return dao.rankingByEmail(email);
	}
}
