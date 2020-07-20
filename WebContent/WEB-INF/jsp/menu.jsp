<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="menu">
	<ul>
		<c:if
			test="${user.role eq 'ADMIN' || user.role eq 'RECEPTIONLIST' || user.role eq 'DOCTOR'}">
			<li><a href="findUser" class="menu_item">Найти пользователя</a></li>
		</c:if>
		<c:if test="${user.role eq 'ADMIN' || user.role eq 'RECEPTIONLIST'}">
			<li><a href="addUser" class="menu_item">Добавить
					пользователя</a></li>
		</c:if>
		<c:if test="${user.role eq 'RECEPTIONLIST'}">
			<li><a href="addRecords" class="menu_item">Добавить
					записи на приём врачу</a></li>
		</c:if>
		<c:if test="${user.role eq 'DOCTOR'}">
			<li><a href="doctorReception" class="menu_item">Прием врача</a></li>
		</c:if>
		<c:if test="${user.role eq 'USER'}">
			<li><a href="recordToDoctor" class="menu_item">Запись на приём</a></li>
		</c:if>
	</ul>
</div>