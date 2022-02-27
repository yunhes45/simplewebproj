window.addEventListener("DOMContentLoaded", function(){
	function nowTime(){
		var today   = new Date();
		var years   = today.getFullYear();
		var month   = today.getMonth() + 1;
		var date    = today.getDate();
		var hours   = today.getHours();
		var minutes = today.getMinutes();
		var seconds = today.getSeconds();
		
		var time    = years + "-" + (("00" + month.toString().slice(-2)) + "-" + (("00" + date.toString()).slice(-2))
					   + " " + (("00" + hours.toString()).slice(-2)) + ":" + (("00")) + minutes.toString().slice(-2)) + ":" + (("00" + seconds.toString()).slice(-2)); 
		
		var nowTime = document.getElementById('nowTime');
		nowTime.innerText(time.value);
		
		alert(nowTime);
	}
});