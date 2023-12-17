<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List" 
%>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="java.util.List" %>
<%@ page import="cibertec.models.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <title>CursosTec Usuarios</title>
    <jsp:include page="layout/meta.jsp" />
</head>
<body>
<jsp:include page="layout/header.jsp" />
<div class="container py-4">
    <h1>Mantenimiento de usuarios</h1>
    <p>Esta pagina tiene acceso restringido.</p>
</div>

<div class="container" style="min-height:600px">
	 <div class="p-4">
	 	<div class="d-flex justify-content-between">
	        <h2>Listado de usuarios</h2>
	        <div class="d-flex justify-content-end">
	          <button
	            class="btn btn-primary mb-3"
	            data-toggle="modal"
	            data-target="#addModal"
	          >
	            Agregar Profesor
	          </button>
	        </div>
		</div>
		<% 
	        List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
	        if(usuarios == null) {
	        	%>
	        	<h2>No hay usuarios registrados</h2>
	        <% 	} else { %>
	        	<table class="table">
	          <thead>
	            <tr>
	              <th>ID</th>
	              <th>Nombre</th>
	              <th>Email</th>
	              <th>Rol</th>
	              <th>Acciones</th>
	            </tr>
	          </thead>
	          <tbody id="cursos-list">
		       	<% 
		        for (Usuario user : usuarios) { 
	        	%>
	        		<tr>
		            	<td><%= user.getId() %></td>
		            	<td><%= user.getNombre() %></td>
		            	<td><%= user.getEmail() %></td>
		            	<td><%= user.getRol().getNombre() %></td>
		            	<td>
		            		<button 
		            			class="btn btn-warning btn-sm btn-edit" 
		            			data-toggle="modal" 
		            			data-target="#editModal" 
		            			data-id="<%= user.getId() %>"
		            			data-name="<%= user.getNombre() %>"
		            			data-email="<%= user.getEmail() %>"
		            			data-password="<%= user.getPassword() %>"
		            			data-rol="<%= user.getRol().getId() %>"
		            		>
		            			Editar
		            		</button> 
		            		<button 
		            			class="btn btn-danger btn-sm btn-delete" 
		            			data-id="<%= user.getId() %>"
		            		>
		            			Eliminar
		            		</button>
		            	</td>
		            </tr>
	       		<% } %>
        	</tbody>
	        </table>
         <% }%>
	 	
      </div>
</div>


<!-- Modal para agregar curso -->
    <div
      class="modal fade"
      id="addModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="addModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="addModalLabel">Agregar Usuario</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <!-- Formulario para agregar curso -->
            <form id="addForm">
              <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" class="form-control" id="nombre" required />
              </div>
              <div class="form-group">
                <label for="descripcion">Email:</label>
                <input type="text" class="form-control" id="email" required />
              </div>
              <div class="form-group">
                <label for="descripcion">Password:</label>
                <input type="password" class="form-control" id="password" required />
              </div>
              <div class="form-group">
                <label for="profesor">Rol:</label>
                <!-- Select para elegir el profesor -->
                <select class="form-control" id="rol" required>
                  <option value="1">Admin</option>
				  <option value="2">Invitado</option>      
                </select>
              </div>
              
              <button type="submit" class="btn btn-primary">Agregar</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    

<!-- Modal para editar curso -->
    <div
      class="modal fade"
      id="editModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="editModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="editModalLabel">Editar Usuario</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <!-- Formulario para editar curso -->
            <form id="editForm">
              <input type="hidden" id="editId" />
              <div class="form-group">
                <label for="editNombre">Nombre:</label>
                <input
                  type="text"
                  class="form-control"
                  id="editNombre"
                  required
                />
              </div>
              <div class="form-group">
                <label for="editEmail">Email:</label>
                <input
                  type="text"
                  class="form-control"
                  id="editEmail"
                  required
                />
              </div>
              <div class="form-group">
                <label for="editDescripcion">Password:</label>
                <input
                  type="password"
                  class="form-control"
                  id="editPassword"
                  required
                />
              </div>
              <div class="form-group">
                <label for="profesor">Rol:</label>
                <!-- Select para elegir el profesor -->
                <select class="form-control" id="editRol" required>
                  <option value="1">Admin</option>
				  <option value="2">Invitado</option>      
                </select>
              </div>
              
              <button type="submit" class="btn btn-primary">
                Guardar Cambios
              </button>
            </form>
          </div>
        </div>
      </div>
      </div>

<jsp:include page="layout/footer.jsp" />
<script>
$(document).ready(function () {
    // Agregar curso
    $("#addForm").submit(function (e) {
      e.preventDefault();
      var nombre = $("#nombre").val();
      var email = $("#email").val();
      var password = $("#password").val();
      var rol = $("#rol").val();
      $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/usuarios", // Coloca tu URL aquí
        contentType: "application/json",
        data: JSON.stringify({
            action: "add",
            item: {
	            nombre: nombre,
	            email: email,
	            password: password,
	            rol: {id:rol}
            }
          }),
        success: function () {
          $("#addModal").modal("hide");
          //window.location.reload();
        },
      });
    });

    // Manejar clic en botones de editar
    $(document).on("click", ".btn-edit", function () {
      // Obtener el ID del curso desde el atributo data-id del botón
      var cursoId = $(this).data("id");
	  var cursoName = $(this).data("name");
	  var cursoEmail = $(this).data("email");
	  var password = $(this).data("password");
	  var rolId = $(this).data("rol");
      // Llenar el formulario de edición con los datos del curso
      $("#editId").val(cursoId);
      $("#editNombre").val(cursoName);
      $("#editEmail").val(cursoEmail);
      $("#editPassword").val(password);
      $("#editRol").val(rolId);

      // Mostrar el modal de edición
      $("#editModal").modal("show");
    });

    // Editar curso
    $("#editForm").submit(function (e) {
      e.preventDefault();
      var id = Number($("#editId").val());
      var nombre = $("#editNombre").val();
      var email = $("#editEmail").val();
      var password = $("#editPassword").val();
      var rol = $("#editRol").val();
      $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/usuarios", // Coloca tu URL aquí
        contentType: "application/json",
        data: JSON.stringify({
            action: "edit",
            item: {
	            id: id,
	            nombre: nombre,
	            email: email,
	            password: password,
	            rol: {id:rol}
            }
          }),
        success: function () {
          $("#editModal").modal("hide");
          //location.reload();
        },
      });
    });

    // Eliminar curso
    $(document).on("click", ".btn-delete", function () {
      var id = $(this).data("id");
      if (confirm("¿Estás seguro de eliminar este curso?")) {
        $.ajax({
          type: "POST",
          url: "${pageContext.request.contextPath}/usuarios", // Coloca tu URL aquí
          contentType: "application/json",
          data: JSON.stringify({ action: "delete", item:{id: id} }),
          success: function () {
            location.reload();
          },
        });
      }
    });


  });
</script>
</body>
</html>