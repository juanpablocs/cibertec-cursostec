<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/"><strong>CursosTec</strong></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Inicio <span class="sr-only">(current)</span></a>
                </li>
                <!-- Otros elementos del men� aqu� -->
            </ul>
            <ul class="navbar-nav d-flex align-items-center">
                <% 
                String user = (String) session.getAttribute("user");
                if (user != null && !user.isEmpty()) {
                %>
                    <li class="nav-item">
                    	<a href="${pageContext.request.contextPath}/cuenta">                    	
	                        <img src="https://static.vecteezy.com/system/resources/thumbnails/019/879/186/small/user-icon-on-transparent-background-free-png.png" style="width: 50px;">
                    	</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/logout">Cerrar Sesi�n</a> <!-- Agregar ruta de logout -->
                    </li>
                <% } else {
                %>
                    <li class="nav-item">
                        <a class="nav-link btn btn-primary" href="login" style="color:white">Iniciar Sesi�n</a> <!-- Cambiar por la ruta correcta a la p�gina de login -->
                    </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
