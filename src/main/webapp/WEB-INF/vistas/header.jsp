<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
</head>
		<section id="top">
         <header>
            <div class="container">
               <div class="header-top">
                  <div class="row">
                     <div class="col-md-6">
                        <div class="full">
                           <!--div class="logo">
                              <a href="home"><img src="images/logo.png" alt="#" /></a>
                           </div-->
                        </div>
                     </div>
                     <div class="col-md-6">
                        <div class="right_top_section">
                        <c:if test="${not empty user.username}">
                           <ul class="login">
							  <li class="login-modal">
                                 <a class="login" style="background-color:white; color:black;'">Bienvenido, ${user.username}.</a>
                              </li>
                              <li class="login-modal">
                                 <a href='logout' class="login">Logout</a>
                              </li>
                             </ul>
                         </c:if>
                              
                        <c:if test="${empty user.username}">      
                           <ul class="login">
                              <li>
                                 <div class="cart-option">
                                    <a href="registrar">Registrarse</a>
                                 </div>
                              </li>
                               <li>
                                 <div class="cart-option">
                                    <a href='login'>Login</a>
                                 </div>
                              </li>
                           </ul>
                        </c:if>
                           <!-- end button section -->
                        </div>
                     </div>
                  </div>
               </div>

                  <div class="row">
                     <div class="col-md-12">
                        <div class="full">
                           <div class="main-menu-section">
                              <div class="menu">
                                 <nav class="navbar navbar-inverse">
                                    <div class="navbar-header">
                                       <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".js-navbar-collapse">
                                       <span class="sr-only">Toggle navigation</span>
                                       <span class="icon-bar"></span>
                                       <span class="icon-bar"></span>
                                       <span class="icon-bar"></span>
                                       </button>
                                       <a class="navbar-brand" href="#">Menu</a>
                                    </div>
                                    <div class="collapse navbar-collapse js-navbar-collapse">
                                       <ul class="nav navbar-nav"> 
                                       	 <c:set var="user" value="${user}" scope="session" />
											<c:if test="${not empty user.username}">
												<c:if test="${!user.esAdmin}">
			                                          <li class="active"><a href="mis-torneos?idUsuario=<c:out value="${user.id}" />">Mis torneos</a></li>
			                                          <li><a href="registrar-equipo">Registrar Equipo</a></li>
			                                          <li><a href="listado-torneo-inscripcion-abierta?idUsuario=<c:out value="${user.id}" />">Inscribirme a torneo</a></li>
			                                          <li><a href="seleccionar-horario?idUsuario=<c:out value="${user.id}" />">Seleccionar Horario</a></li>
			                                          <li><a href="proximos-partidos?idUsuario=<c:out value="${user.id}" />">Proximos partidos</a></li>
			                                   	</c:if>
			                                   	<c:if test="${user.esAdmin}">
		                                          <li class="active"><a href="registrar-torneo">Registrar torneo</a></li>
			                                          <li><a href="iniciar-fecha">Iniciar fecha</a></li>
			                                          <li><a href="fechas-en-preparacion">Fechas en preparacion</a></li>
			                                          <li><a href="fechas-en-curso">Fechas en curso</a></li>
												</c:if>
											 </c:if>
											 <li><a href="torneos">Torneos</a></li>
                                       </ul>
                                    </div>
                                    <!-- /.nav-collapse -->
                                 </nav>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            
         </header>
         <div class="inner-page-bannerMain">
            <div class="container">
                     <div class="carousel-caption">
                        <div class="col-lg-5 col-md-7 col-sm-12 col-xs-12"></div>
                        <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
                           <div class="slider-contant">
                              <h3 style="text-align:right;">Fulbito<br><span class="color-yellow">Torneos Amateur</span></h3>
                           </div>
                        </div>
                     </div>
            </div>
         </div>
      </section>