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
<title>Traveler</title>
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
				<sec:authorize access="hasRole('ROLE_USER')">
					<ul class="nav navbar-nav">
						<li><a href="${basepath}/lista-imprez-turystycznych">Imprezy</a></li>
						<li><a href="${basepath}/lista-skladnikow">Składniki</a></li>
						<li><a href="${basepath}/lista-katalogow">Katalogi</a></li>
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
					<p class="navbar-text navbar-right">Witaj <sec:authentication property="principal.username"/>, <a href="${basepath}/j_spring_security_logout">Wyloguj się</a></p>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<form class="form-inline navbar-right" style="margin-top: 7px;" role="form" name="f" action="/traveler-0.0.1/j_spring_security_check" method="POST">
					  <div class="form-group">
					    <label class="sr-only" for="j_username">Email address</label>
					    <input type="text" class="form-control" id="j_username" name="j_username" placeholder="użytkownik" />
					  </div>
					  <div class="form-group">
					    <label class="sr-only" for="j_password">Password</label>
					    <input type="password" class="form-control" id="j_password" name="j_password" placeholder="hasło" />
					  </div>
					  <button type="submit" class="btn btn-default">Zaloguj się</button>
					</form>
				</sec:authorize>
			</div>
		</div>
	</div>
	<div class="main-panel" style="height: 100%;">
		<form:form method="post" enctype="multipart/form-data" class="form-inline" role="form" commandName="queryCommand">
			<div class="long-panel panel panel-primary padding-10" style="margin-top: 200px;">
				<div style="margin: auto; width: 700px;">
					<div class="form-group float-left" style="width:623px;">
						<form:label path="query" class="sr-only">Szukane słowo:</form:label>
						<div class="width-100pc">
						<form:input path="query" class="form-control input-md" placeholder="szukane słowo" />
						</div>
					</div>
  					<button type="submit" class="btn btn-default margin-left-10">Szukaj</button>
			</div>	
			</div>
		</form:form>
	</div>
</body>
</html>