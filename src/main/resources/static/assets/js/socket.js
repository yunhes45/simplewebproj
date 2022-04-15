/* socket */

console.log("conn");
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
		//소켓 열리면 초기화 세팅	
	}
	ws.onmessage = function(data){
		var member_no = document.getElementById("my_no").value;
		var chatroom_no = document.getElementById("chatroom_no").value;
		var msg = data.data;
		console.log(msg);
		
		var msgarr = msg.split(",");
		var getSocket_member_no = msgarr[0];
		var getSocket_member_id = msgarr[1];
		var getSocket_member_img = msgarr[2];
		var getSocket_msg = msgarr[3];
		var getSocket_nowTimes = msgarr[4];
		var getSocket_chatroom_no = msgarr[5];
		
		console.log(getSocket_member_no);
		console.log(getSocket_member_id);
		console.log(getSocket_member_img);
		console.log(getSocket_msg);
		console.log(getSocket_nowTimes);
		console.log(getSocket_chatroom_no);
		
	}

}

function send(){
	var member_no = document.getElementById("my_no").value;
	var member_id = document.getElementById("my_id").value;
	var member_img = document.getElementById("my_img").value;
	var msg = document.getElementById("chat_holder").value;
	var chatroom_no = document.getElementById("chatroom_no").value;

	var today     =  new Date();
	var hours     =  today.getHours();
	var minutes   =  today.getMinutes();
	var seconds   =  today.getSeconds();		
	var nowTimes = (("00"+hours.toString()).slice(-2)) + ":" + (("00"+minutes.toString()).slice(-2)) + ":" + (("00"+seconds.toString()).slice(-2));  

	// 채팅 공백 시 응답 X
	var chatform_nullcheck = document.querySelector("#chat_holder");
	
	if(chatform_nullcheck.value != ""){
		ws.send(member_no+","+member_id+","+member_img+","+msg+","+nowTimes+","+chatroom_no);
		document.getElementById("chat_holder").value = "";
		
	}
	
	
	
}