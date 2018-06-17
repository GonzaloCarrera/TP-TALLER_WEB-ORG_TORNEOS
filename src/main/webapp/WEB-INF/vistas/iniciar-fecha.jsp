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
		<form action='iniciar-fecha' method='POST'>
			<select class='form-control' id='idTorneo' name='idTorneo' required>
				 <c:forEach items="${torneos}" var="entry">
					<option value='${entry.id}'>${entry.nombreTorneo}</option>
	      		 </c:forEach>
      		 </select>
      		 <button type='submit' class='btn btn-success btn-lg' id='submit'>Enviar</button>
      	</form>
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>