/*window.addEventListener("DOMContentLoaded", function(){
	
	var post_content_text = document.getElementById('post_content_text').innerText;
	
	var regex = post_content_text.split(/(#[^\s]+)/g)
	
	var regex_text;
	
	for(var i = 1; i < regex.length; i++){
		var trim_regex = regex[i].trim();
		
		if(regex[i].startsWith("#")){
			regex[i] = "<a href='/mainboard?search=" + regex[i] + "'>" + regex[i] + "</a>";
		}else{
			regex[i] = regex[i];
		}
		var loop_text = regex[i];

		regex_text += loop_text;
	}
	
	document.getElementById('post_content_text').innerHTML = regex_text;

});*/