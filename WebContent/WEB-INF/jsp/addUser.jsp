<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Добавить пользователя</title>
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
	<div class="form_login">
		<c:if test="${messages ne ''}">
			<div class="errors">${messages}</div>
		</c:if>
		<form name="add_user" method="post">
			<div class="input">
				<label>ФИО<br /> <input type="text" name="name" /></label>
			</div>
			<div class="input">
				<label>Номер полиса<br /> <input type="text"
					name="insurance" /></label>
			</div>
			<div class="input">
				<label>Дата рождения<br /> <input type="date"
					name="dateBirth" /></label>
			</div>
			<div class="input">
				<label>Адрес<br /> <input type="text" name="address" /></label>
			</div>
			<c:if test="${user.role eq 'ADMIN'}">
				<div class="input">
					<label>Роль пользователя<br /> <select name="role">
							<c:forEach items="${roles}" var="role">
								<option value="${role}">${role}</option>
							</c:forEach>
					</select></label>
				</div>
			</c:if>
			<div class="input">
				<label>Номер участка - терапевт <br /> <select
					name="region">
						<c:forEach items="${regions}" var="region">
							<option value="${region.id}">${region.id}-
								${region.doctorName}</option>
						</c:forEach>
				</select></label>
			</div>
			<button type="submit" name="cmd" class="button" value="addUser">Добавить пользователя</button>
		</form>
	</div>
</body>
</html>