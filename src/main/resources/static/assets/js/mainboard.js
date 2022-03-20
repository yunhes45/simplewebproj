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

function bookmark(id){
	var post_no = id.split("_");
	console.log(post_no[0]);
	console.log(post_no[1]);
	
		$.ajax({
			type: "POST",
			url:  "/bookmark",
			data: {
				post_no : post_no[1],	
			},
			dataType: "json",
			success: function(response){
				var bookmarkCount_id       = 'bookmarkCount_'+post_no[1];
				var trim_bookmarkCount_id  = bookmarkCount_id.trim(); 
				
				if(response.bookmarkstat == 0){
					document.getElementById(id).className = 'icon_bookmark_sprite_on';
					document.getElementById(trim_bookmarkCount_id).innerText = "북마크 " + response.bookmarkcount + " 개";

				}else{
					document.getElementById(id).className = 'icon_bookmark_sprite';
					document.getElementById(trim_bookmarkCount_id).innerText = "북마크 " + response.bookmarkcount + " 개";
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

function delcomment(id){
	$.ajax({
		type: "POST",
		url:  "/delcomment",
		data: {
			comment_no :  id,
		},
		success: function(response){
			var comment_item       = 'comment_item_'+response;
			var trim_comment_item  = comment_item.trim(); 
			
			$("#"+trim_comment_item).remove();
			
		},
		error: function(data){
			
		}		
	});	
}