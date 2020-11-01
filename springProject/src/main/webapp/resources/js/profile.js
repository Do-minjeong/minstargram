
var onFollow = function(){
	var on_f = $("#on_follow");
	on_f.removeClass("btnBlue");
	on_f.addClass("btnGray");
	on_f.text("팔로우취소");
	on_f.attr("id","off_follow");
};
var offFollow = function(){
	var off_f = $("#off_follow");
	off_f.removeClass("btnGray");
	off_f.addClass("btnBlue");
	off_f.text("팔로우하기");
	off_f.attr("id","on_follow");
};

function profileAjaxFunc(type, url, params, dataType, callback){
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
				callback(data);
		},
		error : function(status, error){
			console.log("status: "+status+"  error: "+error);
		}
	});
};

var modalOpen = function(data){
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
		$.each(data.replyList, function(idx, item){
			str += 		"	<div class='md-writer'>"
				+				"	<div class='modal-userInfo'>"
				+				"		<div class='md-canvas canvas displayCenter'>"
				+						"	<div class='md-profile-pic profile-pic'>"
				+								"<img"
				+								"	src='https://travelblog.expedia.co.kr/wp-content/uploads/2016/06/03.jpg'>"
				+					"		</div>"
				+					"	</div>"
				+				"	</div>"
				+				"	<div class='writer-contents-box'>"
				+				"		<div class='md-box1'>"
				+						"	<div >"
				+							"	<span class='md-article-id article-id'><a"
				+								"	href='/main/profile?member_no=${post.member_no}'>"+item.userid+"</a></span>"
				+							"<span class='font14'>"+item.r_contents+"</span>"
				+					"		</div>"
				+					"		<button class='btnNone md-replyLikeBtn' id='rpLikebtn"+item.reply_no+"'>";
				if(item.r_like_btn == 'true'){
					str += "	<img alt='reply_like' class='on_rpLike' src='https://www.flaticon.com/svg/static/icons/svg/865/865974.svg'>";
				}
				else{
					str += "<img alt='reply_like' class='off_rpLike' src='https://www.flaticon.com/svg/static/icons/svg/865/865991.svg'>";
				}
				str	+=						"	</button>"
				+						"</div>"
				+					"	<div class='md-regdate'><span>1분전</span></div>"
				+					"</div>"
				+				"</div>"
		})
	}
	str	+=		"</div></div>	<div class='modal-ft'>"
		+				"<div class='article-iconbox article-section'>"
		+				"	<button class='article-icon btnNone likebtn' id='likebtn${post.post_no}'>";
	if(data.like_btn == 'true' )
		str += "	<img alt='좋아요' class='on_like' src='https://www.flaticon.com/svg/static/icons/svg/1946/1946346.svg'>	";
	else 
		str += "<img alt='좋아요' class='off_like' src='https://www.flaticon.com/svg/static/icons/svg/1946/1946406.svg'>";

	str +=				"	</button>"
		+					"<button class='article-icon btnNone commentbtn' id='commentbtn${post.post_no}'>"
		+					"	<img alt='댓글' src='https://www.flaticon.com/svg/static/icons/svg/1946/1946412.svg'>"
		+				"	</button>"
		+				"	<button class='article-icon btnNone messagebtn' id='messagebtn${post.post_no}'><img alt='메시지' src='https://www.flaticon.com/svg/static/icons/svg/1946/1946547.svg'></button>"
		+					"<button class='article-icon bookmark btnNone bookmarkbtn' id='bookmarkbtn${post.post_no}'>";
	if(data.bookmark_btn == 'true')
		str += "	<img alt='북마크' class='on_bookmark' src='https://www.flaticon.com/svg/static/icons/svg/1174/1174447.svg'>	";
	else 
		str += "<img alt='북마크' class='off_bookmark' src='https://www.flaticon.com/svg/static/icons/svg/1174/1174410.svg'>";

	str +=		"</button>"
		+				"</div>"
		+				"<div class='article-likes article-section'>"
		+				"	<button class='likes_info btnNone font14'>"
		+				"		좋아요 <span id='likeval"+data.post_no+"'>"+data.like_cnt+"</span>개"
		+				"	</button>"
		+			"	</div>"
		+				"<div class='md-regdate'><span>"+data.reg_date+"</span></div>"
		+			"</div>"
		+			"<div class='modal-rp'>"
		+			"	<form action='' class='md-comment-form comment-form displayCenter'>"
		+			"		<textarea id='cmt"+data.post_no+"' name='r_contents' wrap='virtual' cols='38' class='btnNone' placeholder='댓글 달기..'></textarea>"
		+			"		<button type='submit' class='btnNone' disabled>게시</button>"
		+			"	</form>"
		+		"	</div>"
		+		"</div>"
		+	"</div>"
		+	"	</div>";
	$(".md-modal-body").empty();
	$(".md-modal-body").append(str);
	$("#myModal").modal();
}

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