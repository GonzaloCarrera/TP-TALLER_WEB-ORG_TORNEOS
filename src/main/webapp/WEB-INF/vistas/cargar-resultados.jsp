<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
   <!-- Basic -->
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
               <h3>Cargar resultados</h3>
               <ul class="breadcrumb">
                  <li><a href="home">Home</a></li>
                  <li><a href="fechas-en-curso">Fechas en Curso</a></li>
                  <li class="active">Cargar resultados</li>
               </ul>
            </div>
      </div>
      <section id="contant" class="contant main-heading team">
         <div class="row">
            <div class="container">
             
                     <div class="contact-us">
						<h2 stye="text-align:center">Cargar resultados</h2>
						<c:if test="${not empty partidos}">
							<form:form action="cargar-resultados" method="POST">
								<input style="margin-right:3px; width:33%;height:3.8em;" type="number" name="golesEquipo1" id="golesEquipo1" placeholder="Goles equipo 1" min=0 max=99 required/> 
								<select class='form-control' style="font-size: inherit;width:33%;height:3.8em;display: inline;" id='idPartido' name='idPartido' required>
									 <c:forEach items="${partidos}" var="entry">
										<option name="idPartido" value='${entry.id}'>${entry.equipo1.nombreEquipo} vs ${entry.equipo2.nombreEquipo}</option>
						      		 </c:forEach>
					      		 </select>
					      		 <input style="float:none;width:33%;height:3.8em;" type="number" name="golesEquipo2" id="golesEquipo2" placeholder="Goles equipo 2" min=0 max=99 required/> 
					      		 
					      		 <button class="btn btn-lg btn-success btn-block" Type="Submit"/>Cargar resultado</button>
					      	</form:form>
					      	 	<c:if test="${not empty error}"><h4><span>${error}</span></h4></c:if>
					      	<c:if test="${not empty exito}"><h4><span>${exito}</span></h4></c:if>	
					     </c:if>
					     <c:if test="${empty partidos}"><h4><span>Haz registrado el resultado de todos los partidos correspondientes a la fecha correctamente.</span></h4></c:if>
					     		   
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