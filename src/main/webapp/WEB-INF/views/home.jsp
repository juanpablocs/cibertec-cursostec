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
    <title>CursosTec</title>
    <jsp:include page="layout/meta.jsp" />
</head>
<body>
<jsp:include page="layout/header.jsp" />
<div class="container py-4">
    <h1>Bienvenido</h1>
    <p>Esta es la página de inicio de los cursos.</p>
</div>
<div class="container">
    <h1>Listado de Cursos</h1>
    
    <div class="row">
    	<% 
	        List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
	        if(cursos == null) {
	        	%>
	        	<h2>no hay cursos</h2>
	        <% 	
	        } else {
	        for (Curso curso : cursos) { 
        %>
    	<div class="col-md-4">
	        <a href="curso/<%= curso.getId() %>" class="card bg-white">
	            <img src="<%= curso.getImage() != null ? curso.getImage() : "https://via.placeholder.com/150" %>" class="card-img-top" style="height:200px" alt="Imagen del Curso"/>
	            <div class="card-body">
	                <h5 class="card-title"><%= curso.getNombre() %></h5>
	                <p class="card-text" style="color:#999;display:block;height:50px;overflow:hidden;"><%= curso.getDescripcion() %></p>
	            </div>
	        </a>
	    </div>
	    <% }} %>
    </div>
</div>
<jsp:include page="layout/footer.jsp" />
</body>
</html>
