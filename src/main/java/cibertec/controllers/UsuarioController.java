package cibertec.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import cibertec.dao.UsuarioDaoImpl;
import cibertec.dto.UsuarioDto;
import cibertec.models.Rol;
import cibertec.models.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/usuarios")
public class UsuarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper;
	private UsuarioDaoImpl userDao;

    public void init() {
        userDao = new UsuarioDaoImpl();
        objectMapper = new ObjectMapper(); 
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("usuarios", userDao.getUsuarios());
        String user = (String) request.getSession().getAttribute("email");
    	System.out.println(user);
    	if(user == null) {
    		response.sendRedirect(request.getContextPath()+"/");
    		return;
    	}
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/usuarios.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO: fix session
    	//    	String userId = (String) request.getSession().getAttribute("id");
    	String userId = "1";
        UsuarioDto profeDto = objectMapper.readValue(request.getInputStream(), UsuarioDto.class);
        Map<String, Object> respuesta = new HashMap<>();
        
        boolean executed = false;
        Usuario data = new Usuario();

        switch(profeDto.getAction()) {
        case "delete":
        	executed = userDao.eliminar(profeDto.getItem().getId(), userId);
        	break;
        case "edit":
        	Usuario updCurso = new Usuario();
        	Usuario user = profeDto.getItem();
        	updCurso.setId(user.getId());
        	updCurso.setNombre(user.getNombre());
        	updCurso.setEmail(user.getEmail());
        	updCurso.setPassword(user.getPassword());
        	Rol rol = new Rol();
        	rol.setId(user.getRol().getId());
        	updCurso.setRol(rol);
        	executed = userDao.actualizar(updCurso, userId);
            data.setId(updCurso.getId());
        	break;
        case "add":
        	Usuario newCurso = new Usuario();
        	Usuario user1 = profeDto.getItem();
        	newCurso.setNombre(user1.getNombre());
        	newCurso.setEmail(user1.getEmail());
        	newCurso.setPassword(user1.getPassword());
        	Rol rol1 = new Rol();
        	rol1.setId(user1.getRol().getId());
        	newCurso.setRol(rol1);
        	System.out.println(newCurso);
        	executed = userDao.agregar(newCurso, userId);
        	data.setNombre(newCurso.getNombre());
        	break;
        }
        
        respuesta.put("error", !executed);
        respuesta.put("data", executed? data : null);
        
        // Prepara la respuesta en formato JSON
        String jsonRespuesta = objectMapper.writeValueAsString(respuesta); // O algún otro objeto de respuesta
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonRespuesta);
    } 
}