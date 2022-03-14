var count = 0;

$(window).scroll(function(){
	var page = document.querySelector("#page").value;

	console.log($(window).scrollTop() == $(document).height() - $(window).height());	
	if($(window).scrollTop() == $(document).height() - $(window).height()) {
		count++;
		
		$.ajax({
			type: "POST",
			url:  "/mainboard",
			data: {
				page  : page,	
				count : count,	
			},
			dataType: "html",
			success: addMorePostAjax,
			error: function(data){
				alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
			}		
		});
		
	}
	
})

	
/*$("#maincontent").scroll(function(){
	var elem = $("#maincontent");
	var page = document.querySelector("#page").value;
	
	console.log(page);
	
	if(elem[0].scrollHeight - elem.scrollTop() == elem.outerHeight()){
		count++;
		$.ajax({
			type: "POST",
			url:  "/mainboard",
			data: {
				page  : page,	
				count : count,	
			},
			dataType: "html",
			success: addMorePostAjax,
			error: function(data){
				alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
			}		
		});
	
	}
	
});*/

function addMorePostAjax(data, textStatus, jqXHR){
	let post = document.querySelector("#maincontent_list_ajax");
	
	post.insertAdjacentHTML("beforeend", data);
}



