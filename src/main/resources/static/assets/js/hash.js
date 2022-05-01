window.addEventListener("DOMContentLoaded", function(){
	
	var ex = document.getElementById('post_content_text').innerText;
	
	var regex = ex.split(/(#[^\s]+)/g)
	
	var ee111;
	
	for(var i = 1; i < regex.length; i++){
		var trim_regex = regex[i].trim();
		
		if(regex[i].startsWith("#")){
			regex[i] = "<a href='/mainboard?search=" + regex[i] + "'>" + regex[i] + "</a>";
		}else{
			regex[i] = regex[i];
		}
		console.log(i);
		var zz = regex[i];
		
		console.log("zzzz : " + zz);
		ee111 += zz;
	}
	
	console.log(zz);
	
	document.getElementById('post_content_text').innerHTML = ee111;

});