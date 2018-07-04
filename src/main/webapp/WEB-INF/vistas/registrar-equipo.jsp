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
				<form:form action="registrar-equipo" method="POST">
			    	<h3 class="form-signin-heading">Registrar Equipo</h3>
					<hr class="colorgraph"><br>		
					
	<div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">Nombre del Equipo</label>
    <div class="col-sm-10">
      <input id="nombreEquipo" name="nombreEquipo" type="text" class="form-control" placeholder="Nombre del equipo" required/>
    </div>
  </div>

      <input type="hidden" name="idUsuario" value="${usuario.id}" id="usuario"/>    	
			  		
			  		<div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">1° Jugador</label>
    <div class="col-sm-10">
      <input id="nombreJugador1" name="nombreJugador1" type="text" class="form-control" placeholder="Nombre del jugador" required/>
    </div>
  </div>
			
			
		<div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">2° Jugador</label>
    <div class="col-sm-10">
      <input id="nombreJugador2" name="nombreJugador2" type="text" class="form-control" placeholder="Nombre del jugador" required/>
    </div>
  </div>	
  
  		<div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">3° Jugador</label>
    <div class="col-sm-10">
      <input id="nombreJugador3" name="nombreJugador3" type="text" class="form-control" placeholder="Nombre del jugador" required/>
    </div>
  </div>
  
  		<div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">4° Jugador</label>
    <div class="col-sm-10">
      <input id="nombreJugador4" name="nombreJugador4" type="text" class="form-control" placeholder="Nombre del jugador" required/>
    </div>
  </div>
  
  		<div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">5° Jugador</label>
    <div class="col-sm-10">
      <input id="nombreJugador5" name="nombreJugador5" type="text" class="form-control" placeholder="Nombre del jugador" required/>
    </div>
  </div>

  
  		<div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">6° Jugador</label>
    <div class="col-sm-10">
      <input id="nombreJugador6" name="nombreJugador6" type="text" class="form-control" placeholder="Nombre del jugador" required/>
    </div>
  </div>
  
  		<div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">7° Jugador</label>
    <div class="col-sm-10">
      <input id="nombreJugador7" name="nombreJugador7" type="text" class="form-control" placeholder="Nombre del jugador" required/>
    </div>
  </div>
  
  		<div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">8° Jugador</label>
    <div class="col-sm-10">
      <input id="nombreJugador8" name="nombreJugador8" type="text" class="form-control" placeholder="Nombre del jugador" required/>
    </div>
  </div>
  
  		<div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">9° Jugador</label>
    <div class="col-sm-10">
      <input id="nombreJugador9" name="nombreJugador9" type="text" class="form-control" placeholder="Nombre del jugador" required />
    </div>
  </div>
  
  		<div class="form-group row">
    <label for="inputEmail3" class="col-sm-2 col-form-label">10° Jugador</label>
    <div class="col-sm-10">
      <input id="nombreJugador10" name="nombreJugador10" type="text" class="form-control" placeholder="Nombre del jugador" required />
    </div>
  </div>	
					
					<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Registrar Equipo</button>
				</form:form>
				


				<%--Bloque que es visible si el elemento error no estÃ¡ vacÃ­o	--%>
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
