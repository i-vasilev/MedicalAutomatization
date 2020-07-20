<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style.css">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/logo.png" type="image/x-icon">
<title>Войдите в учётную запись</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="form_login">
		<form name="login" method="POST"
			action="${pageContext.request.contextPath}/main">
			<div class="input">
				<label>Номер полиса<br /> <input type="text"
					name="insurance" /></label>
			</div>
			<div class="input">
				<label>Пароль<br /> <input type="password" name="pass" /></label>
			</div>
			<button type="submit" name="cmd" class="button" value="Login" >Войти</button>
		</form>
	</div>
</body>
</html>