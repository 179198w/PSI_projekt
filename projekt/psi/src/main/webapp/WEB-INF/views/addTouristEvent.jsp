<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="basepath" scope="request"
	value="<%=request.getContextPath()%>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista imprez turystycznych</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${basepath}/styles/style.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="${basepath}">Traveler</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a
						href="${basepath}/lista-imprez-turystycznych">Imprezy</a></li>
					<li><a href="${basepath}/lista-skladnikow">Składniki</a></li>
					<li><a href="${basepath}/lista-katalogow">Katalogi</a></li>
					<li><a href="${basepath}/lista-terminow">Terminy</a></li>
					<li><a href="${basepath}/lista-cennikow">Cenniki</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Słowniki <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${basepath}/lista-hoteli">Słownik hoteli</a></li>
							<li><a href="${basepath}/lista-miast">Słownik miast</a></li>
							<li><a href="${basepath}/lista-panstw">Słownik państw</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main-panel">
		<form:form method="post" enctype="multipart/form-data"
			class="form-horizontal">
			<div class="long-panel panel panel-primary">
				<div class="panel-heading">
					<h3>Informacje</h3>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<form:label path="name" class="col-md-4 control-label">Nazwa:</form:label>
						<div class="col-md-4">
							<form:input path="name" class="form-control input-md" />
						</div>
					</div>
					<div class="form-group">
						<form:label path="description" class="col-md-4 control-label">Opis:</form:label>

						<div class="col-md-3">
							<form:textarea path="description" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<form:label path="operator" class="col-md-4 control-label">Operator:</form:label>
						<div class="col-md-4">
							<form:input path="operator" class="form-control input-md" />
						</div>
					</div>
					<div class="form-group">
						<form:label path="statue" class="col-md-4 control-label">Regulamin:</form:label>
						<div class="col-md-4">
							<input type="file" name="statue" class="input-file" />
						</div>
					</div>
					<div class="form-group">
						<form:label path="peopleLimit" class="col-md-4 control-label">Liczba miejsc:</form:label>
						<div class="col-md-4">
							<form:input path="peopleLimit" class="form-control input-md" />
						</div>
					</div>
					<div class="form-group">
						<form:label path="photos" class="col-md-4 control-label">Zdjęcia:</form:label>
						<div class="col-md-4">
							<input type="file" name="photos[0]" class="input-file" />
						</div>
					</div>
				</div>
			</div>
			<div class="left-panel panel panel-primary">
				<div class="panel-heading">
					<h3>Miejsce</h3>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<form:label path="countryId" class="col-md-4 control-label">Państwo:</form:label>
						<div class="col-md-8">
							<form:select path="countryId" class="form-control">
								<form:option value="0">Wybierz państwo</form:option>
								<form:options items="${countries}" itemLabel="name"
									itemValue="id" />
							</form:select>

						</div>
					</div>
					<div class="form-group">
						<form:label path="cityId" class="col-md-4 control-label">Miasto:</form:label>
						<div class="col-md-8">
							<form:select path="cityId" class="form-control">
								<form:option value="0">Wybierz miasto</form:option>
								<form:options items="${cities}" itemLabel="name" itemValue="id" />
							</form:select>

						</div>
					</div>
					<div class="form-group">
						<form:label path="hotelId" class="col-md-4 control-label">Hotel:</form:label>
						<div class="col-md-8">
							<form:select path="hotelId" class="form-control">
								<form:option value="0">Wybierz hotel</form:option>
								<form:options items="${hotels}" itemLabel="name" itemValue="id" />
							</form:select>

						</div>
					</div>
				</div>
			</div>
			<div class="right-panel panel panel-primary">
				<div class="panel-heading">
					<h3>Składniki</h3>
				</div>
				<div class="panel-body">
					<form:hidden path="touristEventComponentIds" />
					<label for="touristEventComponents">Nowy składnik imprezy:</label>
					<select id="touristEventComponents"></select> <input type="button"
						value="Dodaj" /> <label for="touristEventComponentList">Lista:</label>
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
				<div class="display-inline-block panel-body">
					<input type="submit" value="Wyślij" class="btn btn-sm btn-success" />
					<input type="button" value="Anuluj"
						onclick="window.location='${basepath}/lista-imprez-turystycznych'"
						class="btn btn-sm btn-default" />
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>