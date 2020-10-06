<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Access Denied</title>
<%@ include file="includes/header.jsp"%>
<style type="text/css">
	body{
		padding: 100px 30px;	
	}
	.displayFlex{
		display: flex;
		justify-content: center;
	}
	.mainbox{
		width: 1000px;
		margin: 0 auto;
	    border-radius: 50px;
	    background-color: rgba(253,189,64,0.2);
	    padding: 40px;
	}
	.accessImg{
		width: 150px;
		margin-right: 50px;
	}
	.titlebox{
		padding: 20px;
	}
	.title{
		font-size: 40px;
		margin: 15px 0 ;
		font-weight: 800;
	}
	.subtitle{
		font-size: 25px;
		font-weight: 600;
		color: rgba(0,0,0,0.5);
	}
	.main-btn{
		background-color: rgb(253,189,64);
	    border-radius: 20px;
	    font-size: 20px;
	    font-weight: 700;
	    width: 170px;
	    height: 45px;
	    margin-top: 30px;
	}
</style>
</head>
<body>
<div class="mainbox">
	<div class="displayFlex">
		<img alt="accessError" class="accessImg" src="https://www.flaticon.com/svg/static/icons/svg/790/790680.svg">
		<div class="titlebox">
			<h2 class="title" >접근 권한이 없습니다</h2>
			<div class="displayFlex subtitle">[ <c:out value="${ msg }"></c:out> ]</div>
		</div>
	</div>
	<div class="displayFlex">
		<button class="btn main-btn" onclick="location.href='/'">메인으로 가기</button>
	</div>
</div>
</body>
</html>