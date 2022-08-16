window.addEventListener("DOMContentLoaded", function(){
	
	var oriimg = document.getElementById('memimg').value;
	
	var imgsplit = oriimg.split('/');
	
	img = imgsplit[2];
	
	/* canvas */
	const canvas   = document.getElementById('imagecanvas');
	const context  = canvas.getContext('2d');
	
	var origin_memberimg = new Image();
	origin_memberimg.src = "memberimg/" + img;
	origin_memberimg.addEventListener("load", () => {
		context.drawImage(origin_memberimg, 0, 0, 300, 150);
	})
	
	const filechange   = document.getElementById('memberimg');
	filechange.addEventListener('change', function(evt){
		let reader      = new FileReader();
		reader.onload   = function(e){
			memberimg          = new Image();
			memberimg.src      = e.target.result;
			memberimg.onload   = function(){
				context.drawImage(memberimg, 0, 0, 300, 150);
				context.restore();
			}
		};
		reader.readAsDataURL(evt.target.files[0]);
	});
	
	var member_id          = document.getElementById('member_id');
	var member_email       = document.getElementById('member_email');
	var member_pwd         = document.getElementById('member_pwd');
	var member_pwd_check   = document.getElementById('member_pwd_check');
	var member_nickname    = document.getElementById('member_nickname');
	var member_mobile      = document.getElementById('member_mobile');
	var member_job         = document.getElementById('member_job');
	var copy               = document.getElementById('member_copy');
	
	member_id.addEventListener('keyup', function(){
		if(member_id.value == "" || member_id.value.length == 0){
			setErrorMessage("member_id_error", "아이디를 입력해 주세요.");
		}else{
			var pattern = /[^a-zA-Z0-9]/;
				if(pattern.test(member_id.value)){
					setErrorMessage("member_id_error", "아이디는 영어와 숫자의 조합만 가능합니다.");
				}else{
					removeErrorMessage("member_id_error");
				}
		}
	});
	
	member_email.addEventListener('keyup', function(){
		var pattern = /^[a-zA-Z0-9]+@([a-zA-Z0-9]+\.)+[a-zA-Z0-9]+$/;
			if(!pattern.test(member_email.value)){
				setErrorMessage("member_email_error", "이메일 형식으로 입력해주세요.")
			}else{
				removeErrorMessage("member_email_error");
			}
	});
	
	member_pwd.addEventListener('keyup', function(){
		document.getElementById('member_pwd_error').className = "error";
		var level = passwordLevel(member_pwd.value);
			if(level == 0){
				setErrorMessage("member_pwd_error", "비밀번호를 입력해 주세요.");
			}else if(level == 1){
				setErrorMessage("member_pwd_error", "비밀번호는 영어,숫자,특수문자(~!@#$%^&*()_+|<>?{})만 가능합니다.");
			}else if(level == 2){
				setErrorMessage("member_pwd_error", "너무 쉬운 암호입니다.");
			}else if(level == 3){
				setErrorMessage("member_pwd_error", "더 강력한 조합이 필요합니다.");
			}else if(level == 4){
				document.getElementById('member_pwd_error').className = "non_error";
				setErrorMessage("member_pwd_error", "안전한 암호입니다.");
			}
			
			if(member_pwd.value != member_pwd_check.value){
				setErrorMessage("member_pwd_check_error", "비밀번호를 확인해 주세요.");
			}else{
				removeErrorMessage("member_pwd_check_error");
			}
	});
	
	member_pwd_check.addEventListener('keyup', function(){
		if(member_pwd.value != member_pwd_check.value){
			setErrorMessage("member_pwd_check_error", "비밀번호를 확인해 주세요.");
		}else{
			removeErrorMessage("member_pwd_check_error");
		}
	});

	member_nickname.addEventListener('keyup', function(){
		if(member_nickname.value == ""){
			setErrorMessage("member_nickname_error", "이름을 입력해 주세요.");
		}else{
			removeErrorMessage("member_nickname_error");
		}
	});	
	
	member_mobile.addEventListener('keyup', function(){
		var pattern = /^(?:(010-\d{4})|(01[1|6|7|8|9]-\d{3,4}))-(\d{4})$/;
			if(!pattern.test(member_mobile.value)){
				setErrorMessage("member_mobile_error", "-을 포함한 휴대폰 형식으로 입력해주세요.")
			}else{
				removeErrorMessage("member_mobile_error");
			}
	});

	member_job.addEventListener('keyup', function(){
		if(member_job.value == ""){
			setErrorMessage("member_job_error", "직업을 선택하세요.");
		}else{
			removeErrorMessage("member_job_error");
		}
	});
/*	
	member_copy.addEventListener('keyup', function(){
		if(!member_copy.checked){
			setErrorMessage("member_copy_error", "개인정보수집에 동의해야 회원가입이 가능합니다.");
		}else{
			removeErrorMessage("member_copy_error");
		}
	});
	*/
	document.getElementById('signup_btn').addEventListener('click', function(evt){
		if(member_id.value == "" || member_id.value.length == 0){
			evt.preventDefault();
			setErrorMessage("member_id_error", "아이디를 입력해 주세요.");
		}else{
			var pattern = /[a-zA-Z0-9]/;
				if(!pattern.test(member_id.value)){
					evt.preventDefault();
					setErrorMessage("member_id_error", "아이디는 영어와 숫자의 조합만 가능합니다.");
				}
		}
		
		var pattern = /^[a-zA-Z0-9]+@([a-zA-Z0-9]+\.)+[a-zA-Z0-9]+$/;
			if(!pattern.test(member_email.value)){
				evt.preventDefault();
				setErrorMessage("member_email_error", "이메일 형식으로 입력해 주세요.");
			}
		
		document.getElementById('member_id_error').className = "error";
		var level = passwordLevel(member_pwd.value);
			if(level == 0){
				evt.preventDefault();
				setErrorMessage("member_pwd_error", "비밀번호를 입력해 주세요.");
			}else if(level == 1){
				evt.preventDefault();
				setErrorMessage("member_pwd_error", "비밀번호는 영어,숫자,특수문자(~!@#$%^&*()_+|<>{})만 가능합니다.");
			}else if(level == 2){
				evt.preventDefault();
				setErrorMessage("member_pwd_error", "너무 쉬운 암호입니다.");
			}else if(level == 3){
				evt.preventDefault();
				seterrorMessage("member_pwd_error", "더 강력한 조합이 필요합니다.");
			}else if(level == 4){
				document.getElementById('member_pwd_error').className = "non_error";
				setErrorMessage("member_pwd_error", "안전한 암호 입니다.");
			}
			
		if(member_pwd.value != member_pwd_check.value){
			evt.preventDefault();
			setErrorMessage("member_pwd_check", "비밀번호를 확인해 주세요.");
		}
		
		if(member_mobile.value == ""){
			evt.preventDefault();
			setErrorMessage("member_mobile_error", "휴대폰 번호를 입력해 주세요.");
		}
		
		if(member_nickname.value == ""){
			evt.preventDefault();
			setErrorMessage("member_nickname_error", "이름을 입력해 주세요.");
		}

		if(member_job.value == ""){
			evt.preventDefault();
			setErrorMessage("member_job_error", "직업을 선택하세요.");
		}
/*		
		if(!member_copy.checked){
			evt.preventDefault();
			setErrorMessage("member_copy_error", "개인정보수집에 동의해야 회원가입이 가능합니다.");
		}
		*/
	});
	
});