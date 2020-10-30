
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

function followAjaxFunc(type, url, params, callback){
	$.ajax({
		type : type,
		url : url,
		data: JSON.stringify(params),
		dataType: "text",
		contentType : "application/json; charset=utf-8",
		headers: {
			'X-CSRF-TOKEN': $("#csrf_token").val()
		},
		success : function(data){
			console.log("success : " + data);
			if(callback)
				callback();
		},
		error : function(status, error){
			console.log("status: "+status+"  error: "+error);
			console.log(title + " Error");
		}
	});
};