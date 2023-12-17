<% 
	String user = (String) session.getAttribute("email");
	String name = (String) session.getAttribute("nombre");
%>
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container d-flex justify-content-between">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">
			<img src="https://i.imgur.com/icha0zJ.png" alt="" width="100" />
		</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="" id="navbarNav">
            <ul class="navbar-nav d-flex align-items-center">
                <%
                if (user != null && !user.isEmpty()) {
                %>
                    <li class="nav-item">
                    	<a class=" d-flex align-items-center" href="${pageContext.request.contextPath}/cuenta">                    	
	                        <img src="https://static.vecteezy.com/system/resources/thumbnails/019/879/186/small/user-icon-on-transparent-background-free-png.png" style="width: 50px;">
	                       	<span style="font-weight:bold;color:white;margin-right:20px;margin-left:-5px;text-transform:Capitalize"><%=name %></span>
                    	</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/logout" style="color:white">Cerrar Sesion</a> <!-- Agregar ruta de logout -->
                    </li>
                <% } else {
                %>
                    <li class="nav-item">
                        <a class="nav-link btn btn-primary" href="login" style="color:white">Iniciar Sesion</a> <!-- Cambiar por la ruta correcta a la p�gina de login -->
                    </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
