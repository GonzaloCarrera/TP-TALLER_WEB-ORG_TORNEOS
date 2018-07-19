<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.min.js"></script>
    <link href='http://fonts.googleapis.com/css?family=Raleway:500' rel='stylesheet' type='text/css'>
	<link href="css/Estilos.css" rel='stylesheet' type='text/css'>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<link href='http://fonts.googleapis.com/css?family=Raleway:500' rel='stylesheet' type='text/css'>
    <title>Fulbito</title>
</head>
<body>
	
	<div class="container">
            
		<div class="middlePage" >
		  <div class="page-header">
		    <h2 class="logo">Fulbito <span style="color:yellow;">Torneos Amateur</span></h2>
		  </div>
		      <div class="container">
		          <div class="row centered-form">
		          <div class="col-xs-12 col-sm-6 col-md-5">
		            <div class="panel panel-primary">
		              <div class="panel-heading">
		                <h3 class="panel-title">Registrar Usuario <small></small></h3>
		              </div>
		              <div class="panel-body">
		                <form:form action="registrar-usuario" method="POST" modelAttribute="usuario">
		            
		                    
		                      <div class="form-group">
		                        <form:input path="username" id="username" type="text" class="form-control input-sm" placeholder="Nombre de usuario" required="true"/>
		                      </div>
		                    
		       
		                  <div class="form-group">
		                    <form:input path="email" type="email" id="email" class="form-control input-sm" placeholder="Email" required="true"/>
		                  </div>
		              
		                    
		                      <div class="form-group">
		                        <form:input path="password" type="password" id="password" class="form-control input-sm" placeholder="Contraseña" required="true"/>
		                      </div>
		                 
		              
		                 	 <a href="login.php" title="volver" style="margin-left:1%;width:48%;" class="btn btn-lg btn-primary">Volver</a>
		                      <button type="submit" name="guardar" style="margin-left:1%;width:48%;" class="btn btn-lg btn-primary">Registrarse</button>
		                </form:form>   
		              </div>
		               <c:if test="${not empty error}">
					        <h4 style="text-align:center">${error}</h4>
					        <br>
				        </c:if>
		            </div>
		          </div>
		        </div>
		      </div>
		</div>
	    
	</div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
    
</body>
</html>