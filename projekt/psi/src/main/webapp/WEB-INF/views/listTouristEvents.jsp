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
	<title>Lista imprez turystycznych</title>
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${basepath}/styles/style.css">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
	<script>
		function publishTouristEvent(touristEventId) {
			alert(touristEventId);
		}
		function editTouristEvent(touristEventId) {
			alert(touristEventId);
		}
		function removeTouristEvent(touristEventId) {
			doPost('${basepath}/usun-impreze-turystyczna', {touristEventId: touristEventId});
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
	<div class="left-panel panel panel-primary">
		<div class="panel-heading">
			<h3>Nowa impreza</h3>
		</div>
		<div class="panel-body">
			<input type="button" value="Stwórz" onclick="window.location='${basepath}/dodaj-impreze-turystyczna'" class="margin-auto margin-top-10 display-block display-block btn btn-lg btn-default"/>
		</div>
	</div>
	<div class="right-panel panel panel-primary">
		<div class="panel-heading">
			<h3>Wyniki wyszukiwania imprez</h3>
		</div>
		<div class="panel-body">
			<table class="results-list">
				<tr>
					<th>Nazwa</th>
					<th>Katalog</th>
					<th>Miasto</th>
					<th>Operator</th>
					<th>Akcje</th>
				</tr>
				<c:forEach var="touristEvent" items="${touristEvents}">
					<tr>
						<td>
							${touristEvent.name}
						</td>
						<td>
							${touristEvent.catalogs.isEmpty() ? "" : touristEvent.catalogs[0].name}
						</td>
						<td>
							${touristEvent.hotel.city.name}
						</td>
						<td>
							${touristEvent.operator.name}
						</td>
						<td>
							<input type="button" onclick="publishTouristEvent(${touristEvent.id});" value="publikuj" class="btn btn-xs btn-default" />
							<input type="button" onclick="editTouristEvent(${touristEvent.id});" value="edytuj" class="btn btn-xs btn-default" />
							<input type="button" onclick="removeTouristEvent(${touristEvent.id});" value="usuń" class="btn btn-xs btn-default" />
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
</body>
</html>