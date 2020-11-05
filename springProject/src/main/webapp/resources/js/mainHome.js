
// 댓글 입력창에 한글자라도 입력이 되어있어야 버튼 활성화
$(document).on("keyup", ".comment-form textarea", function(key){
	var val = this.value;
	var btn = $(".comment-form button");
	if(val.length==0){
		btn.attr("disabled", true);
	}else{
		btn.attr("disabled", false);
	}
});

/* Only mainHome */
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

/* Only mainHome */
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
//$(".likebtn , .bookmarkbtn , .replyLikeBtn").on("click", function(){
$(document).on('click', '.likebtn , .bookmarkbtn , .replyLikeBtn', function(){
	var btn_id = $(this).attr("id");
	var btn_name = btn_id.substring(0, btn_id.indexOf("btn"));
	var no = btn_id.substring(btn_id.indexOf("btn")+3, btn_id.length);
	console.log(btn_id);
	
	var img = $("."+btn_id).children();
	var img_class = img.attr("class");
	console.log("ic: "+img_class);
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
			console.log("on 들어옴");
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
			console.log("off 들어옴");
			url = '/bookmark/';
			imgSrc = "https://www.flaticon.com/svg/static/icons/svg/1174/1174447.svg";
		} else if(btn_name == "rpLike"){
			url = "/replyLike/";
			imgSrc = "https://www.flaticon.com/svg/static/icons/svg/865/865974.svg";
		}
	}
	subAjaxFunc(type, url, no, null, "text", btn_name, callback);
	console.log("img: "+img);
	img.attr("src", imgSrc);
	img.removeClass(rClass);
	img.addClass(aClass);
	
});

var likeCallback = function(post_no, data){
	console.log("like callback");
	$("#likes_info"+post_no+" span").text(data);
}

// 댓글달기
$(document).on("click", ".comment-form button[type=submit]", function(e){
	e.preventDefault();
	var tt_id = $(this).prev().attr("id");
	var lc_id = $(this).attr("id");
	var callback = '';

	if(lc_id.indexOf("md")==0)	callback = modalReplyCallback;
	else if (lc_id.indexOf("mh"==0)) callback = replyCallback;
	
	var params = {
					r_contents: $("#"+tt_id).val()
				 };
	var post_no = tt_id.substring(tt_id.indexOf("cmt")+3, tt_id.length);
	
	subAjaxFunc("POST", "/reply/", post_no, params, "json", "reply post", callback);
	$(".comment-form textarea").val("");
	
});

/* Only mainHome */
/* 게시글에서 댓글달기 ajax 후 callback 함수 */
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

/* 모달에서 댓글달기 ajax 후 callback 함수 */
var modalReplyCallback = function(post_no, data){
	console.log('modal reply Callback method');
	var str = "<div class='md-writer'>"
				+	"<div class='modal-userInfo'>"
				+		"<div class='md-canvas canvas displayCenter'>"
				+		"	<div class='md-profile-pic profile-pic'>"
				+			"	<img src='"+data.rp_profile_photo+"'>"
				+	"		</div>"
				+	"	</div>"
			+	"	</div>"
			+	"	<div class='writer-contents-box'>"
			+		"	<div class='md-box1'>"
			+			"	<div >"
			+				"	<span class='md-article-id article-id'><a"
			+			"			href='/main/profile?member_no="+data.member_no+"'>"+data.userid+"</a></span>"
			+			"		<span class='font14'>"+data.r_contents+"</span>"
			+		"		</div>"
			+		"		<button class='btnNone md-replyLikeBtn' id='rpLikebtn"+data.reply_no+"'>"
			+		"			<img alt='reply_like' class='off_rpLike' src='https://www.flaticon.com/svg/static/icons/svg/865/865991.svg'>	"											
			+			"	</button>"
			+		"	</div>"
			+		"	<div class='md-regdate'><span>0분전</span></div>"
			+		"</div>"
		+	"	</div>";
	
	$(".md-rpbox").prepend(str);
}

/* like, bookmark, reply like ajax사용시 호출 */
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
			if(callback){
				if(title.indexOf("info") != -1)
					if(title.indexOf("likes") == 0)
						callback("좋아요", data);
					else if(title.indexOf("followers")==0)
						callback("팔로워", data);
					else if(title.indexOf("followings") == 0 )
						callback("팔로잉", data);
				else
					callback(no, data);
			}
		},
		error : function(status, error){
			console.log("status: "+status+"  error: "+error);
			console.log(title + " Error");
		}
	});
};

/* 좋아요한 회원 리스트 보기 */
$(document).on("click", ".likes_info", function(){
	var this_id = $(this).attr("id");
	var post_no = this_id.substring(this_id.indexOf("info")+4, this_id.length);
	
	subAjaxFunc("GET", "/likes_info/", post_no, null, "json", "likes_info", infoModalCallback);
	
});

