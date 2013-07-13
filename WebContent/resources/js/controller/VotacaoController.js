var votacaoController = {
	getFilmes: function(){
		$.ajax({ 
		    url: 'filmes/list.json', 
		    dataType: 'json', 
		    data: null, 
		    async: false, 
		    success: function(data){ 
		    	votacao.dataMovies = data;		    	
		    } 
		});	
	},
	
	postVotacao: function(nome, email){
		var data = {nome: nome, email: email, votos: votacao.dataVotos};
		data = JSON.stringify(data, null, 2);
		
		$.post("filmes/persistVotos", {data: data}, votacaoView.onMessagePostVotacao, 'json');
	}
};