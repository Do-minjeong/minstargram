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



$(".likebtn").on("click", function(){
	var btn_id = $(this).attr("id");
	var post_no = btn_id.substring(btn_id.indexOf("btn")+3, btn_id.length);

	var img = $("#"+btn_id).children();
	var img_class = img.attr("class");
	
	if(img_class.indexOf("red")==0){
		likeOff(post_no);
		img.attr("src", "https://www.flaticon.com/svg/static/icons/svg/1946/1946406.svg")
		img.removeClass("red_like");
		img.addClass("white_like");
	} else if(img_class.indexOf("white")==0) {
		likeOn(post_no);
		img.attr("src","https://www.flaticon.com/svg/static/icons/svg/1946/1946346.svg");
		img.removeClass("white_like");
		img.addClass("red_like");
	}
});


function likeOn(post_no){
	$.ajax({
		type : "POST",
		url : "/like/"+post_no,
		contentType : "application/json; charset=utf-8",
		headers: {
			'X-CSRF-TOKEN': $("#csrf_token").val()
		},
		success : function(data){
			console.log("like On Success");
		},
		error : function(xhr, status, er){
			console.log("xhr: "+xhr+" status: "+status);
			console.log("like On Error");
		}
	});
}

function likeOff(){
	$.ajax({
		type : "DELETE",
		url : "/like/"+post_no,
		contentType : "application/json; charset=utf-8",
		headers: {
			'X-CSRF-TOKEN': $("#csrf_token").val()
		},
		success : function(data){
			console.log("like OFF Success");
		},
		error : function(xhr, status, er){
			console.log("xhr: "+xhr+" status: "+status);
			console.log("like OFF Error");
		}
	});
}

