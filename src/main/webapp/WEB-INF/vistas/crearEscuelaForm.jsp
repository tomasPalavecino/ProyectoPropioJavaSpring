<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" >
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">

    <title>Crear nueva escuela</title>
</head>
<body>
    <div class = "container">
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

        <form:form action="crearEscuelaa" method="POST" modelAttribute="datosEscuela">
            <c:if test="${not empty mensajeError}">
                <label class="text-danger">${mensajeError}</label>
            </c:if>
            <form:input path="nombre" class="form-control" placeholder="Nombre" required="required"/>
            <form:input path="direccion" class="form-control" placeholder="Direccion" required="required"/>
            <form:input path="localidad" class="form-control" placeholder="Localidad" required="required"/>
            <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Crear escuela</button>
        </form:form>

        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
