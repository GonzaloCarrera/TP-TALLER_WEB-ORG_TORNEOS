<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="css/bootstrap.min.css" rel="stylesheet" >
</head>
<body>


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

	<ul class='nav navbar-nav navbar-right'>
					<c:set var="usuario" value="${usuario}" scope="session" />
					<c:if test="${empty usuario.username}">
						<li><a href='registrar'><span class='glyphicon glyphicon-user'></span>&nbsp;Registrarse</a></li>
						<li><a href='login'><span class='glyphicon glyphicon-log-in'></span>&nbsp;Login</a></li>
					</c:if>
					<c:if test="${not empty usuario.username}">
						<li style="color: #9d9d9d; padding-top: 1em;">
								Bienvenido, ${usuario.username}.
						</li>
						<li><a href='logout'><span class='glyphicon glyphicon-log-out'></span>&nbsp;Logout</a></li>
					</c:if>
				</ul>

</body>
</html>