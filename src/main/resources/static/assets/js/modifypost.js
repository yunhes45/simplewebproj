window.addEventListener("DOMContentLoaded", function(){
	
	var oriimg = document.getElementById('poimg').value;
	
	var imgsplit = oriimg.split('/');
	
	img = imgsplit[2];
	
	console.log(img);

	/* canvas */
	const canvas   = document.getElementById('writepost_imagecanvas');
	const context  = canvas.getContext('2d');
	
	var origin_postimg = new Image();
	origin_postimg.src = "postimg/" + img;
	origin_postimg.addEventListener("load", () => {
		context.drawImage(origin_postimg, 0, 0, 300, 150);
	})
	
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