$(".comment-form textarea").keyup(function(key){
	var val = this.value;
	var btn = $(".comment-form button");
	if(val.length==0){
		btn.attr("disabled", true);
	}else{
		btn.attr("disabled", false);
	}
});
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
