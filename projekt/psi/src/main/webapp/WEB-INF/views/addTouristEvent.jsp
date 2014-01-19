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
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>
	<form:form method="post" enctype="multipart/form-data">
		<div class="long-panel">
			<div class="panel-header">
				<h2>Informacje</h2>
			</div>
			<div class="panel-content">
				<form:label path="name">Nazwa:</form:label>
				<form:input path="name" />
				<form:label path="description">Opis:</form:label>
				<form:textarea path="description"/>
				<form:label path="operator">Operator:</form:label>
				<form:input path="operator"/>
				<form:label path="statue">Regulamin:</form:label>
				<input type="file" name="statue"/>
				<form:label path="peopleLimit">Liczba miejsc:</form:label>
				<form:input path="peopleLimit"/>
				<form:label path="photos">Zdjęcia:</form:label>
				<input type="file" name="photos[0]" multiple="multiple" />
			</div>
		</div>
		<div class="left-panel">
			<div class="panel-header">
				<h2>Miejsce</h2>
			</div>
			<div class="panel-content">
				<label for="countryId">Państwo:</label>
				<select id="countryId"></select>
				<label for="cityId">Miasto:</label>
				<select id="cityId"></select>
				<form:label path="hotelId">Hotel:</form:label>
				<form:select path="hotelId"/>
			</div>
		</div>
		<div class="right-panel">
			<div class="panel-header">
				<h2>Składniki</h2>
			</div>
			<div class="panel-content">
				<form:hidden path="touristEventComponentIds"/>
				<label for="touristEventComponents">Nowy składnik imprezy:</label>
				<select id="touristEventComponents"></select>
				<input type="button" value="Dodaj" />
				<label for="touristEventComponentList">Lista:</label>
				<table>
					<tr>
						<th>Typ</th>
						<th>Nazwa</th>
						<th>Akcje</th>
					</tr>
				</table>
			</div>
		</div>
		<div class="long-panel">
			<div class="panel-content">
				<input type="submit" value="Wyślij" />
				<input type="button" value="Anuluj" onclick="window.location='${basepath}/lista-imprez-turystycznych'" />
			</div>
		</div>
	</form:form>
</body>
</html>