function like(id){
	var post_no = id.split('_');
	console.log(post_no[0]);
	console.log("1 : " + post_no[1]);
	
		$.ajax({
			type: "POST",
			url:  "/like",
			data: {
				post_no : post_no[1],	
			},
			dataType: "json",
			success: function(response){
				var likeCount_id       = 'likeCount_'+post_no[1];
				var trim_likeCount_id  = likeCount_id.trim(); 
				
				if(response.likestat == 0){
					document.getElementById(id).className = 'icon_heart_sprite_on';
					document.getElementById(trim_likeCount_id).innerText = "좋아요 " + response.likecount + " 개";

				}else{
					document.getElementById(id).className = 'icon_heart_sprite';
					document.getElementById(trim_likeCount_id).innerText = "좋아요 " + response.likecount + " 개";
				}
				 
			},
			error: function(data){
				alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
			}		
		});	
}

function comment(id, comment_value){
	var comment_text = comment_value.value;
	var resetValue = comment_value;
	resetValue.value = null;
	
		$.ajax({
			type: "POST",
			url:  "/comment",
			data: {
				post_no        :  id,
				comment_text   :  comment_text,	
			},
			dataType: "html",
			success: function(response){
				console.log(id);
				let comment = document.querySelector("#comment_"+id);
				comment.insertAdjacentHTML("beforeend", response);
			},
			error: function(data){
				alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
			}		
		});	
		
}
