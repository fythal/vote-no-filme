var app = {
	init: function(){
		app.importScripts();
		
		votacaoView.init();
	},
	
	importScripts: function(){
		var oScript = document.createElement('script');
		oScript.src = 'resources/js/model/VotacaoModel.js';
		oScript.type = 'text/javascript';
		$("head").append(oScript);
		
		oScript = document.createElement('script');
		oScript.src = 'resources/js/controller/VotacaoController.js';
		oScript.type = 'text/javascript';
		$("head").append(oScript);
		
		oScript = document.createElement('script');
		oScript.src = 'resources/js/view/VotacaoView.js';
		oScript.type = 'text/javascript';
		$("head").append(oScript);
		
		oScript = document.createElement('script');
		oScript.src = 'resources/js/utils/SortedUtil.js';
		oScript.type = 'text/javascript';
		$("head").append(oScript);
	}
		
};

window.onload = app.init;