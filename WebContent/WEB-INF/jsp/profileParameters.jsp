<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Параметры пользователя</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style.css">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/logo.png"
	type="image/x-icon">
</head>
<body>

	<%@include file="logout.jsp"%>
	<%@include file="header.jsp"%>
	<%@include file="menu.jsp"%>
	<div>Пользователь: ${newUser.name}</div>
	<div>Данные для входа в личный кабинет:</div>
	<div>Логин: ${newUser.insurance}</div>
	<div>Пароль: ${password}</div>
</body>
</html>