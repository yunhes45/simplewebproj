/* chat in */

console.log("chatin");

var ws;

var member_no = document.getElementById("my_no").value;
var chatroom_no = document.getElementById("chatroom_no").value;

wsOpen();
function wsOpen(){
	ws = new WebSocket("ws://" + location.host + "/chatin");

	wsEvt();
}

function wsEvt(){

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

ws.onmessage = function(data){	
	
	console.log("chatroom_no : ", chatroom_no);
	
	/* 원본 소켓 내용 */
	var msg = data.data;
	console.log("msg chatin : " + msg); 
	
	/* 원본 소켓 메시지 -> json파싱(Text) */
	var parse_msg = JSON.parse(msg);
	console.log("parse_msg chatin : " + parse_msg.msg);
	
    var getSocket_chatroom_no = parse_msg.chatroom_no;
   	var alarm_chatroom_no = 'chatroom_alarm_' + getSocket_chatroom_no;	
	var trim_alarm_chatroom_no = alarm_chatroom_no.trim();	
	
	if(parse_msg != null){
		if(chatroom_no == getSocket_chatroom_no){
		
			console.log("dfsfs", trim_alarm_chatroom_no);
			
			document.getElementById(trim_alarm_chatroom_no).innerText = "0";
			
			AjaxDeleteChatAlarm();	
		}
	}
	
}

function AjaxDeleteChatAlarm(){
	$.ajax({
		type: 'POST',
		url: '/chat/m/' + chatroom_no,
		data: {
			member_no: member_no,
			chatroom_no: chatroom_no,
			chat_alarm_cnt: "0",
		},
		success: function(data){
			
		},
		error: function(data){
			
		}
	});	
}