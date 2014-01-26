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
	<title>Dodaj termin</title>
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${basepath}/styles/style.css">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
	<script>
		function addTouristEvent() {
			var touristEvent = $('#touristEvent');
			var form = $('#command');
			var touristEventCount = $(':input[name^="touristEvents"]').length;
			
			$('<input type="hidden">')
            	.attr('name', 'touristEvents[' + touristEventCount + ']')
            	.attr('value', touristEvent.val())
            	.appendTo(form);
		}
	</script>
</head>
<body>
<div class="main-panel">
	<form:form method="post" enctype="multipart/form-data">
		<div class="long-panel">
			<div class="panel-header">
				<h3>Informacje</h3>
			</div>
			<div class="panel-content">
				<form:label path="from">Data od:</form:label>
				<form:input path="from" />
				<br />
				<br />
				<form:label path="to">Data od:</form:label>
				<form:input path="to" />
				<br />
				<br />
				<form:label path="repeatPeriod">Powtórz termin:</form:label>
				<form:checkbox path="repeatPeriod" />
				<br />
				<br />
				<form:label path="repeatCount">Liczba powtórzeń:</form:label>
				<form:input path="repeatCount" />
				<br />
				<br />
				<form:label path="periodSpace">Odstęp terminów:</form:label>
				<form:input path="periodSpace" />
				<form:select path="periodSpaceType">
					<form:option value="days">dni</form:option>
					<form:option value="weeks">tygodni</form:option>
					<form:option value="months">miesięcy</form:option>
				</form:select>
				<br />
				<br />
			</div>
		</div>
		<div class="long-panel">
			<div class="panel-header">
				<h3>Imprezy w terminie</h3>
			</div>
			<div class="panel-content">
				<label>Nowa impreza w terminie:</label>
				<select id="touristEvent">
					<option>Wybierz imprezę turystyczną</option>
					<c:forEach items="${touristEvents}" var="touristEvent">
						<option value="${touristEvent.id}">${touristEvent.name}</option>
					</c:forEach>
				</select>
				<input type="button" value="Dodaj do listy" onclick="addTouristEvent()" />
				<br />
				<br />
				<table>
				<tr>
					<th>Nazwa</th>
					<th>Akcje</th>
				</tr>
				</table>
			</div>
		</div>
		<div class="long-panel text-align-center">
			<div class="display-inline-block">			
				<input type="submit" value="Wyślij" />
				<input type="button" value="Anuluj" onclick="window.location='${basepath}/lista-terminow'" />
			</div>
		</div>
	</form:form>
</div>
</body>
</html>