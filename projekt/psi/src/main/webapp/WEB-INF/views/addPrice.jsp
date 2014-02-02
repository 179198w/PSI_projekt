<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="basepath" scope="request" value="<%=request.getContextPath()%>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nowa cena</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${basepath}/styles/style.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script>
jQuery(document).ready(function($) {
	
	$('#type').change(function() {
		var type = $('#type option:selected').text();
		if ('Bazowa'.toLowerCase() == type.toLowerCase()) {
			$('#touristEventComponent').hide();
		} else {
			$('#touristEventComponent').show();
			$('#touristEventComponentId').val('');
		}
	});
	
	$('#touristEventId').change(function() {
		var touristEventId = $('#touristEventId option:selected').val();
		$.post('${basepath}/ajax/lista-terminow', { touristEventId: touristEventId })
			.done(function(data) {
				$('#periodIds').html(data);
		 	});
		$.post('${basepath}/ajax/lista-skladnikow', { touristEventId: touristEventId })
		.done(function(data) {
			$('#touristEventComponentId').html(data);
	 	});
	});

});
</script>
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
					<li><a href="${basepath}/lista-terminow">Terminy</a></li>
					<li class="active"><a href="${basepath}/lista-cen">Cennik</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Słowniki <b class="caret"></b></a>
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
	<ol class="breadcrumb">
	  		<li><a href="${basepath}/lista-cen">Cennik</a></li>
	  		<li class="active">Nowa cena</li>
		</ol>
		<form:form method="post" enctype="multipart/form-data" class="form-horizontal" commandName="priceCommand">
			<div class="long-panel panel panel-primary">
				<div class="panel-heading">
					<h3>Informacje</h3>
				</div>
				<div class="panel-body">
					<form:hidden path="id"/>
					<spring:bind path="adultValue">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:label path="adultValue" cssClass="col-md-4 control-label">Cena normalna:</form:label>
							<div class="col-md-3">
								<form:input path="adultValue" cssClass="form-control input-md" />
								<form:errors path="adultValue" cssClass="help-block" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="childValue">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:label path="childValue" cssClass="col-md-4 control-label">Cena ulgowa:</form:label>
							<div class="col-md-3">
								<form:input path="childValue" cssClass="form-control input-md" />
								<form:errors path="childValue" cssClass="help-block" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="type">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:label path="type" class="col-md-4 control-label">Typ:</form:label>
							<div class="col-md-4">
								<form:select path="type" class="form-control">
									<form:option value="${null}">Wybierz typ</form:option>
									<form:options items="${types}" itemLabel="name"/>
								</form:select>
								<form:errors path="type" cssClass="help-block" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="touristEventId">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:label path="touristEventId" class="col-md-4 control-label">Impreza:</form:label>
							<div class="col-md-4">
								<form:select path="touristEventId" class="form-control">
									<form:option value="${null}">Wybierz imprezę</form:option>
									<form:options items="${touristEvents}" itemLabel="name" itemValue="id"/>
								</form:select>
								<form:errors path="touristEventId" cssClass="help-block" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="periodIds">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:label path="periodIds" class="col-md-4 control-label">Termin:</form:label>
							<div class="col-md-4">
								<form:select path="periodIds" class="form-control"  multiple="true">									
									<c:forEach items="${periods}" var="period">
										<form:option value="${period.id}">${period.from} - ${period.to}</form:option>
									</c:forEach>
								</form:select>
								<form:errors path="periodIds" cssClass="help-block" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="touristEventComponentId">
						<div id="touristEventComponent" style="display: none" class="form-group ${status.error ? 'has-error' : ''}">
							<form:label path="touristEventComponentId" class="col-md-4 control-label">Składnik:</form:label>
							<div class="col-md-4">
								<form:select path="touristEventComponentId" class="form-control">
									<form:option value="${null}">Wybierz składnik</form:option>
									<c:forEach items="${touristEventComponents}" var="touristEventComponent">
										<option value="${touristEventComponent.id}">${touristEventComponent.type.name} - ${touristEventComponent.name}</option>
									</c:forEach>
								</form:select>
								<form:errors path="touristEventComponentId" cssClass="help-block" />
							</div>
						</div>
					</spring:bind>
				</div>
			</div>
			<div class="long-panel text-align-center panel panel-primary">
				<div class="display-inline-block panel-body">
					<input type="submit" value="Wyślij" class="btn btn-sm btn-success" /> <input type="button" value="Anuluj" onclick="window.location='${basepath}/lista-cen'" class="btn btn-sm btn-default" />
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>