<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List" 
    import="cibertec.models.Curso"
%>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="java.util.List" %>
<%@ page import="cibertec.models.Profesor" %>
<!DOCTYPE html>
<html>
<head>
    <title>CursosTec Cursos</title>
    <jsp:include page="layout/meta.jsp" />
</head>
<body>
<jsp:include page="layout/header.jsp" />
<div class="container py-4">
    <h1>Mantenimiento de cursos</h1>
    <p>Esta pagina tiene acceso restringido.</p>
</div>

<div class="container" style="min-height:600px">
	 <div class="p-4">
	 	<div class="d-flex justify-content-between">
	        <h2>Listado de cursos</h2>
	        <div class="d-flex justify-content-end">
	          <button
	            class="btn btn-primary mb-3"
	            data-toggle="modal"
	            data-target="#addModal"
	          >
	            Agregar Curso
	          </button>
	        </div>
		</div>
		<% 
	        List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
	        if(cursos == null) {
	        	%>
	        	<h2>No hay cursos registrados</h2>
	        <% 	} else { %>
	        	<table class="table">
	          <thead>
	            <tr>
	              <th>ID</th>
	              <th>Nombre</th>
	              <th>Descripción</th>
	              <th>Imagen</th>
	              <th>Profesor Asignado</th>
	              <th>Acciones</th>
	            </tr>
	          </thead>
	          <tbody id="cursos-list">
		       	<% 
		        for (Curso curso : cursos) { 
	        	%>
	        		<tr>
		            	<td><%= curso.getId() %></td>
		            	<td><%= curso.getNombre() %></td>
		            	<td><%= curso.getDescripcion() %></td>
		            	<td><img src="<%= curso.getImage() != null ? curso.getImage() : "https://via.placeholder.com/150" %>" alt="Imagen del curso" style="max-width: 50px; max-height: 50px;"></td>
		            	<td><%= curso.getProfesor().getNombre() %></td>
		            	<td>
		            		<button 
		            			class="btn btn-warning btn-sm btn-edit" 
		            			data-toggle="modal" 
		            			data-target="#editModal" 
		            			data-id="<%= curso.getId() %>"
		            			data-name="<%= curso.getNombre() %>"
		            			data-description="<%= curso.getDescripcion() %>"
		            			data-image="<%= curso.getImage() %>"
		            			data-teacher_id="<%= curso.getProfesorId() %>"
		            		>
		            			Editar
		            		</button> 
		            		<button 
		            			class="btn btn-danger btn-sm btn-delete" 
		            			data-id="<%= curso.getId() %>"
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

<%
    List<Profesor> profesores = (List<Profesor>) request.getAttribute("profesores");
    ObjectMapper mapper = new ObjectMapper();
    String profesoresJson = mapper.writeValueAsString(profesores);
%>

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
            <h5 class="modal-title" id="addModalLabel">Agregar Curso</h5>
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
                <label for="descripcion">Descripción:</label>
                <textarea
                  class="form-control"
                  id="descripcion"
                  rows="3"
                  required
                ></textarea>
              </div>
              <div class="form-group">
                <label for="imagen">Imagen URL:</label>
                <input type="url" class="form-control" id="imagen" required />
              </div>
              <div class="form-group">
                <label for="profesor">Profesor Asignado:</label>
                <!-- Select para elegir el profesor -->
                <select class="form-control" id="profesor" required>
                  <%
				        for (Profesor profesor : profesores) {
				            out.println("<option value='" + profesor.getId() + "'>" + profesor.getNombre() + "</option>");
				        }
				    %>
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
            <h5 class="modal-title" id="editModalLabel">Editar Curso</h5>
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
                <label for="editDescripcion">Descripción:</label>
                <textarea
                  class="form-control"
                  id="editDescripcion"
                  rows="3"
                  required
                ></textarea>
              </div>
              <div class="form-group">
                <label for="editImagen">Imagen URL:</label>
                <input
                  type="url"
                  class="form-control"
                  id="editImagen"
                  required
                />
              </div>
              <div class="form-group">
                <label for="editProfesor">Profesor Asignado:</label>
                <!-- Select para elegir el profesor -->
                <select class="form-control" id="editProfesor" required>
                  <%
				        for (Profesor profesor : profesores) {
				            out.println("<option value='" + profesor.getId() + "'>" + profesor.getNombre() + "</option>");
				        }
				    %>
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
var _teachers = <%= profesoresJson %>;
$(document).ready(function () {
    // Agregar curso
    $("#addForm").submit(function (e) {
      e.preventDefault();
      var nombre = $("#nombre").val();
      var descripcion = $("#descripcion").val();
      var imagen = $("#imagen").val();
      var profesor = $("#profesor").val();
      $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/cursos", // Coloca tu URL aquí
        contentType: "application/json",
        data: JSON.stringify({
            action: "add",
            curso: {
	            nombre: nombre,
	            descripcion: descripcion,
	            image: imagen,
	            profesor: {id: profesor},
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
	  var cursoImage = $(this).data("image");
	  var cursoTeacher = $(this).data("teacher_id");
      // Llenar el formulario de edición con los datos del curso
      $("#editId").val(cursoId);
      $("#editNombre").val(cursoName);
      $("#editDescripcion").val(cursoDescription);
      $("#editImagen").val(cursoImage);
      $("#editProfesor").val(cursoTeacher);

      // Mostrar el modal de edición
      $("#editModal").modal("show");
    });

    // Editar curso
    $("#editForm").submit(function (e) {
      e.preventDefault();
      var id = Number($("#editId").val());
      var nombre = $("#editNombre").val();
      var descripcion = $("#editDescripcion").val();
      var imagen = $("#editImagen").val();
      var profesor = Number($("#editProfesor").val());
      $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/cursos", // Coloca tu URL aquí
        contentType: "application/json",
        data: JSON.stringify({
            action: "edit",
            curso: {
	            id: id,
	            nombre: nombre,
	            descripcion: descripcion,
	            image: imagen,
	            profesor: {id: profesor},
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
          url: "${pageContext.request.contextPath}/cursos", // Coloca tu URL aquí
          contentType: "application/json",
          data: JSON.stringify({ action: "delete", curso:{id: id} }),
          success: function () {
            location.reload();
          },
        });
      }
    });

    // Función para obtener los datos de un curso por su ID (puedes reemplazarla con tu propia lógica)
    function getCursoById(id) {
      // Datos mock (puedes reemplazarlos con tus propios datos)
      var mockData = [
        {
          id: 1,
          nombre: "Curso 1",
          descripcion: "Descripción del Curso 1",
          imagen: "https://via.placeholder.com/150",
          profesor: "Profesor 1",
        },
        {
          id: 2,
          nombre: "Curso 2",
          descripcion: "Descripción del Curso 2",
          imagen: "https://via.placeholder.com/150",
          profesor: "Profesor 2",
        },
        {
          id: 3,
          nombre: "Curso 3",
          descripcion: "Descripción del Curso 3",
          imagen: "https://via.placeholder.com/150",
          profesor: "Profesor 3",
        },
      ];

      // Buscar el curso por ID
      var curso = mockData.find(function (c) {
        return c.id === id;
      });

      return curso || {}; // Devolver un objeto vacío si no se encuentra el curso
    }
  });
</script>
</body>
</html>