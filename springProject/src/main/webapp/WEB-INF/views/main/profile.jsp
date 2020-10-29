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
					<img alt="프로필사진" src="https://www.urbanbrush.net/web/wp-content/uploads/edd/2018/12/urbanbrush-20181213142535248709.png">
				</div>
			</div>
		</div>
		<div class="profile-right">
			<div class="pr-1">
				<span><c:out value="${ profile.userid }" /></span>
				<c:if test="${ profile.relationVO.relationship eq 0 || profile.relationVO.relationship eq 2 }">
					<button type="button" class="btn btnBlue on_follow" >팔로우하기</button>				
				</c:if>
				<c:if test="${ profile.relationVO.relationship eq 1 || profile.relationVO.relationship eq 3 }">
					<button type="button" class="btn btnGray off_follow">팔로우취소</button>				
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
		<div class="photo-row displayCenter">
			<div class="photo3">
				<img alt="" src="https://lh3.googleusercontent.com/proxy/8cYo-lNfik1GuDnKZ6sVRhWFyYCbC-Y5kBgnvmfDdKQCtOVgfyzagBK9mwY-FqQTDdbPD_fyRLHxnPaYu2LANQ2_adGWqXUYQV0_c_oQebf3WBzPExOUk3y4zInnl2_qNhDNy2tUFxd4p2ayU1VrXbFjbQALE13J9YBQIDbRao0wM2s7v_uNKGOOj0sctdfSMNl2vtCibHls2c3QWfz-VumtsJHl4OMBLXU_diPOUGOUkeS8i-C3Lk7cULokuKbtjZd970NDazfYkvr51Dw5Y5IjI5hVhAKn1NHNJYsrbg-b9nlqUeYKtjXIttrAoUslb_wZB_aEcEaT2rvwkaYedridlEI5h9lutjU4Ia2N2AUljUIwFxk1MailISZE">
			</div>
			<div class="photo3">
				<img alt="" src="https://lh3.googleusercontent.com/proxy/8cYo-lNfik1GuDnKZ6sVRhWFyYCbC-Y5kBgnvmfDdKQCtOVgfyzagBK9mwY-FqQTDdbPD_fyRLHxnPaYu2LANQ2_adGWqXUYQV0_c_oQebf3WBzPExOUk3y4zInnl2_qNhDNy2tUFxd4p2ayU1VrXbFjbQALE13J9YBQIDbRao0wM2s7v_uNKGOOj0sctdfSMNl2vtCibHls2c3QWfz-VumtsJHl4OMBLXU_diPOUGOUkeS8i-C3Lk7cULokuKbtjZd970NDazfYkvr51Dw5Y5IjI5hVhAKn1NHNJYsrbg-b9nlqUeYKtjXIttrAoUslb_wZB_aEcEaT2rvwkaYedridlEI5h9lutjU4Ia2N2AUljUIwFxk1MailISZE">
			</div>
			<div class="photo3">
				<img alt="" src="https://lh3.googleusercontent.com/proxy/8cYo-lNfik1GuDnKZ6sVRhWFyYCbC-Y5kBgnvmfDdKQCtOVgfyzagBK9mwY-FqQTDdbPD_fyRLHxnPaYu2LANQ2_adGWqXUYQV0_c_oQebf3WBzPExOUk3y4zInnl2_qNhDNy2tUFxd4p2ayU1VrXbFjbQALE13J9YBQIDbRao0wM2s7v_uNKGOOj0sctdfSMNl2vtCibHls2c3QWfz-VumtsJHl4OMBLXU_diPOUGOUkeS8i-C3Lk7cULokuKbtjZd970NDazfYkvr51Dw5Y5IjI5hVhAKn1NHNJYsrbg-b9nlqUeYKtjXIttrAoUslb_wZB_aEcEaT2rvwkaYedridlEI5h9lutjU4Ia2N2AUljUIwFxk1MailISZE">
			</div>
		</div>
		<div class="photo-row displayCenter">
			<div class="photo3">
				<img alt="" src="https://lh3.googleusercontent.com/proxy/8cYo-lNfik1GuDnKZ6sVRhWFyYCbC-Y5kBgnvmfDdKQCtOVgfyzagBK9mwY-FqQTDdbPD_fyRLHxnPaYu2LANQ2_adGWqXUYQV0_c_oQebf3WBzPExOUk3y4zInnl2_qNhDNy2tUFxd4p2ayU1VrXbFjbQALE13J9YBQIDbRao0wM2s7v_uNKGOOj0sctdfSMNl2vtCibHls2c3QWfz-VumtsJHl4OMBLXU_diPOUGOUkeS8i-C3Lk7cULokuKbtjZd970NDazfYkvr51Dw5Y5IjI5hVhAKn1NHNJYsrbg-b9nlqUeYKtjXIttrAoUslb_wZB_aEcEaT2rvwkaYedridlEI5h9lutjU4Ia2N2AUljUIwFxk1MailISZE">
			</div>
			<div class="photo3">
				<img alt="" src="https://lh3.googleusercontent.com/proxy/8cYo-lNfik1GuDnKZ6sVRhWFyYCbC-Y5kBgnvmfDdKQCtOVgfyzagBK9mwY-FqQTDdbPD_fyRLHxnPaYu2LANQ2_adGWqXUYQV0_c_oQebf3WBzPExOUk3y4zInnl2_qNhDNy2tUFxd4p2ayU1VrXbFjbQALE13J9YBQIDbRao0wM2s7v_uNKGOOj0sctdfSMNl2vtCibHls2c3QWfz-VumtsJHl4OMBLXU_diPOUGOUkeS8i-C3Lk7cULokuKbtjZd970NDazfYkvr51Dw5Y5IjI5hVhAKn1NHNJYsrbg-b9nlqUeYKtjXIttrAoUslb_wZB_aEcEaT2rvwkaYedridlEI5h9lutjU4Ia2N2AUljUIwFxk1MailISZE">
			</div>
			<div class="photo3">
				<img alt="" src="https://lh3.googleusercontent.com/proxy/8cYo-lNfik1GuDnKZ6sVRhWFyYCbC-Y5kBgnvmfDdKQCtOVgfyzagBK9mwY-FqQTDdbPD_fyRLHxnPaYu2LANQ2_adGWqXUYQV0_c_oQebf3WBzPExOUk3y4zInnl2_qNhDNy2tUFxd4p2ayU1VrXbFjbQALE13J9YBQIDbRao0wM2s7v_uNKGOOj0sctdfSMNl2vtCibHls2c3QWfz-VumtsJHl4OMBLXU_diPOUGOUkeS8i-C3Lk7cULokuKbtjZd970NDazfYkvr51Dw5Y5IjI5hVhAKn1NHNJYsrbg-b9nlqUeYKtjXIttrAoUslb_wZB_aEcEaT2rvwkaYedridlEI5h9lutjU4Ia2N2AUljUIwFxk1MailISZE">
			</div>
		</div>
	</div>

</div>
</body>
</html>