function like(id){
	var post_no = id;
	console.log(post_no);
	
		$.ajax({
			type: "POST",
			url:  "/like",
			data: {
				post_no  : post_no,	
			},
			dataType: "json",
			success: function(response){
				var likecount = document.querySelector('likecount_'+id);
				console.log(likecount);
			},
			error: function(data){
				alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
			}		
		});
		
}