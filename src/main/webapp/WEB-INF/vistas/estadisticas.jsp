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
               <h3>Fechas en curso</h3>
               <ul class="breadcrumb">
                  <li><a href="home">Home</a></li>
                  <li class="active">Fechas en curso</li>
               </ul>
            </div>
      </div>
      <section id="contant" class="contant main-heading team">
         <div class="row">
            <div class="container">
               <div class="contact">
               		<c:if test="${not empty error}">
			        <h4><span>${error}</span></h4>
			        <br>
		        	</c:if>	
                  <div class="col-md-8 col-md-offset-2">
                     <div class="contact-us">
					 <h2 style="text-align:center;">
				 	 ${torneo.nombreTorneo}<br/>
				 	 ${torneo.descripcionTorneo}</h2>
					 <table class="table table-sm">
						  <thead>
						    <tr>
						      <th scope="col">#</th>
						      <th scope="col">Equipo</th>
						      <th scope="col">Puntos</th>
						      <th scope="col">PJ</th>
						      <th scope="col">PG</th>
						      <th scope="col">PE</th>
						      <th scope="col">PP</th>
						      <th scope="col">GF</th>
						      <th scope="col">GC</th>
						      <th scope="col">DG</th>
						    </tr>
						  </thead>
						  <c:forEach items="${rank}" var="entry">
							<tbody>
						     <tr>
								    <td scope="row">
									<c:set var="numberOfRows" value="${numberOfRows+1}"/>
									<c:out value="${numberOfRows}"/></td>
									<td>${entry.equipo.nombreEquipo}</td>
									<td>${entry.puntos }</td>
									<td>${entry.partidosGanados+entry.partidosEmpatados+entry.partidosPerdidos}</td>
									<td>${entry.partidosGanados}</td>
									<td>${entry.partidosEmpatados}</td>
									<td>${entry.partidosPerdidos}</td>
									<td>${entry.golesAFavor}</td>
									<td>${entry.golesEnContra}</td>
									<td>${entry.golesAFavor-entry.golesEnContra}</td>
							</tr>
						  </tbody>
				   		 </c:forEach>		
						</table>
                     </div>
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