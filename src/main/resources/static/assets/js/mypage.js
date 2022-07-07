
function postvisible(){
	document.getElementById('postvisible').className = 'postvisible_on';
	document.getElementById('bookmarkvisible').className = 'bookmarkvisible';
}

function bookmarkvisible(){
	document.getElementById('postvisible').className = 'postvisible';
	document.getElementById('bookmarkvisible').className = 'bookmarkvisible_on';	
}

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