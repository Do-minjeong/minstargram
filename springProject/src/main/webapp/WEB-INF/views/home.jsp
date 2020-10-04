<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
<%@ include file="includes/header.jsp"%>
<link rel="stylesheet" href="/resources/home.css?As">
</head>
<body style="background: rgba(var(--b3f,250,250,250),1);">
<div class="container">
	<div class="box displayCenter">
		<div class="displayCenter titlediv">
			<h1 class="titleText">MIN <span class="star text-yellow">★</span> GRAM</h1>
			<h2>HOME</h2>
		</div>
		<div>
			<form action="/customLogout" method="post">
				<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
				<fieldset>
					<input type="submit" class="btn btn-lg btn-success" value="Logout"/>
				</fieldset>
			</form>
		</div>
	</div>
</div>

</body>
</html>
