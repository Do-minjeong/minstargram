// 댓글 입력창에 한글자라도 입력이 되어있어야 버튼 활성화
$(".comment-form textarea").keyup(function(key){
	var val = this.value;
	var btn = $(".comment-form button");
	if(val.length==0){
		btn.attr("disabled", true);
	}else{
		btn.attr("disabled", false);
	}
});

// home이 처음 띄워질 때 게시글마다 첫 이미지크기 조절
$(".active").each(function(idx, item){
	setTimeout(function(){
		var firstImage = $(item).children().children();
		var fi_h = firstImage.height();
		//console.log(fi_h);
		if(fi_h>500){
			firstImage.removeClass("w-100");
			firstImage.addClass("h-500");
		}
	}, 650);
});

// 게시글의 양옆으로 이동 버튼클릭시 이동 후 이미지 크기 조절
$(".carousel-control-prev , .carousel-control-next").on("click", function(){
	var active = $(this).parent().children(".carousel-inner");
	setTimeout(function(){
		var activeImage = active.children(".active").children().children();
		var img_h = activeImage.height();
		if(img_h>500) {
			activeImage.removeClass("w-100");
			activeImage.addClass("h-500");
		}
	}, 650);
});	



$(".likebtn , .bookmarkbtn").on("click", function(){
	var btn_id = $(this).attr("id");
	var btn_name = btn_id.substring(0, btn_id.indexOf("btn"));
	var post_no = btn_id.substring(btn_id.indexOf("btn")+3, btn_id.length);

	var img = $("#"+btn_id).children();
	var img_class = img.attr("class");
	
	var type = '', imgSrc = '';
	var rClass = '', aClass = '';
	// 취소
	if(img_class.indexOf("on")==0){
		type = "DELETE";
		if(btn_name == 'like'){
			likeOnOff(type, post_no);
			imgSrc = "https://www.flaticon.com/svg/static/icons/svg/1946/1946406.svg";
			rClass = "on_"+btn_name;
			aClass = "off_"+btn_name;
		} else if(btn_name == 'bookmark'){
			bookmarkOnOff(type, post_no);
			imgSrc = "https://www.flaticon.com/svg/static/icons/svg/1946/1946422.svg";
			rClass = "on_"+btn_name;
			aClass = "off_"+btn_name;
		}
	} else if(img_class.indexOf("off")==0) { // 좋아요 추가
		type = "POST";
		if(btn_name == 'like'){
			likeOnOff(type, post_no);
			imgSrc = "https://www.flaticon.com/svg/static/icons/svg/1946/1946346.svg";
			rClass = "off_"+btn_name;
			aClass = "on_"+btn_name;
		} else if(btn_name == 'bookmark'){
			bookmarkOnOff(type, post_no);
			imgSrc = "https://www.flaticon.com/svg/static/icons/svg/1946/1946379.svg";
			rClass = "off_"+btn_name;
			aClass = "on_"+btn_name;
		}
	}
	
	img.attr("src", imgSrc);
	img.removeClass(rClass);
	img.addClass(aClass);
	
});


function likeOnOff(type, post_no){
	$.ajax({
		type : type,
		url : "/like/"+post_no,
		contentType : "application/json; charset=utf-8",
		headers: {
			'X-CSRF-TOKEN': $("#csrf_token").val()
		},
		success : function(data){
			console.log("like OnOff Success");
			$("#likeval"+post_no).text(data);
		},
		error : function(xhr, status, er){
			console.log("xhr: "+xhr+" status: "+status);
			console.log("like On Error");
		}
	});
}

function bookmarkOnOff(type, post_no){
	$.ajax({
		type : type,
		url : "/bookmark/"+post_no,
		contentType : "application/json; charset=utf-8",
		headers: {
			'X-CSRF-TOKEN': $("#csrf_token").val()
		},
		success : function(data){
			console.log("boomark OnOff Success");
		},
		error : function(xhr, status, er){
			console.log("xhr: "+xhr+" status: "+status);
			console.log("boomark On Error");
		}
	});
}

