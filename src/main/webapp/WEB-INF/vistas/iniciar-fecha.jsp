<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
   <!-- Basic -->
   <head>
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
   <title>Fulbito</title>
   <meta name="keywords" content="">
   <meta name="description" content="">
   <meta name="author" content="">
   <link rel="shortcut icon" href="" type="image/x-icon" />
   <link rel="apple-touch-icon" href="">
   <link rel="stylesheet" href="css/bootstrap.min.css">
   <link rel="stylesheet" href="css/style.css">
   <link rel="stylesheet" href="css/colors.css">
   <link rel="stylesheet" href="css/versions.css">
   <link rel="stylesheet" href="css/responsive.css">
   <link rel="stylesheet" href="css/custom.css">
   <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
   <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
   <link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
   </head>
   <body class="game_info" data-spy="scroll" data-target=".header">
      <!-- LOADER -->
      <div id="preloader">
         <img class="preloader" src="images/loading-img.gif" alt="">
      </div>
      <%@include file="header.jsp" %>
      <div class="inner-information-text">
            <div class="container">
               <h3>Iniciar Fecha</h3>
               <ul class="breadcrumb">
                  <li><a href="home">Home</a></li>
                  <li class="active">Iniciar Fecha</li>
               </ul>
            </div>
      </div>
      <section id="contant" class="contant main-heading team">
         <div class="row">
            <div class="container">
               <div class="contact">
                     <div class="contact-us">
                    <c:if test="${not empty error}">
				        <div class="alert alert-danger" style="text-align:center;" role="alert">${error}</div>
		        	</c:if>	
                        <h2 style="text-align:center">Iniciar Fecha</h2>
                         <c:if test="${empty torneos}">
            		   		<h4>No hay ningun torneo en curso.</h4>
              			 </c:if>
              			 <c:if test="${not empty torneos}">
						<table class="table table-sm">
						  <thead>
						    <tr>
						      <th scope="col">Nombre del Torneo</th>
						      <th scope="col">Descripcion</th>
						      <th scope="col">Cupo de Equipos</th>
						      <th scope="col">Opciones</th>
						    </tr>
						  </thead>
						  <c:forEach items="${torneos}" var="entry">
							<tbody>
						     <tr>
								<td>${entry.nombreTorneo}</td>
								<td>${entry.descripcionTorneo}</td>
								<td>${entry.cantidadDeEquipos}</td>
								<td><a
											href='seleccionar-horario-fecha?idTorneo=<c:out value="${entry.id}" />'
											class="btn btn-lg btn-success btn-block" role="button"> Iniciar fecha
										</a></td>
							   </tr>
						 	  </tbody>
						   </c:forEach>
						</table>
					</c:if>
                     </div>
               </div>
            </div>
         </div>
      </section>
      <footer id="footer" class="footer">
         <%@include file="footer.jsp" %> 
      </footer>
      <a href="#home" data-scroll class="dmtop global-radius"><i class="fa fa-angle-up"></i></a>
      <!-- ALL JS FILES -->
      <script src="js/all.js"></script>
      <!-- ALL PLUGINS -->
      <script src="js/custom.js"></script>
   </body>
</html>

