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
		
		var getSocket_original_filename = parse_msg.original_filename;

		var getSocket_nowTimesarr = getSocket_nowTimes.split(" ");
		var getSocket_nowTimesdate = getSocket_nowTimesarr[0];
		var getSocket_nowTimesTime = getSocket_nowTimesarr[1];

		console.log("1 : " + getSocket_chatroom_no);
		console.log("1 : " + chatroom_no);
		console.log("1 : " + getSocket_member_no);
		console.log("1 : " + member_no);
		
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

function send(){
	var member_no = document.getElementById("my_no").value;
	var member_id = document.getElementById("my_id").value;
	var member_img = document.getElementById("my_img").value;
	var msg = document.getElementById("chat_holder").value;
	var chatroom_no = document.getElementById("chatroom_no").value;
	var division = "text";

	var today     =  new Date();
	var years     =  today.getFullYear();
	var month     =  today.getMonth() + 1;
	var date      =  today.getDate();
	var hours     =  today.getHours();
	var minutes   =  today.getMinutes();
	var seconds   =  today.getSeconds();		
	var nowTimes = years + "-" + (("00"+month.toString()).slice(-2)) + "-" + (("00"+date.toString()).slice(-2)) + " " + (("00"+hours.toString()).slice(-2)) + ":" + (("00"+minutes.toString()).slice(-2)) + ":" + (("00"+seconds.toString()).slice(-2));  
	
	// 채팅 공백 시 응답 X
	var chatform_nullcheck = document.querySelector("#chat_holder");
	
	if(chatform_nullcheck.value != ""){
		var param = {
			member_no: member_no,
			member_id: member_id,
			member_img: member_img,
			msg: msg,
			chatroom_no: chatroom_no,
			division: division,
			nowTimes: nowTimes
		}
		ws.send(JSON.stringify(param));
		document.getElementById("chat_holder").value = "";
		
		AjaxInsertChatLog(member_no, chatroom_no, msg, division, nowTimes);
		
	}
	
}

function sendfile(){
	var member_no = document.getElementById("my_no").value;
	var member_id = document.getElementById("my_id").value;
	var member_img = document.getElementById("my_img").value;
	var path_original_file_name = document.getElementById("chat_file").value;
	
	// 경로 제거 
	var split_original_file_name = path_original_file_name.split('\\').reverse();
	
	// 파일명+확장자 
	var original_file_name = split_original_file_name[0];
	console.log("split---- : " + original_file_name);
	
	// 파일명 
	var real_original_file_name = split_original_file_name[0].split('.')[0]; 
	console.log("파일명 : " + real_original_file_name);
	
	// 확장자 
	var exex = split_original_file_name[0].split('.')[1];	
	
	
	var chatroom_no = document.getElementById("chatroom_no").value;
	var division = "file";
	
	var today     =  new Date();
	var years     =  today.getFullYear();
	var month     =  today.getMonth() + 1;
	var date      =  today.getDate();
	var hours     =  today.getHours();
	var minutes   =  today.getMinutes();
	var seconds   =  today.getSeconds();		
	var nowTimes = years + "-" + (("00"+month.toString()).slice(-2)) + "-" + (("00"+date.toString()).slice(-2)) + " " + (("00"+hours.toString()).slice(-2)) + ":" + (("00"+minutes.toString()).slice(-2)) + ":" + (("00"+seconds.toString()).slice(-2));  

	var chat_file = new FormData($("#sendfile")[0]);
		
	$.ajax({
		type: 'POST',
		url: '/upload_chat_file',
		data: chat_file,
        enctype: 'multipart/form-data',
	    processData: false,
	    contentType: false,
	    cache: false,
	    dataType: "json",
	    success: function(response){
			var filepk = response.chat_filelist_no;
			var msg = response.chat_filename;
				
				var param = {
					member_no: member_no,
					member_id: member_id,
					member_img: member_img,
					msg: msg,
					original_filename: original_file_name,
					chatroom_no: chatroom_no,
					division: division,
					nowTimes: nowTimes
				}
				
				ws.send(JSON.stringify(param));
				document.getElementById("chat_file").value = "";
				
				AjaxInsertChatLog(member_no, chatroom_no, msg, division, nowTimes);
		},
		error: function(data){
			
		}
		
	});

}

function AjaxInsertChatLog(member_no, chatroom_no, msg, division, nowTimes){
	
	var nowTimesarr = nowTimes.split(" ");
	var nowTimesdate = nowTimesarr[0];
	var nowTimestime = nowTimesarr[1];
	
	$.ajax({
		type: 'POST',
		url: '/chat/m/' + chatroom_no,
		data: {
			member_no: member_no,
			chatroom_no: chatroom_no,
			msg: msg,
			nowTimesdate: nowTimesdate,
			nowTimestime: nowTimestime,
			division: division,
			nowTimes: nowTimes,
		},
		success: function(data){
			
		},
		error: function(data){
			
		}
	});
}
