/* chat in */

console.log("alarm");

var ws;

var member_no = document.getElementById("my_no").value;

wsOpen();
function wsOpen(){
	ws = new WebSocket("ws://" + location.host + "/{}/alarm");

	wsEvt();
}

function wsEvt(){
	
	ws.onopen = function(data){
		//소켓 열리면 초기화 세팅	
	}
	
	ws.onmessage = function(data){
		
		/* 원본 소켓 내용 */
		var msg = data.data;
		console.log("msg : " + msg);
		
		/* 원본 소켓 메시지 -> json파싱(Text) */
		var parse_msg = JSON.parse(msg);
		console.log("parse_msg : " + parse_msg.msg);
		
		var getSocket_member_no = parse_msg.member_no;
		var getSocket_member_id = parse_msg.member_id;
		var getSocket_member_img = parse_msg.member_img;
		var getSocket_msg = parse_msg.msg;
		var getSocket_chatroom_no = parse_msg.chatroom_no;
		var getSocket_division = parse_msg.division;
		var getSocket_nowTimes = parse_msg.nowTimes;
		var getSocket_chat_alarm = parse_msg.chat_alarm;
		
		var getSocket_original_filename = parse_msg.original_filename;

		var getSocket_nowTimesarr = getSocket_nowTimes.split(" ");
		var getSocket_nowTimesdate = getSocket_nowTimesarr[0];
		var getSocket_nowTimesTime = getSocket_nowTimesarr[1];
		
		if(getSocket_member_no == member_no){

		}else{
			if(document.getElementById(" chat_alarm_no_" + getSocket_chatroom_no + " ")){
				
				var chat_alarm_groupt_cnt = document.getElementById(" chat_alarm_group_cnt_" + getSocket_chatroom_no + " ").innerText;
				var parsecnt = parseInt(chat_alarm_groupt_cnt) + 1;
				
				document.getElementById(" chat_alarm_no_" + getSocket_chatroom_no + " ").remove();
				
				var msg = "<form id=' chat_alarm_no_" + getSocket_chatroom_no + " '" + "method='post' action='/move_detail_chat'>";
					msg += "<input type='hidden' name='chatroom_no' value='" + getSocket_chatroom_no + "' />"
				    if(getSocket_division == "text"){
					    msg += "<div id=' chat_alarm_group_cnt_" + getSocket_chatroom_no + " '" + ">" + parsecnt + "</div>";
					    msg += "<div>" + getSocket_member_id + "</div>";
					    msg += "<div>" + getSocket_msg + "</div>"
				    }
				    
				    if(getSocket_division == "file"){
				    msg += "<div id=' chat_alarm_group_cnt_" + getSocket_chatroom_no + " '" + ">" + parsecnt + "</div>";
				    msg += getSocket_member_id + "님이 사진을 전송하였습니다.";
				    }
				    
				    msg += "<input type='submit'>";
			        msg += "</form>"
				    
				    $("#header_chat_alarm").append(msg);
				
			}else{
				
				console.log("no");
				
				var parsecnt = 1;
				
				var msg = "<form id=' chat_alarm_no_" + getSocket_chatroom_no + " '" + "method='post' action='/move_detail_chat'>";
					msg += "<input type='hidden' name='chatroom_no' value='" + getSocket_chatroom_no + "' />"
				    if(getSocket_division == "text"){
					    msg += "<div id=' chat_alarm_group_cnt_" + getSocket_chatroom_no + " '" + ">" + parsecnt + "</div>";
					    msg += "<div>" + getSocket_member_id + "</div>";
					    msg += "<div>" + getSocket_msg + "</div>"
				    }
				    
				    if(getSocket_division == "file"){
				    	msg += "<div id=' chat_alarm_group_cnt_" + getSocket_chatroom_no + " '" + ">" + parsecnt + "</div>";
				    	msg += getSocket_member_id + "님이 사진을 전송하였습니다.";
				    }
				    
				    	msg += "<input type='submit'>";
			        	msg += "</form>"
				    
				    $("#header_chat_alarm").append(msg);
				    
			}
			
		}
		
	}
	

}