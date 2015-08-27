

function check(type){
	
	
http.open("SuspendRestore")	
 http.send("check="+type);


alert(http.resposeText)
}
