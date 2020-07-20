<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Найти пользователя</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/logo.png" type="image/x-icon">
</head>
<body>
	<%@include file="logout.jsp" %>
	<%@include file="header.jsp"%>
	<%@include file="menu.jsp"%>
	<div class="form_login">
		<form name="find_user" method="POST" action="${pageContext.request.contextPath}/showPatient">
			<div class="input">
				<label>Номер полиса<br />
				<input type="text" name="insurance" /></label>
			</div>
			<button type="submit" class="button" name="cmd" value="findUser" >Найти пользователя</button>
		</form>
	</div>
</body>
</html>