window.addEventListener('scroll', function(){
	if(window.pageYOffset > 300){
		document.getElementById('sidebar_side').className = "sidebar_side_on";
	}else{
		document.getElementById('sidebar_side').className = "sidebar_side";
	}
});

function like(id){
	var post_no = id.split('_');
	
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

function likecomment(id, id1){
	var comment_no = id.split('_');
	console.log(comment_no[0]);
	console.log("1 : " + comment_no[1]);
	console.log("2 : " + id1);
	
	$.ajax({
		type: "POST",
		url: "/likecomment",
		data: {
			comment_no: comment_no[1],
			post_no: id1,
		},
		dataType: "json",
		success: function(response){
			console.log(id);
			var commentlikeCount_id       = 'commentLike_'+comment_no[1];
			var trim_commentlikeCount_id  = commentlikeCount_id.trim(); 
			
			console.log("rrr : " + response.likecommentlogic);
			
			if(response.comment_likestat == 0){
				document.getElementById(id).className = 'icon_comment_heart_on';
//				document.getElementById(trim_commentlikeCount_id).innerText = "좋아요 " + response.likecount + " 개";

			}else{
				document.getElementById(id).className = 'icon_comment_heart';
//				document.getElementById(trim_commentlikeCount_id).innerText = "좋아요 " + response.likecount + " 개";
			}			
		},
		error: function(data){
			
		}
	})
}

function follow(id){
	var follow_member_no = id.split('_');
	
		$.ajax({
			type: "POST",
			url:  "/follow",
			data: {
				follow_member_no : follow_member_no[1],	
			},
			dataType: "json",
			success: function(response){
				var Followstat_id       = 'follow_'+follow_member_no[1];
				var trim_Followstat_id  = Followstat_id.trim(); 

				if(response.followcheck == 0){
					var followcancel = document.querySelectorAll("#" + trim_Followstat_id);

					for(var i = 0; i < followcancel.length; i++){
						followcancel[i].innerHTML = `<a>팔로우 취소</a>`;
					}
					
					let follow_list = document.querySelector(".my_follow_list_elem");
					
					var follow_template =  "<div id='follow_list'" + "class=' my_follow_list_" + follow_member_no[1] + " '>"
						follow_template += "<img class='header_profileimg' src='/memberimg/" + response.follow_member_info.member_profileimg.member_profileimg_filename + "'>"
						follow_template += "<h4 class='follow_id'>"
						follow_template += "<a href=' /mypage/" + response.follow_member_info.member_id + "'>" + response.follow_member_info.member_id
						follow_template += "</h4>"
					
					follow_list.insertAdjacentHTML("beforeend", follow_template);					
					
				}else{
					var followcancel = document.querySelectorAll("#" + trim_Followstat_id);

					for(var i = 0; i < followcancel.length; i++){
						followcancel[i].innerHTML = `<a>팔로우</a>`;
					}
								
					let follow_cancel_no = document.querySelector(".my_follow_list_"+follow_member_no[1]);
					follow_cancel_no.remove();
					console.log(follow_cancel_no);		
				}
				 
			},
			error: function(data){
				alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
			}		
		});			
}