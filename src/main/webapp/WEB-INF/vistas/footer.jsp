<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="container" >
            <div class="row">
               <div class="col-md-4">
                  <div class="full">
                     <div class="footer-widget">
                        <div class="footer-logo">
                          <div class="slider-contant">
                              <h3 style="font-size:1.4em">Fulbito <span class="color-yellow">Torneos Amateur</span></h3>
                           </div>
                        </div>
                        <p>Organizamos torneos de un modo único.</p>
                     </div>
                  </div>
               </div>
               <div class="col-md-4">
                  <div class="full">
                     <div class="footer-widget">
                        <h3>Menu</h3>
                        <ul class="footer-menu">
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
			                                          <li><a href="fechas-en-preparaciono">Fechas en preparacion</a></li>
			                                          <li><a href="fechas-en-curso">Fechas en curso</a></li>
			                                        
												</c:if>
											 </c:if>
											 <li><a href="torneos">Torneos</a></li>
                        </ul>
                     </div>
                  </div>
               </div>
               <div class="col-md-4">
                  <div class="full">
                     <div class="footer-widget">
                        <h3>Contactenos</h3>
                        <ul class="address-list">
                           <li> Telefono y correo..</li>
                           <li><i class="fa fa-phone"></i> 123 456 7890</li>
                           <li><i style="font-size:20px;top:5px;" class="fa fa-envelope"></i> fulbito@gmail.com</li>
                        </ul>
                     </div>
                  </div>
               </div>
            </div>
         </div>
         <div class="footer-bottom">
            <div class="container">
               <p>Copyright © 2018 Grupo Rusia</p>
            </div>
         </div>