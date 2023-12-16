<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
    <title>CursosTec - Login</title>
    <jsp:include page="layout/meta.jsp" />
</head>
<body>
<jsp:include page="layout/header.jsp" />
<div class="container py-4" style="min-height:600px">
    <h2>Login</h2>
    <form action="login" method="post">
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
        <% } %>
        <div class="form-group">
            <label for="username">Usuario:</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Contraseña:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary">Ingresar</button>
    </form>
</div>

<jsp:include page="layout/footer.jsp" />
</body>
</html>
