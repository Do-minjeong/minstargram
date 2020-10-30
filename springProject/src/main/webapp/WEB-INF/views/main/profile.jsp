<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="includes/header.jsp"%>
<link href="/resources/css/main.css" rel="stylesheet">
<title>Profile-${ userInfo.username }</title>
</head>
<body>
<div class="mainbox">
	<div class="profilebox1">
		<div class="profile-left displayCenter">
			<div class="canvas profile-canvas displayCenter">
				<div class="profile-pic profile-wh">
					<img alt="프로필사진" src="${ profile.profile_photo }">
				</div>
			</div>
		</div>
		<div class="profile-right">
			<div class="pr-1">
			<input type="hidden" id="pf_member_no" value="${ profile.member_no }">
			<input type="hidden" name="${ _csrf.parameterName }" value="${_csrf.token}" id="csrf_token" >
				<span><c:out value="${ profile.userid }" /></span>
				<c:if test="${ profile.userid eq userInfo.userid }">
					<button type="button" class="btn btnEdit" >프로필편집</button>
				</c:if>
				<c:if test="${ profile.userid ne userInfo.userid }">
					<c:if test="${ profile.relationVO.relationship eq 0 || profile.relationVO.relationship eq 2 }">
						<button type="button" class="btn btnBlue" id="on_follow" >팔로우하기</button>				
					</c:if>
					<c:if test="${ profile.relationVO.relationship eq 1 || profile.relationVO.relationship eq 3 }">
						<button type="button" class="btn btnGray" id="off_follow">팔로우취소</button>				
					</c:if>
				</c:if>
				
				<button type="button" class="btn btnNone profile-more"><img alt="더보기" src="https://www.flaticon.com/svg/static/icons/svg/1828/1828805.svg"></button>
			</div>
			<div class="pr-2">
				<div class="cntbox"><span>게시물</span><span class="cnt">${ profile.post_cnt }</span></div>
				<div class="cntbox"><span>팔로워</span><span class="cnt">${ profile.relationVO.follower_cnt }</span></div>
				<div class="cntbox"><span>팔로잉</span><span class="cnt">${ profile.relationVO.follow_cnt }</span></div>
			</div>
			<div class="pr-3">
				<span><c:out value="${ profile.name }" /></span>
			</div>
			<div class="pr-4">
				<span><c:out value="${ profile.introduce }" /></span>
			</div>
			<c:if test="${ profile.relationVO.relationship eq 2 }">
				<div class="pr-5">
					<span>나를 팔로우하는 사용자입니다.</span>
				</div>
			</c:if>
		</div>
	</div>	
	<div class="menubar displayCenter">
		<div class="menu">
			<a href="">
				<img src="https://www.flaticon.com/svg/static/icons/svg/459/459879.svg">
				<span>게시물</span>
			</a>
		</div>
		<div class="menu">
			<a href="">
				<img src="https://www.flaticon.com/svg/static/icons/svg/1174/1174410.svg">
				<span>저장됨</span>
			</a>
		</div>
		<div class="menu">
			<a href="">
				<img src="https://www.flaticon.com/svg/static/icons/svg/1946/1946481.svg">
				<span>태그됨</span>
			</a>
		</div>
	</div>
	<div class="profilebox2">
		<c:forEach items="${ profile.post_List }" var="postList" varStatus="status" >
			<c:if test="${ status.count mod 3 eq 1 }">
				<div class="photo-row displayCenter">				
			</c:if>
				<div class="photo3">
					<c:if test="${ postList.multi_tf }">
						<img alt="여러사진" class="ph_m" src="/resources/images/photo_multiple.png">					
					</c:if>
					<img class="thumb" src="${postList.o_total_url}">
				</div>
			<c:if test="${ status.count mod 3 eq 0 || status.last }">
				</div>
			</c:if>
		</c:forEach>
	</div>
</div>
<script type="text/javascript" src="/resources/js/profile.js"></script>
<script type="text/javascript">

$("#on_follow , #off_follow").on("click", function(){
	var tg_no = $("#pf_member_no").val();
	var type = '';
	var url = '';
	var callback = '';
	if($(this).attr("id").indexOf("on")==0){
		type = "POST";
		url = "/onFollow/"+tg_no;
		callback = onFollow;
	} else if($(this).attr("id").indexOf("off")==0){
		type = "DELETE";
		url = "/offFollow/"+tg_no;
		callback= offFollow;
	}
		followAjaxFunc(type, url, null, callback);		
	
});

</script>
</body>
</html>