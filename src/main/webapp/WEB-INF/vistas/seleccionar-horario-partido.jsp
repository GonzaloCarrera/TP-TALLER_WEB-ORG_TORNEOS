<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
			<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<form:form action="seleccionar-horario-partido" method="POST">
			    	<h3 class="form-signin-heading">Seleccionar rango horario para el partido de tu equipo 
			    	${horario.equipo.nombreEquipo} perteneciente a la fecha ${horario.fecha.id} 
			    	perteneciente al torneo ${horario.fecha.torneo.nombreTorneo}</h3>
					<hr class="colorgraph"><br>		

		
	<div class="form-group row">
    <label for="horaInicio" class="col-sm-2 col-form-label">Desde</label>
    <div class="col-sm-10">
      <input path="horaInicio" name="horaInicio" id="horaInicio" type="datetime-local" class="form-control" placeholder="hora inicio" />
    </div>
  </div>
  
  	<div class="form-group row">
    <label for="horaFin" class="col-sm-2 col-form-label">Hasta</label>
    <div class="col-sm-10">
      <input path="horaFin" name="horaFin" id="horaFin" type="datetime-local" class="form-control" placeholder="hora fin" />
    </div>
  </div>

      		 <input type="hidden" value="${horario.id}" name="idHorario" id="idHorario"/> 

 	
					
					<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Registrar horario</button>
				</form:form>
				


				<%--Bloque que es visible si el elemento error no está vacío	--%>
				<c:if test="${not empty error}">	
			        <h4><span>${error}</span></h4>
			        <br>
		        </c:if>	
			</div>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>
