<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Прием врача</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/logo.png" type="image/x-icon">
</head>
<body>

	<%@include file="logout.jsp" %>
	<%@include file="header.jsp"%>
	<%@include file="menu.jsp"%>
	<form name="login" method="post">
		<input type="textbox" name="id" value="${id}" hidden>
		<div class="input">
			<label>Жалобы<br /> <textarea name="complaints" cols="100" rows="10"></textarea></label>
		</div>
		<div class="input">
			<label>Диагноз<br /> <textarea name="conclusion" cols="100" rows="10" ></textarea></label>
		</div>
		<div class="input">
			<label>Назначения<br /> <textarea name="prescribing" cols="100" rows="10" ></textarea></label>
		</div>
		<input type="submit" name="cmd" class="button" value="reception">
	</form>
</body>
</html>