var infoModalCallback = function(title, data){
	console.log("like info callback");
	var str = '';
	
	$.each(data, function(idx, item){
		str += "<div class='lk_info_body'>"
			+	 " <div class='lk_info'>"
			+	  "	<div class='md-canvas canvas displayCenter'>"
			+		"	<div class='md-profile-pic profile-pic'>"
			+		"		<img src='"+item.profile_photo+"'> " 
			+		"	</div>"
			+	"	</div>"
			+	"	<div class='lk_info_pf'>"
			+	"		<a class='ANone' href='/main/profile?member_no="+item.member_no+"'>"+item.userid+"</a>"
			+	"		<span>"+item.name+"</span>"
			+	"	</div>"
			+	 " </div>"
			+   "<div class='lk_info_fw'>";
		if(item.relationship > 0){
			if(item.relationship < 2)
				str += "<button type='button' class='btn btnBlue followbtn' id='on_follow"+item.member_no+"'>팔로우하기</button>";
			else 
				str += "<button type='button' class='btn btnGray followbtn' id='off_follow"+item.member_no+"'>팔로우취소</button>";
		}
		str += "<input type='hidden' id='pf_member_no' class='fw_member_no' value='"+item.member_no+"'> "
			+ 	"</div> </div>";
	});
	
	infoModalShow(title, str);
};


var infoModalShow = function(title, content){
	$(".info_md_header").text(title);
	
	$(".info_body").empty();
	$(".info_body").append(content);
	
	$("#info_Modal").modal("show");
	
};

$(document).on("click", ".followbtn", function(){
	var url = '';
	var tg_no = $(this).next().val();
	if ($(this).attr("id").indexOf("on") == 0) {
		url = "/onFollow/" + tg_no;
		profileAjaxFunc("POST", url, null, "text", tg_no, onFollow);
	} else if ($(this).attr("id").indexOf("off") == 0) {
		console.log("off");
		confirmModal_v2(function(confirm){
			if(confirm){
				url = "/offFollow/" + tg_no;
				profileAjaxFunc("DELETE", url, null, "text", tg_no, offFollow);
			} else  return false;
		})					
	}
})

function confirmModal_v2(callback){

	var result = false;
 
	modalConfirm(function(confirm){
		if(confirm == 1){
			result = true;
			callback(result);	
		}else if(confirm == 2){
			result = false;
			callback(result);	
		}
	});			
	
}

var modalConfirm = function (callback){
	
	$("#confirmModal").modal("show");

	$("#confirm-ok").on("click", function(){
		callback(1);
		$("#confirmModal").modal('hide');
	});
	
	$("#confirm-cancle").on("click", function(){
		callback(2);
		$("#confirmModal").modal('hide');
	});
	
};

var onFollow = function(no, data){
	var on_f = $("#on_follow"+no);
	on_f.removeClass("btnBlue");
	on_f.addClass("btnGray");
	on_f.text("팔로우취소");
	on_f.attr("id","off_follow"+no);
};
var offFollow = function(no, data){
	var off_f = $("#off_follow"+no);
	off_f.removeClass("btnGray");
	off_f.addClass("btnBlue");
	off_f.text("팔로우하기");
	off_f.attr("id","on_follow"+no);
};

function profileAjaxFunc(type, url, params, dataType, no, callback){
	$.ajax({
		type : type,
		url : url,
		data: JSON.stringify(params),
		contentType : "application/json; charset=utf-8",
		dataType: dataType,
		headers: {
			'X-CSRF-TOKEN': $("#csrf_token").val()
		},
		success : function(data){
			console.log("success : " + data);
			if(callback)
				callback(no, data);
		},
		error : function(status, error){
			console.log("status: "+status+"  error: "+error);
		}
	});
};

$(document).on("click", ".md-commentbtn", function(){
	$(".md-comment-form textarea").focus();
});


$(document).on("click", ".photo3, .commentbtn", function() {
	var photo_id = $(this).attr("id");
	var post_no = photo_id.substring( photo_id.indexOf("post") + 4, photo_id.length);
	console.log("post_no : " + post_no);
	var params = {
		post_no : post_no
	}
	// ajax로 해당 post 정보가져와서 모달에 뿌리기
	profileAjaxFunc("POST", "/main/getPost", params, "json", null,modalOpen);
});

