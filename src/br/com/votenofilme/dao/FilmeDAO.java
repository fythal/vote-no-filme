package br.com.votenofilme.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.votenofilme.model.FilmeTO;

@Component
public class FilmeDAO extends GenericDAO<FilmeTO>{
	private static final long serialVersionUID = 1L;

	public FilmeDAO() {
		super(FilmeTO.class);
	}
}
