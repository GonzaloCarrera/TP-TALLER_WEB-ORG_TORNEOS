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
      
      <section id="contant" class="contant" style="padding-top: 50px;">
         <div class="container">
            <div class="row">
               <div class="col-lg-4 col-sm-4 col-xs-12">
                  <aside id="sidebar" class="left-bar">
                     <div class="banner-sidebar fondo-cajas-home">
                        <img class="img-responsive" src="images/ronaldo1.jpg" alt="#" />
                        <h3>Grandes partidos...</h3>
                     </div>
                  </aside>
                  <aside id="sidebar" class="left-bar">
                     <div class="banner-sidebar fondo-cajas-home">
                        <img class="img-responsive" src="images/messi1.jpg" alt="#" />
                        	<h3><a href="torneos">Ver estadisticas</a></h3>
                     </div>
                  </aside>
               <c:if test="${not empty user}">
               	  
                  <h2 style="text-align:center;">Proximos partidos</h2>
                  
                  <aside id="sidebar" class="left-bar">
                     <div class="feature-matchs">
                     <c:if test="${not empty partidos}">
                        <table class="table table-bordered table-hover">
                           <thead>
                              <tr>
                                 <th>Horario</th>
                                 <th>Partido</th>
                                 <th>Torneo</th>
                              </tr>
                           </thead>
                             <c:forEach items="${partidos}" var="entry" varStatus="loop">
                           <tbody>      
                              <tr>
                                 <td>${entry.horario}</td>
                                 <td>${entry.equipo1.nombreEquipo} vs ${entry.equipo2.nombreEquipo}</td>
                                 <td>${entry.fecha.torneo.nombreTorneo}</td>
                              </tr>    
                           </tbody>
                           </c:forEach>  
                        </table>
                        </c:if>
                        <c:if test="${empty partidos}">
                        <h3>No tienes partidos proximos a jugar.</h3>
                        </c:if>
                     </div>
                  </aside>
               </c:if>
                  
               </div>
               <div class="col-lg-8 col-sm-8 col-xs-12" >
                  <div class="news-post-holder" >
                     <div class="news-post-widget">
                        <img class="img-responsive fondo-cajas-home" src="images/leicester.jpg" alt="">
                        <div class="news-post-detail">
                           <span class="date">20 marzo 2018</span>
                           <c:if test="${not empty user}">
                           		<h2><a href="registrar-equipo">Registra tu equipo</a></h2>
                           </c:if>
                           <c:if test="${empty user}">
                           		<h2><a href="registrar">Registra tu equipo</a></h2>
                           </c:if>    
                           <p>En simples pasos registra tu equipo y participa de los torneos</p>
                        </div>
                     </div>
                  </div>
                  <div class="news-post-holder">
                     <div class="news-post-widget">
                        <img class="img-responsive fondo-cajas-home" src="images/mancity.jpg" alt="">
                        <div class="news-post-detail">
                           <span class="date">21 mayo 2018</span>
                           <c:if test="${not empty user}">
                           		<h2><a href='listado-torneo-inscripcion-abierta?idUsuario=<c:out value="${user.id}" />'>Registrate en los torneos</a></h2>
                           </c:if>
                           <c:if test="${empty user}">
                           		<h2><a href="registrar">Registrate en los torneos</a></h2>
                           </c:if>
                           <p>Registra a tu equipo en los horarios mas comodos para vos y tus amigos</p>
                        </div>
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