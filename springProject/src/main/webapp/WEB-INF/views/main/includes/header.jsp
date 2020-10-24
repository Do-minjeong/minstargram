<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link href="/resources/css/header.css" rel="stylesheet">
</head>
<body>
<div class="header-container">
	<div class="header">
		<div>
			<button class="btnNone" onclick="location.href='/main/mainHome'">
				<h1 class="titleText">MIN <span class="star">★</span> GRAM</h1>
			</button>
		</div>
		<div>
			<c:if test="${userInfo.login_type_no eq 1 }">
				LOGIN:			
			</c:if>
			<c:if test="${userInfo.login_type_no eq 2 }">
				SOCIAL LOGIN:
			</c:if>
			${ userInfo.username }
		</div>
		<div class="searchbox displayCenter">
			<img alt="searchImg" src="https://www.flaticon.com/svg/static/icons/svg/25/25313.svg" >
			<span>검색</span>
		</div>
		<div class="iconbox displayCenter">
			<div class="icon">
				<a href=""><img alt="home" src="https://www.flaticon.com/svg/static/icons/svg/1946/1946488.svg"></a>
			</div>
			<div class="icon">
				<a href=""><img alt="directmsg" src="https://www.flaticon.com/svg/static/icons/svg/1946/1946547.svg"></a>
			</div>
			<div class="icon">
				<a href=""><img alt="directmsg" src="https://www.flaticon.com/svg/static/icons/svg/1946/1946481.svg"></a>
			</div>
			<div class="icon">
				<a href=""><img alt="directmsg" src="https://www.flaticon.com/svg/static/icons/svg/1946/1946406.svg"></a>
			</div>
			<div class="icon">
				<a href=""><img alt="directmsg" src="https://www.flaticon.com/svg/static/icons/svg/848/848006.svg"></a>
			</div>
		</div>
	</div>
</div>
</body>
</html>

