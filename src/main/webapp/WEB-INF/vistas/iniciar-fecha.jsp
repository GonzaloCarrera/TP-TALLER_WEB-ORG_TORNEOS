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
		<form action='iniciar-fecha' method='POST'>
			<select class='form-control' id='idTorneo' name='idTorneo' required>
				 <c:forEach items="${torneos}" var="entry">
					<option value='${entry.id}'>${entry.nombreTorneo}</option>
	      		 </c:forEach>
      		 </select>
      		 <button type='submit' class='btn btn-success btn-lg' id='submit'>Enviar</button>
      	</form>
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>