var relatorioView = {
	init: function(){
		this.getData();
		this.renderRelatorio();
	},	
	
	getData: function(){
		relatorioController.getPersonData();
		relatorioController.getGeneralData();
	},
		
	renderRelatorio: function(){
		this.renderRelatorioPerson();
		this.renderRelatorioGeneral();
	},
	
	renderRelatorioPerson: function(){		
		var nome = usuarioController.getUsuario().nome;
		$('#person').append('<label class="lblHeaderRanking">' +  nome + '</label>');
		
		this.renderListRelatorio('#person', relatorio.personData);
	},
	
	renderRelatorioGeneral: function(){
		$('#general').append('<label class="lblHeaderRanking">Geral</label>');
		this.renderListRelatorio('#general', relatorio.generalData);
	},
	
	renderListRelatorio: function(idDiv, data){		
		$(idDiv).append('<ul class="none"> </ul>');
		
		var i = 1;
		$.each(data, function(count, votacao) {
			var textAppend = '<li>';
			
			if(i == 1){
				textAppend += '<span class="first">#' + i;
				textAppend += ' <img src="../../resources/images/' + votacao.filme.imagem + '" class="firstImage" />'; 
			}else{
				textAppend += ' <span class="others">#' + i;
				textAppend += ' <img src="../../resources/images/' + votacao.filme.imagem + '" class="othersImage" />';
			}
			
			
			textAppend += ' ' + votacao.filme.titulo;
			textAppend += ' - ' + votacao.votos + ' votos';
			textAppend += '</li>';
			
			$(idDiv + ' ul').append(textAppend);
			i++;
		});
	}
	
	
};

