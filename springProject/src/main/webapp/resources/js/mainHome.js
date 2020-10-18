$(".comment-form textarea").keyup(function(key){
	var val = this.value;
	var btn = $(".comment-form button");
	if(val.length==0){
		btn.attr("disabled", true);
	}else{
		btn.attr("disabled", false);
	}
});

$(".carousel-control-prev , .carousel-control-next").on("click", function(){
	setTimeout(function(){
		var img = $(this).parent().children().children(".active").children().children();
		var img_h = img.height();
		console.log(img);
		console.log(img_h);
		if(img_h>500) {
			img.removeClass("w-100");
			img.addClass("h-500");
		}
	}, 650);
});	

