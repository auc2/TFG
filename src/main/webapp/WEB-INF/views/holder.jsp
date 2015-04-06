<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>

<html>
<head>
<title>Monitors de natació</title>
<style type="text/css">
<%@include file="mystyle.css" %>
</style>

<div id="titol">
	<h1>Activitats Aquàtiques Natació Cervera</h1>
</div>

</head>
<body>
	<ul class="navbar">
		<li><a href="/mainpage">Inici</a>
		<li><a href="/teachers/teacherForm">Afegir monitor</a>
		<li><a href="/teachers">Llistar monitors</a>
		<li><a href="/swimmers/swimmerForm">Afegir nedador</a>
		<li><a href="/swimmers">Llistar nedadors</a>
			
		<li><a href="/swimmerGroups/swimmerGroupForm">Nova classe</a>
		<li><a href="/swimmerGroups">Llista classes</a>
	</ul>
