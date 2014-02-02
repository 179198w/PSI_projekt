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
<title>Edytuj termin</title>
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
					<li><a href="${basepath}/lista-imprez-turystycznych">Imprezy</a></li>
					<li><a href="${basepath}/lista-skladnikow">Składniki</a></li>
					<li><a href="${basepath}/lista-katalogow">Katalogi</a></li>
					<li class="active"><a href="${basepath}/lista-terminow">Terminy</a></li>
					<li><a href="${basepath}/lista-cen">Cennik</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Słowniki <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${basepath}/lista-hoteli">Słownik hoteli</a></li>
							<li><a href="${basepath}/lista-miast">Słownik miast</a></li>
							<li><a href="${basepath}/lista-panstw">Słownik państw</a></li>
						</ul></li>
				</ul>
				<p class="navbar-text navbar-right">Witaj <sec:authentication property="principal.username"/>, <a href="${basepath}/j_spring_security_logout">Wyloguj się</a></p>
			</div>
		</div>
	</div>

	<div class="main-panel">
		<form:form method="post" enctype="multipart/form-data"
			class="form-horizontal" commandName="periodEditCommand">
			<div class="long-panel panel panel-primary">
				<div class="panel-heading">
					<h3>Informacje</h3>
				</div>
				<div class="panel-body">
					<form:hidden path="id"/>
					<spring:bind path="from">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:label path="from" cssClass="col-md-4 control-label">Data od:</form:label>
							<div class="col-md-4">
								<form:input path="from" cssClass="form-control input-md" placeholder="dd-MM-yyyy"/>
								<form:errors path="from" cssClass="help-block" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="to">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:label path="to" cssClass="col-md-4 control-label">Data do:</form:label>
							<div class="col-md-4">
								<form:input path="to" cssClass="form-control input-md" placeholder="dd-MM-yyyy"/>
								<form:errors path="to" cssClass="help-block" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="touristEventId">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:label path="touristEventId" cssClass="col-md-4 control-label">Impreza:</form:label>
							<div class="col-md-4">
								<form:select path="touristEventId" class="form-control">
									<form:option value="${null}">Wybierz imprezę</form:option>
									<form:options items="${touristEvents}" itemLabel="name" itemValue="id" />
								</form:select>
								<form:errors path="touristEventId" cssClass="help-block" />
							</div>
						</div>
					</spring:bind>
				</div>
			</div>
			<div class="long-panel text-align-center panel panel-primary">
				<div class="display-inline-block panel-body">
					<input type="submit" value="Wyślij" class="btn btn-sm btn-success" />
					<input type="button" value="Anuluj"
						onclick="window.location='${basepath}/lista-terminow'"
						class="btn btn-sm btn-default" />
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>