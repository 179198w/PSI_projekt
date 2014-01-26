<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="basepath" scope="request" value="<%= request.getContextPath() %>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Lista imprez turystycznych</title>
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${basepath}/styles/style.css">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="main-panel">
	<form:form method="post" enctype="multipart/form-data">
		<div class="long-panel">
			<div class="panel-header">
				<h3>Informacje</h3>
			</div>
			<div class="panel-content">
				<form:label path="name">Nazwa:</form:label>
				<form:input path="name" />
				<br />
				<br />
				<form:label path="description">Opis:</form:label>
				<form:textarea path="description"/>
				<br />
				<br />
				<form:label path="type">Typ:</form:label>
				<form:select path="type">
					<form:option value="">Wybierz typ</form:option>
					<form:options items="${types}" />
				</form:select>
			</div>
		</div>
		<div class="long-panel text-align-center">
			<div class="display-inline-block">			
				<input type="submit" value="Wyślij" />
				<input type="button" value="Anuluj" onclick="window.location='${basepath}/lista-skladnikow'" />
			</div>
		</div>
	</form:form>
</div>
</body>
</html>