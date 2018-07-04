<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	</head>
	<body>
		<div class = "container">
		
		
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
		<br/><br/>
	USER<br/>
			<a
									href='mis-torneos?idUsuario=<c:out value="${usuario.id}" />'
									class="btn btn-primary" role="button"> Mis torneos
								</a>
			<a
									href='registrar-equipo'
									class="btn btn-primary" role="button"> Registrar Equipo
								</a>		
			<a
									href='listado-torneo-inscripcion-abierta'
									class="btn btn-primary" role="button"> Inscribirme a torneo
								</a>	
			<a
									href='seleccionar-horario?idUsuario=<c:out value="${usuario.id}" />'
									class="btn btn-primary" role="button"> Seleccionar Horario
								</a>
			<a
									href='proximos-partidos?idUsuario=<c:out value="${usuario.id}" />'
									class="btn btn-primary" role="button"> Proximos partidos
								</a>
			
			<br/>	<br/>
			ADMIN	<br/>
      		 <a
									href='registrar-torneo'
									class="btn btn-info" role="button"> Registrar torneo
								</a>
      		 
      		 <a
									href='torneos-en-curso'
									class="btn btn-info" role="button"> Torneos en curso
								</a>
			 <a
									href='iniciar-fecha'
									class="btn btn-info" role="button"> Iniciar fecha
								</a>
			 <a
									href='fechas-en-preparacion'
									class="btn btn-info" role="button"> Fechas en preparacion
								</a>
			 <a
									href='fechas-en-curso'
									class="btn btn-info" role="button"> Fechas en curso
								</a>
		
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>