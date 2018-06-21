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
 

FECHAS PERTENECIENTES AL TORNEO
								      	
						      	
	<table class="table table-sm">
  <thead>
    <tr>
   	  <th scope="col">Fecha</th>
      <th scope="col">idFecha</th>
      <th scope="col">estado</th>
      <th scope="col">t.nombreTorneo</th>
      <th scope="col">t.cantidadDeEquipos</th>
      <th scope="col">t.estado</th>
    </tr>
  </thead>
  
  	<c:set var="numberOfRows" value="0"/>	
  <c:forEach var="entry" items="${fechas}">
  <tbody>
    <tr>
    <th scope="row">
<c:set var="numberOfRows" value="${numberOfRows+1}"/>
<c:out value="${numberOfRows}"/></th>
      <td>${entry.id}</td>
      <td>${entry.estado}</td>
      <td>${entry.torneo.nombreTorneo}</td>
      <td>${entry.torneo.cantidadDeEquipos}</td>
      <td>${entry.torneo.estado}</td>
    </tr>
  </tbody>
   	</c:forEach>
  
</table>
								      	
								      	
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>