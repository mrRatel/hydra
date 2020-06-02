function post(url,data,success(res),failed(res)){
	$.post(url,data,function(resp){
		alert(JSOn.stringify(resp))
	})
}