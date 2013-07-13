package br.com.votenofilme.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.votenofilme.model.VotoTO;

@Component
public class VotoDAO extends GenericDAO<VotoTO>{
	private static final long serialVersionUID = 1L;

	public VotoDAO() {
		super(VotoTO.class);
	}

}
