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
				
				var likeCount_plus    = response.likecount+1;
				var likeCount_minus   = response.likecount-1;
				
				console.log("1111111111 : " + response.likestat);
				console.log("2222222222 : " + response.likecount);
				
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