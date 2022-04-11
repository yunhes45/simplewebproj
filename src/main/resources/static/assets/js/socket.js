/* socket */
console.log("dd");

function deliver_socket(id){
	var ex1 = document.getElementById("dd");
	console.log(ex1);
	console.log(id);
	ex11 = id;

	var ws;
	var scrolldiv = document.getElementById("chat_form");
	scrolldiv.scrollTop = scrolldiv.scrollHeight;
	
	wsOpen();
	function wsOpen(){
		ws = new WebSocket("ws://" + location.host + "/chating");
		wsEvt();
	}
	
	function wsEvt(){
		ws.onopen = function(data){
			
		}
		ws.onmessage = function(data){
		}
	
}

}