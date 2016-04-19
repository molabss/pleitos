	var E05_MESSAGE_USER = "O Sistema não respondeu dentro do tempo aceitável\nCOD E05";
	var E05_MESSAGE_CONSOLE = "TIMEOUT - o servidor está demorando mais para responder\n do que o cliente está configurado para esperar\nCOD E05";
	var E10_MESSAGE_USER = "Sua solicitação não pôde ser processada\nCOD: E10";
	var E10_MESSAGE_CONSOLE = "O recurso solicitado não foi encontrado ou o servidor está indisponível";
	

	//DEFINI PADROES PARA blockUI
	$.blockUI.defaults.css.border = '0px';
	$.blockUI.defaults.css.padding="20px";
	$.blockUI.defaults.css.backgroundColor='#FFFFFF';
	$.blockUI.defaults.message = getDefaultLoadingMessage();
	//------------------------------------------------------------------------------------
	
	
	function ajxRequest(async, timeout, method, url, param, beforeSend, onComplete, onSucess, onError)
	{
		$.ajax({
			async: async,
			type : method,
			url : url,
			data : param,
			dataType : "json",			
			beforeSend :beforeSend,
			success : onSucess,
			complete : onComplete,
			error : onError,
			cache: false,
			timeout: timeout
		});
	}
	
	
	
	function ajxRequestStd(options,onSucess){

		try{

			$.ajax({
				contentType:"application/x-www-form-urlencoded; charset=UTF-8",
				async: options.async,
				type : options.method,
				url : options.url,
				data : options.param,
				dataType : options.returnType,			
				beforeSend :function(){
					$.blockUI();
				},
				success : onSucess,
				complete : function(){
					$.unblockUI();
				},
				error : function(jqXHR , textStatus, errorThrown){
					
					$.unblockUI();
					
					if(jqXHR.status == 0){
						console.error(E05_MESSAGE_CONSOLE);
						alert(E05_MESSAGE_USER);					
						return;
					}
			
					if(jqXHR.responseJSON == undefined){
						$("#erroMsg").html(jqXHR.responseText);
						return;
					}
					
					console.error(jqXHR.responseJSON.serverMessageUser);
					alert(jqXHR.responseJSON.serverMessageUser);
					
					
					
					
					if(jqXHR.responseJSON.redirect){ window.location.href = jqXHR.responseJSON.redirectTo};
					
				},
				cache: false,
				timeout: options.timeout
			});
			
		}catch (e) {
			console.log("Erro "+e);
			window.location.href = "index.html";
		}
	}	
	
	
	
	function ajxRequestJSNp(method,timeout,url, param, beforeSend, onComplete, onSucess, onError){
		$.ajax({
			async: true,
			type : method,
			url : url,
			data : param,
			crossDomain:true,
			contentType: "application/json",
			dataType : "jsonp",
			jsonpCallback: 'callback',
			beforeSend :beforeSend,
			success : onSucess,
			complete : onComplete,
			error : onError,
			cache: false,
			timeout: timeout
		});
	}
	
	
	function getDefaultLoadingMessage()
	{
		var html='<img src ="img/ajx-loading.gif"/>';
  		html+='<span data-role="none" style="padding-top: 25px; color: #000000; font-size:150%"> Por favor, aguarde...</span>';
  		return html;
	}
	
	function getCustomLoadingMessage(msg)
	{
		var html='<img src ="../../img/ajx-loading.gif"/>';
  		html+='<span data-role="none" style="padding-top: 25px; color: #000000; font-size:150%"> '+msg+'</span>';
  		return html;
	}
	