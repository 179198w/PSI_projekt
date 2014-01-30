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
		<div class="long-panel panel panel-primary">
			<div class="panel-heading">
				<h3>Informacje</h3>
			</div>
			<div class="panel-body">
				<form:label path="name">Nazwa:</form:label>
				<form:input path="name" />
				<br />
				<br />
				<form:label path="description">Opis:</form:label>
				<form:textarea path="description"/>
				<br />
				<br />
				<form:label path="operator">Operator:</form:label>
				<form:input path="operator"/>
				<br />
				<br />
				<form:label path="statue">Regulamin:</form:label>
				<input type="file" name="statue" class="display-inline-block" />
				<br />
				<br />
				<form:label path="peopleLimit">Liczba miejsc:</form:label>
				<form:input path="peopleLimit"/>
				<br />
				<br />
				<form:label path="photos">Zdjęcia:</form:label>
				<input type="file" name="photos[0]" class="display-inline-block" />
			</div>
		</div>
		<div class="left-panel panel panel-primary">
			<div class="panel-heading">
				<h3>Miejsce</h3>
			</div>
			<div class="panel-body">
				<form:label path="countryId">Państwo:</form:label >
				<form:select path="countryId">
					<form:option value="0">Wybierz państwo</form:option>
					<form:options items="${countries}" itemLabel="name" itemValue="id" />
				</form:select>
				<br />
				<br />
				<form:label path="cityId">Miasto:</form:label >
				<form:select path="cityId">
					<form:option value="0">Wybierz miasto</form:option>
					<form:options items="${cities}" itemLabel="name" itemValue="id" />
				</form:select>
				<br />
				<br />
				<form:label path="hotelId">Hotel:</form:label>
				<form:select path="hotelId">
					<form:option value="0">Wybierz hotel</form:option>
					<form:options items="${hotels}" itemLabel="name" itemValue="id" />
				</form:select>
			</div>
		</div>
		<div class="right-panel panel panel-primary">
			<div class="panel-heading">
				<h3>Składniki</h3>
			</div>
			<div class="panel-body">
				<form:hidden path="touristEventComponentIds"/>
				<label for="touristEventComponents">Nowy składnik imprezy:</label>
				<select id="touristEventComponents"></select>
				<input type="button" value="Dodaj" />
				<label for="touristEventComponentList">Lista:</label>
				<table class="results-list">
					<tr>
						<th>Typ</th>
						<th>Nazwa</th>
						<th>Akcje</th>
					</tr>
				</table>
			</div>
		</div>
		<div class="long-panel text-align-center panel panel-primary">
			<div class="display-inline-block">			
				<input type="submit" value="Wyślij" class="btn btn-sm btn-success" />
				<input type="button" value="Anuluj" onclick="window.location='${basepath}/lista-imprez-turystycznych'" class="btn btn-sm btn-default" />
			</div>
		</div>
	</form:form>
</div>
</body>
</html>