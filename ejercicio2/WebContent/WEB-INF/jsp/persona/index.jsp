<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/comun/cabecera.jsp" %>

<%--  BEGIN: específico --%>
<style>
#tabla-form tr:last-child td {
	text-align: center;
}

#tabla-personas td:nth-child(3), #tabla-personas td:nth-child(4),
	#tabla-personas td:nth-child(5) {
	text-align: center;
}
</style>
<script>
	var modificar = function() {
		document.forms[0].submit();
	}
</script>
<%--  END: específico --%>

<h1>Personas</h1>

<div class="error">
	${error}
</div>

<form action='guardar' method='post'>
	<table id='tabla-form' class='tabla-centrada'>
		<tr>
			<td>Nombre</td>
			<td><input type='text' name='nombre'></td>
		</tr>
		<tr>
			<td>Apellido</td>
			<td><input type='text' name='apellido'></td>
		</tr>
		<tr>
			<td>Fecha Nacimiento</td>
			<td><input type='text' name='fechanacimiento'></td>
		</tr>
		<tr>
			<td colspan='2'>
				<input type='submit' name='btnAgregar' value='Agregar'>
				<input type='button' value='Modificar' onclick='modificar()'>
			</td>
		</tr>
	</table>
</form>
<br>
<table id='tabla-personas' class='tabla-centrada tabla-datos'>
	<thead>
		<tr>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Fecha Nacimiento</th>
			<th>Mostrar</th>
			<th>Eliminar</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="p" items="${personas}">
			<tr>
				<td>${p.nombre}</td>
				<td>${p.apellido}</td>
				<td>${p.fechanacimiento}</td>
				<td><a href='#'>mostrar</a></td>
				<td><a href='#'>eliminar</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="/WEB-INF/jsp/comun/pie.jsp"%>