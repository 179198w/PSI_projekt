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
<title>Dodaj termin</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${basepath}/styles/style.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script>
	var touristEvents = [
		<c:forEach items="${periodCommand.touristEventIds}" var="touristEventId">
		'${touristEventId}',
		</c:forEach>
	];
	
	jQuery(document).ready(function($) {
		
		$('#addTouristEventButton').click(function() {
			var touristEvent = $('#touristEvent option:selected');
			if (touristEvent.val() != 0 && $.inArray(touristEvent.val(), touristEvents) == -1) {
				touristEvents.push(touristEvent.val());
				var touristEventTable = $('#touristEventTable');
				touristEventTable
					.append('<tr><td>' + touristEvent.text() + '</td><td><input type="button" value="usuń" class="btn btn-xs btn-default removeTouristEventButton" touristEventId="' + touristEvent.val() + '" /></td></tr>');
			}
		});
		
		$(document).on('click', '.removeTouristEventButton', function() {
			var touristEventId = $(this).attr('touristEventId');
			touristEvents.splice($.inArray(touristEventId, touristEvents), 1);
			$(this).closest('tr').remove();
		});
		
		$('input[type=\'submit\']').click(function() {
			var hiddenInputContainer = $('#touristEvents');
			for (var i = 0; i < touristEvents.length; i++) {
				$('<input type="hidden">')
				.attr('name', 'touristEventIds[' + i + ']')
				.attr('value', touristEvents[i])
				.appendTo(hiddenInputContainer);
			}
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
			</div>
		</div>
	</div>

	<div class="main-panel">
		<form:form method="post" enctype="multipart/form-data"
			class="form-horizontal" commandName="periodCommand">
			<div class="long-panel panel panel-primary">
				<div class="panel-heading">
					<h3>Informacje</h3>
				</div>
				<div class="panel-body">
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
					<div class="form-group">
						<form:label path="repeatPeriod" class="col-md-4 control-label">Powtórz termin:</form:label>
						<div class="col-md-3">
							<form:checkbox path="repeatPeriod"/>
						</div>
					</div>
					<div class="form-group">
						<form:label path="repeatCount" class="col-md-4 control-label">Liczba powtórzeń:</form:label>
						<div class="col-md-3">
							<form:input path="repeatCount" class="form-control input-md" />
						</div>
					</div>
					<div class="form-group">
						<form:label path="periodSpace" class="col-md-4 control-label">Odstęp terminów:</form:label>
						<div class="col-md-3">
							<form:input path="periodSpace" class="form-control input-md" />
						</div>
						<div class="col-md-2">
							<form:select path="periodSpaceType" class="form-control">
								<form:option value="days">dni</form:option>
								<form:option value="weeks">tygodni</form:option>
								<form:option value="months">miesięcy</form:option>
							</form:select>
						</div>
					</div>
				</div>
			</div>
			<div class="long-panel panel panel-primary">
				<div class="panel-heading">
					<h3>Imprezy w terminie</h3>
				</div>
				<div class="panel-body">
					<div id="touristEvents"></div>
					<div class="form-group">
						<label class="col-md-4 control-label width-200" for="touristEvent">Nowa impreza w terminie:</label>
						<div class="col-md-7">
							<select id="touristEvent" class="form-control float-left width-70pc">
								<option value="0">Wybierz imprezę</option>
								<c:forEach items="${touristEvents}" var="touristEvent">
									<option value="${touristEvent.id}">${touristEvent.name}</option>
								</c:forEach>
							</select> <input id="addTouristEventButton" type="button" class="btn btn-default margin-left-10" value="Dodaj" />
						</div>
					</div>

					<table id="touristEventTable" class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Nazwa</th>
								<th class="fit-cell-to-content text-align-center">Akcje</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${periodCommand.touristEventIds}" var="touristEventId">
							<tr>
								<c:forEach items="${touristEvents}" var="touristEvent">
									<c:if test="${touristEventId == touristEvent.id}">
										<td>${touristEvent.name}</td>
									</c:if>
								</c:forEach>
								<td><input type="button" value="usuń" class="btn btn-xs btn-default removeTouristEventButton" touristEventId="${touristEventId}" /></td>
							</tr>
						</c:forEach>						
						</tbody>
					</table>
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