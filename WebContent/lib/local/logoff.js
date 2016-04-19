
	function logoff(){
		if(confirm("Você será desconectado...")){
			ajxRequestStd({returnType:"json", async:false, timeout:10000, method:"GET", url:"../../ws/restrito/carga/logoff"}, 
				function(response){
					window.location.href = response.serverMessageUser
				}
			);
		}
	}