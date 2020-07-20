<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>Пациент</title>
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
	<div class="content_user">
		<form method="post"
			action="${pageContext.request.contextPath}/showPatient">
			<div class="username">
				<c:out value="${patient.name}" />

				<c:if test="${user.role eq 'ADMIN'}">
					<input type="number" value="${patient.id}" name="doctor_id"
						hidden="true" />
					<input type="number" value="${patient.insurance}" name="insurance"
						hidden="true" />
					<c:if test="${patient.role eq 'DOCTOR'}">
						<div>
							<label>Специализация врача<br /> <select
								name="specialization">
									<c:forEach var="specialization" items="${specializations}">
										<option value="${specialization.id}"
											${specialization.id == currentSpecialization.id ? "selected" : ""}>${specialization.name}</option>
									</c:forEach>
							</select></label>
						</div>
						<div>
							<label>Участок врача<br /> <select name="region">
									<c:forEach items="${regions}" var="region">
										<option value="${region.id}"
											${region.id == currentRegion.id? "selected":""}>Участок
											№${region.id}</option>
									</c:forEach>
									<c:out value=""></c:out>
							</select></label>
						</div>
					</c:if>
					<c:if test="${user.role eq 'ADMIN' || user.role eq 'RECEPTIONLIST'}">
						<div>
							<label>Деактивировать пользователя<input type="checkbox"
								name="deactivated" class="checkbox" value="true"
								${patient.isRemoved() ? "checked" : ""}></label>
						</div>
					</c:if>
					<div class="input">
						<button type="submit" name="cmd" class="button" value="saveUser">Сохранить</button>
					</div>
				</c:if>
			</div>
		</form>
		<c:if test="${patient.role eq 'USER'}">
			<a
				href="${pageContext.request.contextPath}/recordToDoctor?idUser=${patient.id}">Запись
				к врачу</a>
			<div class="records">
				<c:forEach var="record" items="${records}">
					<form action="showPatient" method="post">
						<div class="record">
							${record.doctor.specialization} ${record.doctor.name}
							<jsp:useBean id="timeRec" class="java.util.Date" />
							<fmt:formatDate value="${record.time}" pattern="d.M.y HH:mm" />
							<input type="text" name="id" value="${record.id}" hidden="true">
							<c:if test="${record.isAppear()}">
								<div class="complaints">
									<b>Жалобы:</b> ${record.complaints}
								</div>
								<div class="conclusion">
									<b>Диагноз:</b> ${record.conclusion}
								</div>
								<div class="prescribing">
									<b>Назначения:</b> ${record.prescribing}
								</div>
							</c:if>
							<c:if test="${not record.isAppear()}">
								<button type="submit" name="cmd" value="removeRecord"
									class="buttonLink">Удалить</button>
							</c:if>
						</div>
					</form>
				</c:forEach>
			</div>
		</c:if>
	</div>
</body>
</html>
