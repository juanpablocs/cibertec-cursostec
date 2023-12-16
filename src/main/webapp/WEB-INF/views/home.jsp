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
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Descripción</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
            if(cursos == null) {
            	%>
            	<tr><td>no hay cursos</td></tr>
            <% 	
            } else {
            for (Curso curso : cursos) { 
            %>
                <tr>
                    <td><%= curso.getId() %></td>
                    <td><%= curso.getNombre() %></td>
                    <td><%= curso.getDescripcion() %></td>
                </tr>
            <% }} %>
        </tbody>
    </table>
</div>
<jsp:include page="layout/footer.jsp" />
</body>
</html>
