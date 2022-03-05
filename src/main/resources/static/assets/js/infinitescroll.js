/*$(window).scroll(function(){
	if($(window).scrollTop() == $(document).height() - $(window).height()) {
		alert("End window")
	}
	
})*/


$("#maincontent").scroll(function(){
	var elem = $("#maincontent");
	var page = document.querySelector("#page").value;
	
	console.log(page);
	
	if(elem[0].scrollHeight - elem.scrollTop() == elem.outerHeight()){
		
		$.ajax({
			type: "POST",
			url:  "/mainboard",
			data: {
				page: page,		
			},
			dataType: "html",
			success: addMorePostAjax,
			error: function(data){
				alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
			}		
		});
	
	}
	
});

function addMorePostAjax(data, textStatus, jqXHR){
	let post = document.querySelector("#maincontent_list_ajax");
	
	post.insertAdjacentHTML("beforeend", data);
}