/* 게시글 모달 띄우기 */
var modalOpen = function(no, data){
	console.log(data);
	var str = "<div class='modal-ct'>"
		+	"<div id='carouselExampleIndicators'"
		+			"class='carousel slide carouselWidth' data-ride='carousel' data-interval='false'>"
		+			"<ol class='carousel-indicators'>";
	$.each(data.attachList, function(idx, item){
		str += 	"	<li data-target='#carouselExampleIndicators'"
			+	"	data-slide-to='"+idx+"' ";
		if(idx==0){
			str += 	"class='active'";
		}
		str += "></li>";
	})
	str	+=			"</ol>"
		+		"	<div class='carousel-inner'>";
	$.each(data.attachList, function(idx, item){
		str += 	"<div class='carousel-item  ";
		if(idx==0){
			str += 	" active ";
		}
		str += "'>"
			+				"	<div class='md-article-img'>"
			+						"<img class='d-block w-100'"
			+					"		alt='"+data.post_no+"-"+idx+" Slide'"
			+				"			src='"+item.total_url+"'>"
			+				"	</div>"
			+  		"</div>"	;

	})			
	str += 		"</div>"			
	+ "<a class='carousel-control-prev'"
	+			"	href='#carouselExampleIndicators' role='button'"
	+			"	data-slide='prev'> <span class='carousel-control-prev-icon'"
	+			"	aria-hidden='true'></span> <span class='sr-only'>Previous</span>"
	+		"	</a> <a class='carousel-control-next'"
	+			"	href='#carouselExampleIndicators' role='button'"
	+			"	data-slide='next'> <span class='carousel-control-next-icon'"
	+				"aria-hidden='true'></span> <span class='sr-only'>Next</span>"
	+		"	</a>"
	+	"	</div>"
	+		"<div class='modal-pf'>"
	+"			<div class='md-article-header article-header'>"
	+			"	<div class='md-canvas canvas displayCenter'>"
	+				"	<div class='md-profile-pic profile-pic'>"
	+					"	<img"
	+						"	src='"+data.profile_photo+"'>"
	+					"</div>"
	+				"</div>"
	+				"<div class='md-smallheader smallheader'>"
	+					"<div class='article-id font14'>"
	+						"<a href='/main/profile?member_no="+data.member_no+"'>"+data.userid+"</a>"
	+					"</div>"
	+					"<button class='btnNone'>"
	+						"<div class='morebtn'>"
	+							"<img alt='더보기'"
	+						"		src='https://www.flaticon.com/svg/static/icons/svg/512/512142.svg'>"
	+						"</div>"
	+				"	</button>"
	+			"	</div>"
	+			"</div>"
	+			"<div class='article-contents article-section md-article-contents'>"
	+			"<div class='md-container'>"
	+				"<div class='md-writer'>"
	+				"	<div class='modal-userInfo'>"
	+			"			<div class='md-canvas canvas displayCenter'>"
	+							"<div class='md-profile-pic profile-pic'>"
	+							"	<img"
	+						"	src='"+data.profile_photo+"'>"
	+						"	</div>"
	+						"</div>"
	+				"	</div>"
	+				"	<div class='writer-contents-box'>"
	+				"		<div class='md-writer-contents'>"
	+							"<span class='md-article-id article-id'><a"
	+							"	href='/main/profile?member_no="+data.member_no+"'>"+data.userid+"</a></span>"
	+							"<span class='font14'>"+data.contents+"</span>"
	+						"</div>"
	+						"<div class='md-regdate'><span>"+data.reg_date+"</span></div>"
	+					"</div>"
	+			"	</div>	"	
	if(data.replyList.length){
		str += "<div class='md-rpbox'>	";
		$.each(data.replyList, function(idx, item){
			str += 		"<div class='md-writer'>"
				+				"	<div class='modal-userInfo'>"
				+				"		<div class='md-canvas canvas displayCenter'>"
				+						"	<div class='md-profile-pic profile-pic'>"
				+								"<img src='"+item.rp_profile_photo+"'>"
				+					"		</div>"
				+					"	</div>"
				+				"	</div>"
				+				"	<div class='writer-contents-box'>"
				+				"		<div class='md-box1'>"
				+						"	<div >"
				+							"	<span class='md-article-id article-id'><a"
				+								"	href='/main/profile?member_no="+item.member_no+"'>"+item.userid+"</a></span>"
				+							"<span class='font14'>"+item.r_contents+"</span>"
				+					"		</div>"
				+					"		<button class='btnNone md-replyLikeBtn replyLikeBtn rpLikebtn"+item.reply_no+"' id='rpLikebtn"+item.reply_no+"'>";
				if(item.r_like_btn == 'true'){
					str += "	<img alt='reply_like' class='on_rpLike' src='https://www.flaticon.com/svg/static/icons/svg/865/865974.svg'>";
				}
				else{
					str += "<img alt='reply_like' class='off_rpLike' src='https://www.flaticon.com/svg/static/icons/svg/865/865991.svg'>";
				}
				str	+=						"	</button>"
				+						"</div>"
				+					"	<div class='md-regdate'><span>"+item.r_reg_date+"</span>";
				if(item.r_like_cnt>0) str += "<span>좋아요 "+item.r_like_cnt+"개</span>";
				str += 	"</div>"
				+					"</div>"
				+				"</div>";
		})
		str += "</div>";
	}
	str	+=		"</div></div>	<div class='modal-ft'>"
		+				"<div class='article-iconbox article-section'>"
		+				"	<button class='article-icon btnNone likebtn likebtn"+data.post_no+"' id='likebtn"+data.post_no+"'>";
	if(data.like_btn == 'true' )
		str += "	<img alt='좋아요' class='on_like' src='https://www.flaticon.com/svg/static/icons/svg/1946/1946346.svg'>	";
	else 
		str += "<img alt='좋아요' class='off_like' src='https://www.flaticon.com/svg/static/icons/svg/1946/1946406.svg'>";

	str +=				"	</button>"
		+					"<button class='article-icon btnNone md-commentbtn' id='commentbtn"+data.post_no+"'>"
		+					"	<img alt='댓글' src='https://www.flaticon.com/svg/static/icons/svg/1946/1946412.svg'>"
		+				"	</button>"
		+				"	<button class='article-icon btnNone messagebtn' id='messagebtn"+data.post_no+"'><img alt='메시지' src='https://www.flaticon.com/svg/static/icons/svg/1946/1946547.svg'></button>"
		+					"<button class='article-icon bookmark btnNone bookmarkbtn bookmarkbtn"+data.post_no+"' id='bookmarkbtn"+data.post_no+"'>";
	if(data.bookmark_btn == 'true')
		str += "<img alt='북마크' class='on_bookmark' src='https://www.flaticon.com/svg/static/icons/svg/1174/1174447.svg'>	";
	else 
		str += "<img alt='북마크' class='off_bookmark' src='https://www.flaticon.com/svg/static/icons/svg/1174/1174410.svg'>";

	str +=		"</button>"
		+				"</div>"
		+				"<div class='article-likes article-section'>"
		+				"	<button class='likes_info btnNone font14' id='likes_info"+data.post_no+"'>"
		+				"		좋아요 <span>"+data.like_cnt+"</span>개"
		+				"	</button>"
		+			"	</div>"
		+				"<div class='md-regdate'><span>"+data.reg_date+"</span></div>"
		+			"</div>"
		+			"<div class='modal-rp'>"
		+			"	<form action='' class='md-comment-form comment-form displayCenter'>"
		+			"		<textarea id='cmt"+data.post_no+"' name='r_contents' wrap='virtual' cols='38' class='btnNone' placeholder='댓글 달기..'></textarea>"
		+			"		<button type='submit' id='md-cf' class='btnNone' disabled>게시</button>"
		+			"	</form>"
		+		"	</div>"
		+		"</div>"
		+	"</div>"
		+	"	</div>";
	$(".md-modal-body").empty();
	$(".md-modal-body").append(str);
	$("#myModal").modal();
}

