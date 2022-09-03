
var scrolldiv = document.getElementById("chat_form");
scrolldiv.scrollTop = scrolldiv.scrollHeight;


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
			nowTimes: nowTimes,
			chat_alarm: 1
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
			chat_alarm_cnt: "1",
		},
		success: function(data){
			
		},
		error: function(data){
			
		}
	});
}