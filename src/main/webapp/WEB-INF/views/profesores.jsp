<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List" 
%>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="java.util.List" %>
<%@ page import="cibertec.models.Profesor" %>
<!DOCTYPE html>
<html>
<head>
    <title>CursosTec Profesores</title>
    <jsp:include page="layout/meta.jsp" />
</head>
<body>
<jsp:include page="layout/header.jsp" />
<div class="container py-4">
    <h1>Mantenimiento de profesores</h1>
    <p>Esta pagina tiene acceso restringido.</p>
</div>

<div class="container" style="min-height:600px">
	 <div class="p-4">
	 	<div class="d-flex justify-content-between">
	        <h2>Listado de profesores</h2>
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
	        List<Profesor> profesores = (List<Profesor>) request.getAttribute("profesores");
	        if(profesores == null) {
	        	%>
	        	<h2>No hay profesores registrados</h2>
	        <% 	} else { %>
	        	<table class="table">
	          <thead>
	            <tr>
	              <th>ID</th>
	              <th>Nombre</th>
	              <th>Especialidad</th>
	              <th>Acciones</th>
	            </tr>
	          </thead>
	          <tbody id="cursos-list">
		       	<% 
		        for (Profesor profe : profesores) { 
	        	%>
	        		<tr>
		            	<td><%= profe.getId() %></td>
		            	<td><%= profe.getNombre() %></td>
		            	<td><%= profe.getEspecialidad() %></td>
		            	<td>
		            		<button 
		            			class="btn btn-warning btn-sm btn-edit" 
		            			data-toggle="modal" 
		            			data-target="#editModal" 
		            			data-id="<%= profe.getId() %>"
		            			data-name="<%= profe.getNombre() %>"
		            			data-description="<%= profe.getEspecialidad() %>"
		            		>
		            			Editar
		            		</button> 
		            		<button 
		            			class="btn btn-danger btn-sm btn-delete" 
		            			data-id="<%= profe.getId() %>"
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
            <h5 class="modal-title" id="addModalLabel">Agregar Profesor</h5>
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
                <label for="descripcion">Especialidad:</label>
                <textarea
                  class="form-control"
                  id="descripcion"
                  rows="3"
                  required
                ></textarea>
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
            <h5 class="modal-title" id="editModalLabel">Editar Profesor</h5>
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
                <label for="editDescripcion">Especialidad:</label>
                <textarea
                  class="form-control"
                  id="editDescripcion"
                  rows="3"
                  required
                ></textarea>
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
      var descripcion = $("#descripcion").val();
      $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/profesores", // Coloca tu URL aquí
        contentType: "application/json",
        data: JSON.stringify({
            action: "add",
            item: {
	            nombre: nombre,
	            especialidad: descripcion,
            }
          }),
        success: function () {
          $("#addModal").modal("hide");
          window.location.reload();
        },
      });
    });

    // Manejar clic en botones de editar
    $(document).on("click", ".btn-edit", function () {
      // Obtener el ID del curso desde el atributo data-id del botón
      var cursoId = $(this).data("id");
	  var cursoName = $(this).data("name");
	  var cursoDescription = $(this).data("description");
      // Llenar el formulario de edición con los datos del curso
      $("#editId").val(cursoId);
      $("#editNombre").val(cursoName);
      $("#editDescripcion").val(cursoDescription);

      // Mostrar el modal de edición
      $("#editModal").modal("show");
    });

    // Editar curso
    $("#editForm").submit(function (e) {
      e.preventDefault();
      var id = Number($("#editId").val());
      var nombre = $("#editNombre").val();
      var descripcion = $("#editDescripcion").val();
      $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/profesores", // Coloca tu URL aquí
        contentType: "application/json",
        data: JSON.stringify({
            action: "edit",
            item: {
	            id: id,
	            nombre: nombre,
	            especialidad: descripcion,
            }
          }),
        success: function () {
          $("#editModal").modal("hide");
          location.reload();
        },
      });
    });

    // Eliminar curso
    $(document).on("click", ".btn-delete", function () {
      var id = $(this).data("id");
      if (confirm("¿Estás seguro de eliminar este curso?")) {
        $.ajax({
          type: "POST",
          url: "${pageContext.request.contextPath}/profesores", // Coloca tu URL aquí
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