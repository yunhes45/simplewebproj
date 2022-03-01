window.addEventListener("DOMContentLoaded", function(){

	/* canvas */
	const canvas   = document.getElementById('writepost_imagecanvas');
	const context  = canvas.getContext('2d');
	
	const filechange   = document.getElementById('postimg');
	filechange.addEventListener('change', function(evt){
		let reader      = new FileReader();
		reader.onload   = function(e){
			postimg          = new Image();
			postimg.src      = e.target.result;
			postimg.onload   = function(){
				context.drawImage(postimg, 0, 0, 300, 150);
				context.restore();
			}
		};
		reader.readAsDataURL(evt.target.files[0]);
	});	

});