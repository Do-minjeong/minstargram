<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link href="/resources/css/header.css" rel="stylesheet">
<%@ include file="../../includes/header.jsp"%>
</head>
<body>
<div class="header-container">
	<div class="header">
		<div>
			<button class="btnNone" onclick="location.href='/main/mainHome'">
				<h1 class="titleText">MIN <span class="star">★</span> GRAM</h1>
			</button>
		</div>
<%-- 		<div>
			<c:if test="${userInfo.login_type_no eq 1 }">
				LOGIN:			
			</c:if>
			<c:if test="${userInfo.login_type_no eq 2 }">
				SOCIAL LOGIN:
			</c:if>
			${ userInfo.username }
		</div> --%>
		<div class="searchbox displayCenter">
			<img alt="searchImg" src="https://www.flaticon.com/svg/static/icons/svg/25/25313.svg" >
			<span>검색</span>
		</div>
		<div class="iconbox displayCenter">
			<div class="icon">
				<button class="btnNone navIcon" >
					<img alt="home" src="https://www.flaticon.com/svg/static/icons/svg/1946/1946488.svg">
				</button>
			</div>
			<div class="icon">
				<button class="btnNone navIcon" >
					<img alt="directmsg" src="https://www.flaticon.com/svg/static/icons/svg/1946/1946547.svg">
				</button>
			</div>
			<div class="icon">
				<button class="btnNone navIcon" >
					<img alt="compass" src="https://www.flaticon.com/svg/static/icons/svg/2948/2948031.svg">
				</button>
			</div>
			<div class="icon">
				<button class="btnNone navIcon" >
					<img alt="heart" src="https://www.flaticon.com/svg/static/icons/svg/1946/1946406.svg">
				</button>
			</div>
			<div class="icon">
				<button class="navIcon btnNone dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<img alt="profile" src="https://www.flaticon.com/svg/static/icons/svg/848/848006.svg">
				</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
				<div class="dd-menu">
					<div class="rhombus"></div>
						<button class="dropdown-item" onclick="location.href='/main/profile?member_no=${userInfo.member_no}'">
							<div class="profile-menu">
								<div class="menu-img">
									<img alt="프로필로 이동" src="https://www.flaticon.com/svg/static/icons/svg/848/848043.svg">
								</div>
								<span>프로필</span>
							</div>
						</button>
						<button class="dropdown-item">
							<div class="profile-menu">
								<div class="menu-img">
									<img alt="북마크로 이동" src="https://www.flaticon.com/svg/static/icons/svg/1174/1174410.svg">
								</div>
								<span>저장됨</span>
							</div>
						</button>
						<button class="dropdown-item">
							<div class="profile-menu">
								<div class="menu-img">
									<img alt="설정으로 이동" src="https://www.flaticon.com/svg/static/icons/svg/3524/3524636.svg">
								</div>
								<span>설정</span>
							</div>
						</button>
						<button class="dropdown-item logoutbtn" onclick="location.href='/customLogout'">
							<div class="profile-menu">
								<span>로그아웃</span>
							</div>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>

