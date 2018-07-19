<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
     <link href='http://fonts.googleapis.com/css?family=Raleway:500' rel='stylesheet' type='text/css'>
<link href="css/Estilos.css" rel='stylesheet' type='text/css'>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->


<link href='http://fonts.googleapis.com/css?family=Raleway:500' rel='stylesheet' type='text/css'>
    <title>Login</title>
</head>
<body>
	
	<div class="container">
            
                <div class="middlePage">
                    <div class="page-header">
                      <h2 class="logo">Fulbito <span style="color:yellow;">Torneos Amateur</span></h2>
                    </div>
                      <div class="panel panel-primary">
                  <div class="panel-heading">
                    <h3 class="panel-title">Iniciar sesión</h3>
                  </div>
                  <div class="panel-body">
                  

                        <form:form action="validar-login" method="POST" modelAttribute="usuario">
                            <fieldset>
                              <form:input path="email" id="email" type="email" placeholder="Ingrese Email" class="form-control input-md" required="true"/>
                              <div class="spacing"><a href="#"></a></div>
                              <form:input path="password" type="password" id="password" placeholder="Ingrese Contraseña" class="form-control input-md" required="true"/>
                              <br>                            
                              
                              <a href="registrar" style="margin-left:1%;width:48%;" title="Registrarse" class="btn btn-lg btn-primary">Registrarse</a>
                              <button id="ingresar" style="margin-left:1%;width:48%;" name="ingresar" class="btn btn-lg btn-primary">Ingresar</button>
                            </fieldset>
                        </form:form>
                        
						<c:if test="${not empty error}">
					        <h4><span>${error}</span></h4>
				        </c:if>	
              
            </div>
            </div>
            </div>
	    
	</div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
    
</body>
</html>
