<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="logout">
	Добро пожаловать, ${sessionScope.user.name}! <a href="logout">Выйти</a>
</div>