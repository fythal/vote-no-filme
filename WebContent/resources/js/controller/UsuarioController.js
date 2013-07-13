var usuarioController = {
	getUsuario: function(){
		$.ajax({ 
		    url: '../../usuario/user.json', 
		    dataType: 'json', 
		    data: null, 
		    async: false, 
		    success: function(data){ 
		    	usuario.id = data.id;
		    	usuario.nome = data.nome;
		    	usuario.email  = data.email;		    	
		    } 
		});	
		
		return usuario;
	}	
};