

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	</head>
	<body>
		<%@include file="header.jsp" %>
		<div class="container fixHeader">
			<br />
			<c:if test="${!usuario.esAdmin}">
				<br/><h2>Usuario</h2><br /> 
				<a href='mis-torneos?idUsuario=<c:out value="${usuario.id}" />'	class="btn btn-primary botonesMenu" role="button"> Mis torneos </a> 
				<a href='registrar-equipo' class="btn btn-primary botonesMenu" role="button"> Registrar Equipo </a> 
				<a href='listado-torneo-inscripcion-abierta' class="btn btn-primary botonesMenu" role="button"> Inscribirme a torneo </a>
				<a href='seleccionar-horario?idUsuario=<c:out value="${usuario.id}" />' class="btn btn-primary botonesMenu" role="button"> Seleccionar Horario </a>
				<a href='proximos-partidos?idUsuario=<c:out value="${usuario.id}" />' class="btn btn-primary botonesMenu" role="button"> Proximos partidos </a>
				<br/>
			</c:if>
			<c:if test="${usuario.esAdmin}">
				<br /> <h2>Administrador</h2> <br /> 
				<a href='registrar-torneo' class="btn btn-info botonesMenu botonesMenuAdmin"	role="button"> Registrar torneo </a>
				<a href='torneos-en-curso'	class="btn btn-info botonesMenu botonesMenuAdmin" role="button"> Torneos en curso </a>
				<a href='iniciar-fecha' class="btn btn-info botonesMenu botonesMenuAdmin" role="button"> Iniciar	fecha </a>
				<a href='fechas-en-preparacion' class="btn btn-info botonesMenu botonesMenuAdmin" role="button"> Fechas en preparacion </a>
				<a href='fechas-en-curso' class="btn btn-info botonesMenu botonesMenuAdmin" role="button"> Fechas en curso </a>
			</c:if>
		</div>
	<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>
			window.jQuery
					|| document
							.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
		</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>