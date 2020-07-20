<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Добавить приём врачу</title>
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
		<form name="addRecords" method="post">

			<div class="input">
				<label>Выберите врача<br /> <select name="doctor"
					${selectedDoctor eq null?"":"readonly"}>
						<c:forEach items="${doctors}" var="doctor">
							<option value="${doctor.id}">${doctor.specialization}-
								${doctor.name}</option>
						</c:forEach>
				</select>
				</label>
			</div>
			<div class="input">
				<label>Дата приёма:<br /> <input type="date" name="date">
				</label>
			</div>
			<div class="input">
				<label>Время начала приёма:<br /> <input type="time"
					name="timeStart">
				</label>
			</div>
			<div class="input">
				<label>Время окончания приёма:<br /> <input type="time"
					name="timeEnd">
				</label>
			</div>
			<div class="input">
				<label>Интервал приёма (минут):<br /> <input type="number"
					name="interval" max="30" min="5">
				</label>
			</div>

			<button type="submit" name="cmd" class="button" value="addRecords">Добавить
				записи на приём</button>
		</form>
	</div>
</body>
</html>