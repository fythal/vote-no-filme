package br.com.votenofilme.dao;

import java.util.HashMap;
import java.util.Map;

import br.com.caelum.vraptor.ioc.Component;
import br.com.votenofilme.model.UsuarioTO;

@Component
public class UsuarioDAO extends GenericDAO<UsuarioTO>{
	private static final long serialVersionUID = 1L;

	public UsuarioDAO() {
		super(UsuarioTO.class);
	}
	
	public UsuarioTO findByEmail(String email){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("email", email);
		
		return super.findOneResult(UsuarioTO.FIND_BY_EMAIL, parameters);
	}
}
