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


// 게시글 좋아요 on,off/ 북마크 on,off / 댓글 좋아요 on,off
$(".likebtn , .bookmarkbtn , .replyLikeBtn").on("click", function(){
	var btn_id = $(this).attr("id");
	var btn_name = btn_id.substring(0, btn_id.indexOf("btn"));
	var no = btn_id.substring(btn_id.indexOf("btn")+3, btn_id.length);

	var img = $("#"+btn_id).children();
	var img_class = img.attr("class");
	
	var type = '', imgSrc = '';
	var rClass = '', aClass = '';
	var url = '', callback = '';
	// 취소
	if(img_class.indexOf("on")==0){
		type = "DELETE";
		rClass = "on_"+btn_name;
		aClass = "off_"+btn_name;		
		if(btn_name == 'like'){
			url = '/like/';
			callback = likeCallback;
			imgSrc = "https://www.flaticon.com/svg/static/icons/svg/1946/1946406.svg";
		} else if(btn_name == 'bookmark'){
			url = '/bookmark/';
			imgSrc = "https://www.flaticon.com/svg/static/icons/svg/1174/1174410.svg";
		} else if(btn_name == "rpLike" ){
			url = "/replyLike/";
			imgSrc = "https://www.flaticon.com/svg/static/icons/svg/865/865991.svg";
		}
	} else if(img_class.indexOf("off")==0) { // 좋아요 추가
		type = "POST";
		rClass = "off_"+btn_name;
		aClass = "on_"+btn_name;
		if(btn_name == 'like'){
			url = '/like/';
			callback = likeCallback;
			imgSrc = "https://www.flaticon.com/svg/static/icons/svg/1946/1946346.svg";
		} else if(btn_name == 'bookmark'){
			url = '/bookmark/';
			imgSrc = "https://www.flaticon.com/svg/static/icons/svg/1174/1174447.svg";
		} else if(btn_name == "rpLike"){
			url = "/replyLike/";
			imgSrc = "https://www.flaticon.com/svg/static/icons/svg/865/865974.svg";
		}
	}
	subAjaxFunc(type, url, no, null, "text", btn_name, callback);
	img.attr("src", imgSrc);
	img.removeClass(rClass);
	img.addClass(aClass);
	
});

var likeCallback = function(post_no, data){
	console.log("like callback");
	$("#likeval"+post_no).text(data);
}


// 댓글달기
$(".comment-form button[type=submit]").on("click", function(e){
	e.preventDefault();
	var tt_id = $(this).prev().attr("id");
	var params = {
					r_contents: $("#"+tt_id).val()
				 };
	var post_no = tt_id.substring(tt_id.indexOf("cmt")+3, tt_id.length);
	
	$("#"+tt_id).val('');
	subAjaxFunc("POST", "/reply/", post_no, params, "json", "reply post", replyCallback);
	
	
});

var replyCallback = function(post_no, data){
	console.log('reply Callback method');
	var str = '<div class="reply"><div class="reply-top">'
			 + '<span class="article-id reply-id"><a href="">'
			 + data.userid + '</a></span>'
			 + '<span class="id-contents"> ' + data.r_contents + '</span></div>'
			 + '<button class="btnNone replyLikeBtn" id="rlikebtn'+data.reply_no+'">'
			 + '<img alt="reply_like" src="https://www.flaticon.com/svg/static/icons/svg/2107/2107952.svg">'
			 + '</button></div>';
	$("#replybox"+post_no).append(str);	
}

function subAjaxFunc(type, url, no, params, dataType, title, callback){
	$.ajax({
		type : type,
		url : url+no,
		data: JSON.stringify(params),
		dataType: dataType,
		contentType : "application/json; charset=utf-8",
		headers: {
			'X-CSRF-TOKEN': $("#csrf_token").val()
		},
		success : function(data){
			console.log(title + " Success");
			console.log(data);
			if(callback)
				callback(no, data);
		},
		error : function(status, error){
			console.log("status: "+status+"  error: "+error);
			console.log(title + " Error");
		}
	});
	
};


/*

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
};
*/
