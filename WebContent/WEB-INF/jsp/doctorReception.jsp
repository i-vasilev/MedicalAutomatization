<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>Прием врача</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style.css">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/logo.png" type="image/x-icon">
</head>
<body>

	<%@include file="logout.jsp"%>
	<%@include file="header.jsp"%>
	<%@include file="menu.jsp"%>
	<div class="receptionPatients">
		<c:if test="${records.size() ne 0}">
			Записи пациентов на сегодня:
			<ul>
				<c:forEach items="${records}" var="record">
					<jsp:useBean id="timeRec" class="java.util.Date" />
					<fmt:setLocale value="en_US" />
					<li><a href="patientReception?id=${record.id}">${record.patient.name},
							<fmt:formatDate value="${record.time}" pattern="HH:mm" />
					</a></li>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${records.size() eq 0}">
			Активных записей на сегодня нет.
		</c:if>
	</div>
</body>
</html>