<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="basepath" scope="request" value="<%= request.getContextPath() %>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Lista katalogów</title>
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${basepath}/styles/style.css">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
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
<div class="main-panel">
	<div class="left-panel">
		<div class="panel-header">
			<h3>Nowa katalog</h3>
		</div>
		<div class="panel-content">
			<input type="button" value="Stwórz" onclick="window.location='${basepath}/dodaj-katalog'" class="margin-auto margin-top-10 display-block"/>
		</div>
	</div>
	<div class="right-panel">
		<div class="panel-header">
			<h3>Wyniki wyszukiwania katalogów</h3>
		</div>
		<div class="panel-content">
			<table class="results-list">
				<tr>
					<th>Nazwa</th>
					<th>Akcje</th>
				</tr>
				<c:forEach var="catalog" items="${catalogs}">
					<tr>
						<td>
							${catalog.name}
						</td>
						<td>
							<input type="button" onclick="publishCatalog(${catalog.id});" value="publikuj" />
							<input type="button" onclick="editCatalog(${catalog.id});" value="edytuj" />
							<input type="button" onclick="removeCatalog(${catalog.id});" value="usuń" />
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
</body>
</html>