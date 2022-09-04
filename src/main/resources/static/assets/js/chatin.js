/* chat in */

console.log("chatin");

var ws;

wsOpen();
function wsOpen(){
	ws = new WebSocket("ws://" + location.host + "/chatin");

	wsEvt();
}

function wsEvt(){
	var member_no = document.getElementById("my_no").value;
	var chatroom_no = document.getElementById("chatroom_no").value;
/*	
	var chatin = $('.chatin').attr('id');
	
	console.log(chatin);
*/

	if(chatroom_no == ""){
		console.log("default");
	}else{
		
		$.ajax({
			type: 'POST',
			url: '/chat/m/' + chatroom_no,
			data: {
				member_no: member_no,
				chatroom_no: chatroom_no,
				chat_alarm_cnt: "0",
			},
			success: function(data){
				var alarm_chatroom_no = 'chatroom_alarm_' + chatroom_no;	
				var trim_alarm_chatroom_no = alarm_chatroom_no.trim();

				var cnt = document.getElementById(trim_alarm_chatroom_no).innerText;
				var parsecnt = parseInt(cnt);
				document.getElementById(trim_alarm_chatroom_no).innerText = 0;				
			},
			error: function(data){
				
			}
		});
		
	}
	
}