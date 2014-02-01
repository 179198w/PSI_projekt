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
<title>Lista katalogów</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${basepath}/styles/style.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script>
		function publishCatalog(catalogId) {
			alert(catalogId);
		}
		function editCatalog(catalogId) {
			alert(catalogId);
		}
		function removeCatalog(catalogId) {
			doPost('${basepath}/usun-katalog', {catalogId: catalogId});
		}
		
		function doPost(url, params) {
            var $form = $('<form method="POST">').attr('action', url);
            $.each(params, function(name, value) {
                $('<input type="hidden">')
                    .attr('name', name)
                    .attr('value', value)
                    .appendTo($form);
            });
            $form.appendTo('body');
            $form.submit();
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
					<li class="active"><a href="${basepath}/lista-katalogow">Katalogi</a></li>
					<li><a href="${basepath}/lista-terminow">Terminy</a></li>
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
		<div class="left-panel panel panel-primary">
			<div class="panel-heading">
				<h3>Nowy katalog</h3>
			</div>
			<div class="panel-body">
				<input type="button" value="Stwórz"
					onclick="window.location='${basepath}/dodaj-katalog'"
					class="margin-auto margin-top-10 display-block btn btn-lg btn-default" />
			</div>
		</div>
		<div class="right-panel panel panel-primary">
			<div class="panel-heading">
				<h3>Wyniki wyszukiwania katalogów</h3>
			</div>
			<div class="panel-body">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Nazwa</th>
							<th class="text-align-center">Akcje</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="catalog" items="${catalogs}">
							<tr>
								<td>${catalog.name}</td>
								<td class="fit-cell-to-content"><input type="button"
									onclick="publishCatalog(${catalog.id});" value="publikuj"
									class="btn btn-xs btn-default" /> <input type="button"
									onclick="editCatalog(${catalog.id});" value="edytuj"
									class="btn btn-xs btn-default" /> <input type="button"
									onclick="removeCatalog(${catalog.id});" value="usuń"
									class="btn btn-xs btn-default" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="left-panel panel panel-primary">
			<div class="panel-heading">
				<h3>Filtrowanie wyników</h3>
			</div>
			<div class="panel-body">
				<form:form method="post" enctype="multipart/form-data" commandName="catalogFilterCommand" class="form-horizontal">
					<div class="form-group">
						<form:label path="name" class="col-md-4 control-label">Nazwa:</form:label>
						<div class="col-md-8">
							<form:input path="name" class="form-control input-md" />
						</div>
					</div>
					<div class="form-group">
						<input type="submit" class="margin-auto display-block btn btn-default" value="Filtruj" />
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>