/* socket */

console.log("conn");

var ws;


wsOpen();
function wsOpen(){
	ws = new WebSocket("ws://" + location.host + "/{}/chating");

	wsEvt();
}


function wsEvt(){
		
	ws.onopen = function(data){
		//소켓 열리면 초기화 세팅	
	}
	ws.onmessage = function(data){
		
		var member_no = document.getElementById("my_no").value;
		var chatroom_no = document.getElementById("chatroom_no").value;
		
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
				var alarm_chatroom_no = 'chatroom_alarm_' + getSocket_chatroom_no;	
				var trim_alarm_chatroom_no = alarm_chatroom_no.trim();

				var cnt = document.getElementById(trim_alarm_chatroom_no).innerText;
				var parsecnt = parseInt(cnt);
				document.getElementById(trim_alarm_chatroom_no).innerText = parsecnt + 1;					
				}

		
		if(getSocket_chatroom_no == chatroom_no){
		if(getSocket_division == "text"){
			if(getSocket_member_no == member_no){
	
				var msgTmp = "<div class='chat_msg_my_form'>"
					msgTmp += "<div class='chat_myLog'>"
					msgTmp += "<div class='chat_myprofile'>"
					msgTmp += "<div class='chat_myimg'>"
					msgTmp += "<img class='img' src='/memberimg/" + getSocket_member_img + "' >"
					msgTmp += "</div>"
					msgTmp += "<div class='chat_myname'>"
					msgTmp += getSocket_member_id;
					msgTmp += "</div>"
					msgTmp += "</div>"
					msgTmp += "<div class='chat_mymsg'>"
					msgTmp += getSocket_msg;
					msgTmp += "</div>"
					msgTmp += "<div class='chat_myTime'>"
					msgTmp += "time : < "
					msgTmp += getSocket_nowTimesTime;
					msgTmp += " >"
					msgTmp += "</div>"
					msgTmp += "</div>"
					
				$("#chat_form").append(msgTmp);
				
					
				
			}else{				
				var msgTmp = "<div class='chat_msg_your_form'>"
					msgTmp += "<div class='chat_yourLog'>"
					msgTmp += "<div class='chat_yourprofile'>"
					msgTmp += "<div class='chat_yourimg'>"
					msgTmp += "<img class='img' src='/memberimg/" + getSocket_member_img + "' >"
					msgTmp += "</div>"
					msgTmp += "<div class='chat_yourname'>"
					msgTmp += getSocket_member_id;
					msgTmp += "</div>"
					msgTmp += "</div>"
					msgTmp += "<div class='chat_yourmsg'>"
					msgTmp += getSocket_msg;
					msgTmp += "</div>"
					msgTmp += "<div class='chat_yourTime'>"
					msgTmp += "time : < "
					msgTmp += getSocket_nowTimesTime;
					msgTmp += " >"
					msgTmp += "</div>"
					msgTmp += "</div>"
					
				$("#chat_form").append(msgTmp);	
					
	
			}	
					
		}else if(getSocket_division == "file"){
			if(getSocket_member_no == member_no){
			
				var msgTmp = "<div class='chat_msg_my_form'>"
					msgTmp += "<div class='chat_myLog'>"
					msgTmp += "<div class='chat_myprofile'>"
					msgTmp += "<div class='chat_myimg'>"
					msgTmp += "<img class='img' src='/memberimg/" + getSocket_member_img + "' >"
					msgTmp += "</div>"
					msgTmp += "<div class='chat_myname'>"
					msgTmp += getSocket_member_id;
					msgTmp += "</div>"
					msgTmp += "</div>"
					msgTmp += "<div class='chat_original_filename'>"
					msgTmp += getSocket_original_filename
					msgTmp += "</div>"
					msgTmp += "<div class='chat_mymsgFile'>"
					msgTmp += "<img class='img' src='/chatfile/"+getSocket_msg+"'>"
					msgTmp += "</div>"
					msgTmp += "<div class='chat_file_bottom'>"
					msgTmp += "<div class='chat_myTime'>"
					msgTmp += "time : < "
					msgTmp += getSocket_nowTimesTime;
					msgTmp += " >"
					msgTmp += "</div>"
					msgTmp += "<form method='POST' action='/downloadChatFormFile'>"
					msgTmp += "<input type='hidden' name='chat_filelist_original_filename' value='" + getSocket_original_filename + "'>"
					msgTmp += "<input type='hidden' name='chat_filelist_filename' value='" + getSocket_msg + "'>"
					msgTmp += "<input type='submit' value='다운로드'>"
					msgTmp += "</form>"
					msgTmp += "</div>"
					msgTmp += "</div>"				
					
				$("#chat_form").append(msgTmp);	
				
			}else{
				var msgTmp = "<div class='chat_msg_your_form'>"
					msgTmp += "<div class='chat_yourLog'>"
					msgTmp += "<div class='chat_yourprofile'>"
					msgTmp += "<div class='chat_yourimg'>"
					msgTmp += "<img class='img' src='/memberimg/" + getSocket_member_img + "' >"
					msgTmp += "</div>"
					msgTmp += "<div class='chat_yourname'>"
					msgTmp += getSocket_member_id;
					msgTmp += "</div>"
					msgTmp += "</div>"
					msgTmp += "<div class='chat_original_filename'>"
					msgTmp += getSocket_original_filename
					msgTmp += "</div>"
					msgTmp += "<div class='chat_yourmsgFile'>"
					msgTmp += "<img class='img' src='/chatfile/"+getSocket_msg+"'>"
					msgTmp += "</div>"
					msgTmp += "<div class='chat_file_bottom'>"
					msgTmp += "<div class='chat_yourTime'>"
					msgTmp += "time : < "
					msgTmp += getSocket_nowTimesTime;
					msgTmp += " >"
					msgTmp += "</div>"
					msgTmp += "<form method='POST' action='/downloadChatFormFile'>"
					msgTmp += "<input type='hidden' name='chat_filelist_original_filename' value='" + getSocket_original_filename + "'>"
					msgTmp += "<input type='hidden' name='chat_filelist_filename' value='" + getSocket_msg + "'>"
					msgTmp += "<input type='submit' value='다운로드'>"
					msgTmp += "</form>"
					msgTmp += "</div>"
					msgTmp += "</div>"
					
				$("#chat_form").append(msgTmp);		
							
			}
		}
		}
		// scroll bottom
		var scrolldiv = document.getElementById("chat_form");
		scrolldiv.scrollTop = scrolldiv.scrollHeight;
		
		// enter
		document.addEventListener("keypress", function(e) {
			if (e.keyCode == 13) { //enter press
				send();
			}
		});	
		
	}
}

