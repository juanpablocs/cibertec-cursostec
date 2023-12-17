<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List" 
    import="cibertec.models.Curso"
%>
<!DOCTYPE html>
<html>
<head>
    <title>CursosTec Cuenta</title>
    <jsp:include page="layout/meta.jsp" />
</head>
<body>
<jsp:include page="layout/header.jsp" />
<div class="container py-4">
    <h1>Administrador de Mantenimiento</h1>
    <p>Esta pagina tiene acceso restringido.</p>
</div>

<div class="container" style="min-height:600px">
	<div class="row">
    <div class="col-sm d-flex justify-content-center">
      <a href="cursos" type="button" class="btn btn-primary">Cursos</a>
    </div>
    <div class="col-sm d-flex justify-content-center">
      <a href="profesores" type="button" class="btn btn-primary">Profesores</a>
    </div>
    <div class="col-sm d-flex justify-content-center">
      <a href="usuarios" type="button" class="btn btn-primary">Usuarios</a>
    </div>
  </div>
</div>

<jsp:include page="layout/footer.jsp" />
</body>
</html>