/* Only profile */
var postsMenu = function(no, data){
	var str = '';
	console.log("length: "+data.length);
	$.each(data, function(idx, item){
		console.log("idx%3 : "+idx%3);
		if(idx%3 == 0) str += "<div class='photo-row displayCenter'>";
		str += "<div class='photo3' id='post"+item.post_no+"'>";
		if(item.mutl_tf == 'true') str += "<img alt='여러사진' class='ph_m' src='/resources/images/photo_multiple.png' >";
		str += "<img class='thumb' src='"+item.o_total_url+"'>"
				+ "</div>";
		if(idx%3 == 2 || idx == data.length) str += "</div>";
	});
	
	$(".profilebox2").empty();
	$(".profilebox2").append(str);
	
}

/* Only profile */
$(".menubtn").on("click", function(){
	var tg_no = $("#pf_member_no").val();
	var og_active = $(".menu_active");
	var new_active = $(this).attr("id");
	
	og_active.removeClass("menu_active");
	$("#"+new_active).parent().addClass("menu_active");
	
	var  url = '';
	
	if(new_active == "m1") url = "/main/memberPosts";
	else if (new_active == "m2") url = "/main/bookmarkPosts";
	else if (new_active == "m3"){
		
	}
	
	profileAjaxFunc("POST", url, {tg_no :tg_no} , "json", null, postsMenu);
});

/* Only profile */
$(".menubtn2").on("click", function(){
	var og_active = $(".menu_active");
	
	og_active.removeClass("menu_active");
	$(this).parent().addClass("menu_active");
})

$(".followCnt").on("click", function(){
	var id = $(this).attr("id");
	var url = "/"+id+"_info/";
	var member_no = $("#pf_member_no").val();
	
	subAjaxFunc("GET", url, member_no, null, "json", id+"_info", infoModalCallback);
})




