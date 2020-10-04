<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>권한이 없습니다</h2>
<img alt="accessError" src="https://www.flaticon.com/svg/static/icons/svg/190/190218.svg" width="100px">
<h3><c:out value="${ msg }"></c:out></h3>
</body>
</html>