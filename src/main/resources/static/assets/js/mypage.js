

var locationurl = location.search;

if(locationurl[1] == 'b'){
	document.getElementById('bookmarkvisible').style.display = 'block';	
	document.getElementById('postvisible').style.display = 'none';
	
}else{
	document.getElementById('postvisible').style.display = 'block';
	document.getElementById('bookmarkvisible').style.display = 'none';
	
}


/*
function postvisible(){
	console.log("popo");
	document.getElementById('postvisible').className = 'postvisible_on';
	document.getElementById('bookmarkvisible').className = 'bookmarkvisible';
	
	document.getElementById('postvisible').style.display = 'block';
	document.getElementById('bookmarkvisible').style.display = 'none';
	
	var my_id = document.getElementById('my_id').value;
	window.location.href="/mypage/" + my_id + "?postpage=1";
}

function bookmarkvisible(){
	document.getElementById('postvisible').className = 'postvisible';
	document.getElementById('bookmarkvisible').className = 'bookmarkvisible_on';

	document.getElementById('bookmarkvisible').style.display = 'block';	
	document.getElementById('postvisible').style.display = 'none';
	
	var my_id = document.getElementById('my_id').value;
	window.location.href="/mypage/" + my_id + "?bookmarkpage=1";	
}
*/

function follow(){
	let member_no =  document.getElementById('member_no').value;
	
		$.ajax({
		type: "POST",
		url:  "/follow",
		data: {
			follow_member_no : member_no,	
		},
		dataType: "json",
		success: function(response){
			if(response.followcheck == 0){
				document.getElementById('follow_check').value = '팔로우 취소';
			}else{
				document.getElementById('follow_check').value = '팔로우';
			}
			
		},
		error: function(data){
			alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
		}		
	});	
}