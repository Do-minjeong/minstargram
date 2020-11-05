console.log("hello");
var inputTog = false;
var pwbtnTog = false;
var num_regex = /^(\s|\d)+$/;
var email_regex = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
var phone_regex = /^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/;
var id_regex = /^[A-Za-z0-9_\\.]{1,}$/;
$(".signupLabel input").keyup(function(key){
	var val = this.value;
	if(val.length==0){
		$(this).parent().next().children(".signupImage:not(.inputRefresh)").remove();
		$(this).prev().hide(100);
		$(this).css("height","38px");
		inputTog = false;
	}else if(!inputTog && val.length>0){
		$(this).prev().show(100);
		$(this).css("height","30px");
		inputTog = true;
	}
 });

var refreshIconTog = false;
$(".signupLabel input").focusout(function(){
	inputTog = false;
	var check = 0;
	var name = $(this).attr("name");
	var value = $(this).val();
	if(value.length>0){
		if(name == 'username'){
			check = usernameCheck(value);
			console.log(refreshIconTog);
			if(value.length>=4 && !refreshIconTog && usernameType == 1){
				displayIcon(3);
				refreshIconTog = true;
			} else if(value.length<4 || usernameType == 0) {
				displayIcon(4);
				refreshIconTog = false;
			}
		}else if(name == 'name'){
			
			check = 1;
		}else if(name == 'userid'){
			check = idCheck(value);
		}else if(name == 'password'){
			check = passwordCheck(value);
		}
	}else if(value.length == 0 && name != 'userid')	check = 5;
	console.log("name: "+value+"  check : "+check);
	displayIcon(check, this);
	
	var successNum = $(".inputSuccess").length;
	if(successNum==4 || (successNum==3 && $("input[name=name]").val()=='')) $("#submitBtn").attr("disabled", false);
	else $("#submitBtn").attr("disabled", true);
});

function displayIcon(check, obj){
	var errorTag = '<div class="signupImage inputError"></div>';
	var successTag = '<div class="signupImage inputSuccess"></div>';
	var refreshTag = '<div class="signupImage inputRefresh"></div>';
	if(check == 1){
		$(obj).parent().next().children(".signupImage:not(.inputRefresh)").remove();
		$(obj).parent().next().prepend(successTag);
	}else if(check == 2){
		$(obj).parent().next().children(".signupImage:not(.inputRefresh)").remove();
		$(obj).parent().next().prepend(errorTag);		
	}else if(check == 3){
		$("input[name=userid]").parent().next().append(refreshTag);
	}else if(check == 4){
		$("input[name=userid]").parent().next().children(".inputRefresh").remove();
	}else if(check == 5){
		$(obj).parent().next().children(".signupImage:not(.inputRefresh)").remove();
	}else{
		return;
	}
}

var usernameType;
function usernameCheck(username){
	
	if(num_regex.test(username)) usernameType = 0; // 숫자로만 이루어져 있을 때
	else usernameType = 1;
	
	if(email_regex.test(username) || phone_regex.test(username)){
		/* ajax를 이용한 username 중복체크 */
		if(dupCheckUN()){  /* 중복된 아이디가 없는 경우*/
			errorMsgEmpty();
			return 1;
		}
		$(".regexError").html(username+"은 중복됩니다.");
	}else{
		if(usernameType == 0 && !phone_regex.test(username)){
			$(".regexError").html("휴대폰 번호가 정확하지 않습니다. 국가 번호를 포함하여 전체 전화번호를 입력해주세요.");
		}else{
			$(".regexError").html("유효한 이메일 주소를 입력하세요 ");
		}
	}
	return 2;
}

function dupCheckUN(){
	console.log("ajax를 이용하여 username 중복체크");
	return true; // username 중복됨
}

function idCheck(id){
	if(id_regex.test(id)){
		if(dupCheckID()){
			errorMsgEmpty();
			return 1;
		}
		$(".regexError").html("사용할 수 없는 ID 입니다. 다른 이름을 사용하세요.");
	}else{
		console.log(id);
		$(".regexError").html("아이디는 영문자, 숫자, 밑줄과 마침표만 가능합니다");
	}
	return 2;
}

function dupCheckID(){
	console.log("ajax를 이용하여 id 중복체크");
	return true; // 아이디가 중복됨
}

function passwordCheck(pwd){
	if(pwd.length>=6){
		errorMsgEmpty();
		return 1;
	}
	else{
		$(".regexError").html("비밀번호를 6자 이상 입력하세요.");
		return 2;
	}
}

function errorMsgEmpty(){
	$(".regexError").html("");
}

$(".pwBtn").on("click",function(e){
	e.preventDefault();
	if(!pwbtnTog){
		$(this).html("숨기기");
		$("input[name=password]").attr("type","text");
		pwbtnTog = true;
	}else{
		$(this).html("비밀번호표시");
		$("input[name=password]").attr("type","password");		
		pwbtnTog = false;
	}
});


$("#updateIdForm").focusout(function(){
	var val = $("input[name=userid]").val();
	var check = 0;
	if(val.length>0){
		check = idCheck(val);
		displayIcon(check, this);
		if(check==1) $("#submitBtn").attr("disabled", false);
		else $("#submitBtn").attr("disabled", true);
	}
});



