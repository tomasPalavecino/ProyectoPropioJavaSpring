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
			<h1>Bienvenidos, ${usuario.name}, a Taller Web 1</h1>
		</div>
		<section>
			<h3><b>Lista de escuelas</b></h3>
			<div class="d-block"><a href="crearEscuela">Crear escuela</a></div>
			<div class="col-md-6">
				<table class="table user-list">
					<thead>
					<tr>
						<th class="text-center">Nombre</th>
						<th class="text-center">Direccion</th>
						<th class="text-center">Localidad</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${listaEscuelas}" var="escuela">
						<tr>
							<td class="text-center">${escuela.nombre}</td>
							<td class="text-center">${escuela.direccion}</td>
							<td class="text-center">${escuela.localidad}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</section>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>