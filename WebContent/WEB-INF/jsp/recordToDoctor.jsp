<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Запись к врачу</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style.css">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/logo.png"
	type="image/x-icon">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
</head>
<body>

	<%@include file="logout.jsp"%>
	<%@include file="header.jsp"%>
	<%@include file="menu.jsp"%>
	<div class="form_login">
		<form name="find_user" action="recordToDoctor" method="post">
			<input type="text" name="idUser"
				value="${idUser eq null ? sessionScope.user.id : idUser}" hidden />
			<div class="input">
				<label>Выберите врача<br /> <select name="doctor"
					${selectedDoctor eq null?"":"readonly"}>
						<c:if test="${selectedDoctor eq null}">
							<c:forEach items="${doctors}" var="doctor">
								<option value="${doctor.insurance}">${doctor.specialization}-
									${doctor.name}</option>
							</c:forEach>
						</c:if>
						<c:if test="${selectedDoctor ne null}">
							<option value="${selectedDoctor.insurance}" selected>${selectedDoctor.name}</option>
						</c:if>
				</select></label>
			</div>
			<c:if test="${selectedDoctor ne null}">
				<div class="input">
					<label>Выберите время<br /> <select name="time">
							<c:forEach items="${records}" var="record">
								<option value="${record.id}">
									<jsp:useBean id="timeRec" class="java.util.Date" />
									<fmt:formatDate value="${record.time}" pattern="F.M.y HH:mm" /></option>
							</c:forEach>
					</select></label>
				</div>
			</c:if>
			<button type="submit" class="button" name="cmd" value="record">Записаться</button>
		</form>
	</div>
</body>
</html>