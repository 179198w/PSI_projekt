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
	function addTouristEvent() {
		var touristEvent = $('#touristEvent');
		var form = $('#command');
		var touristEventCount = $(':input[name^="touristEvents"]').length;

		$('<input type="hidden">').attr('name',
				'touristEvents[' + touristEventCount + ']').attr('value',
				touristEvent.val()).appendTo(form);
	}
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
						<form:label path="from" class="col-md-4 control-label">Data od:</form:label>
						<div class="col-md-3">
							<form:input path="from" class="form-control input-md" />
						</div>
					</div>
					<div class="form-group">
						<form:label path="to" class="col-md-4 control-label">Data do:</form:label>
						<div class="col-md-3">
							<form:input path="to" class="form-control input-md" />
						</div>
					</div>
					<div class="form-group">
						<form:label path="repeatPeriod" class="col-md-4 control-label">Powtórz termin:</form:label>
						<div class="col-md-3">
							<form:input path="repeatPeriod" class="form-control input-md" />
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
							<form:input path="periodSpace"
								class="form-control input-md" />
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
					<label>Nowa impreza w terminie:</label> <select id="touristEvent">
						<option>Wybierz imprezę turystyczną</option>
						<c:forEach items="${touristEvents}" var="touristEvent">
							<option value="${touristEvent.id}">${touristEvent.name}</option>
						</c:forEach>
					</select> <input type="button" value="Dodaj do listy"
						onclick="addTouristEvent()" /> <br /> <br />
					<table>
						<tr>
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
						onclick="window.location='${basepath}/lista-terminow'"
						class="btn btn-sm btn-default" />
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>