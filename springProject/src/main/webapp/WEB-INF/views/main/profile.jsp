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
					<input type="hidden"
						name="${ _csrf.parameterName }" value="${_csrf.token}"
						id="csrf_token"> <span><c:out
							value="${ profile.userid }" /></span>
					<c:if test="${ profile.userid eq userInfo.userid }">
						<button type="button" class="btn btnEdit">프로필편집</button>
					</c:if>
					<c:if test="${ profile.userid ne userInfo.userid }">
						<c:if test="${ profile.relationVO.relationship < 2 }">
							<button type="button" class="btn btnBlue followbtn" id="on_follow${ profile.member_no }">팔로우하기</button>
						</c:if>
						<c:if test="${ profile.relationVO.relationship >= 2 }">
							<button type="button" class="btn btnGray followbtn" id="off_follow${ profile.member_no }">팔로우취소</button>
						</c:if>
					</c:if>
					<input type="hidden" id="pf_member_no" class="fw_member_no"	value="${ profile.member_no }"> 
					<button type="button" class="btn btnNone profile-more">
						<img alt="더보기"
							src="https://www.flaticon.com/svg/static/icons/svg/1828/1828805.svg">
					</button>
				</div>
				<div class="pr-2">
					<div class="cntbox">
						<span>게시물</span><span class="cnt">${ profile.post_cnt }</span>
					</div>
					<button class="btnNone cntbox followCnt" id="followers" >
						<span>팔로워</span><span class="cnt">${ profile.relationVO.follower_cnt }</span>
					</button>
					<button class="btnNone cntbox followCnt" id="followings" >
						<span>팔로잉</span><span class="cnt">${ profile.relationVO.following_cnt }</span>
					</button>
				</div>
				<div class="pr-3">
					<span><c:out value="${ profile.name }" /></span>
				</div>
				<div class="pr-4">
					<span><c:out value="${ profile.introduce }" /></span>
				</div>
				<c:if test="${ profile.relationVO.relationship eq 1 }">
					<div class="pr-5">
						<span>나를 팔로우하는 사용자입니다.</span>
					</div>
				</c:if>
			</div>
		</div>
		<div class="menubar displayCenter">
			<div class="menu menu_active">
				<button class="btnNone menubtn" id="m1"><img
					src="https://www.flaticon.com/svg/static/icons/svg/459/459879.svg">
					<span>게시물</span>
				</button>
			</div>
			<div class="menu">
				<button class="btnNone menubtn" id="m2"><img
					src="https://www.flaticon.com/svg/static/icons/svg/1174/1174410.svg">
					<span>저장됨</span>
				</button>
			</div>
			<div class="menu">
				<button class="btnNone menubtn" id="m3"> <img
					src="https://www.flaticon.com/svg/static/icons/svg/1946/1946481.svg">
					<span>태그됨</span>
				</button>
			</div>
		</div>
		<div class="profilebox2">
			<c:if test="${ profile.post_cnt > 0 }">
				<c:forEach items="${ profile.post_List }" var="postList"
					varStatus="status">
					<c:if test="${ status.count mod 3 eq 1 }">
						<div class="photo-row displayCenter">
					</c:if>
					<div class="photo3" id="post${ postList.post_no }">
						<c:if test="${ postList.multi_tf }">
							<img alt="여러사진" class="ph_m"
								src="/resources/images/photo_multiple.png">
						</c:if>
						<img class="thumb" src="${postList.o_total_url}">
					</div>
				<c:if test="${ status.count mod 3 eq 0 || status.last }">
					</div>
				</c:if>
			</c:forEach>
		</c:if>
	</div>
</div>
<%@ include file="includes/modal.jsp"%>

<script type="text/javascript" src="/resources/js/mainHome.js"></script>
</body>
</html>