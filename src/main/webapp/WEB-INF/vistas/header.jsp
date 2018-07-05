<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="css/bootstrap.min.css" rel="stylesheet" >
</head>
<body>
<!-- 	<header class="clear"> -->
	      <!-- Fixed navbar -->
	      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	        <a class="navbar-brand" href="#">Canchita de Futbol</a>
	        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
	          <span class="navbar-toggler-icon"></span>
	        </button>
	        <div class="collapse navbar-collapse" id="navbarCollapse">
	          <ul class="navbar-nav mr-auto">
	          </ul>
<!-- 	          <form class="form-inline mt-2 mt-md-0"> -->
<!-- 	            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search"> -->
<!-- 	            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button> -->
<!-- 	          </form> -->
			  	<c:if test="${not empty usuario.username}">
						<h4 id="nombreUsuario" style="color: #9d9d9d;">${usuario.username}</h4>
						<li><a href='logout'><span class='glyphicon glyphicon-log-out'></span>&nbsp;Logout</a></li>
				</c:if>
				
	        </div>
	      </nav>
	    </header>
</body>
</html>