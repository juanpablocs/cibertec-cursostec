package cibertec.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import cibertec.dao.ProfesorDaoImpl;
import cibertec.dto.ProfesorDto;
import cibertec.models.Profesor;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/profesores")
public class ProfesorController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper;
	private ProfesorDaoImpl profeDao;

    public void init() {
        profeDao = new ProfesorDaoImpl();
        objectMapper = new ObjectMapper(); 
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("profesores", profeDao.getProfesores());
        String user = (String) request.getSession().getAttribute("email");
    	System.out.println(user);
    	if(user == null) {
    		response.sendRedirect(request.getContextPath()+"/");
    		return;
    	}
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/profesores.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO: fix session
    	//    	String userId = (String) request.getSession().getAttribute("id");
    	String userId = "1";
        ProfesorDto profeDto = objectMapper.readValue(request.getInputStream(), ProfesorDto.class);
        Map<String, Object> respuesta = new HashMap<>();
        
        boolean executed = false;
        Profesor data = new Profesor();

        switch(profeDto.getAction()) {
        case "delete":
        	executed = profeDao.eliminar(profeDto.getItem().getId(), userId);
        	break;
        case "edit":
        	Profesor updCurso = new Profesor();
        	Profesor curso = profeDto.getItem();
        	updCurso.setId(curso.getId());
        	updCurso.setNombre(curso.getNombre());
        	updCurso.setEspecialidad(curso.getEspecialidad());
        	executed = profeDao.actualizar(updCurso, userId);
            data.setId(updCurso.getId());
        	break;
        case "add":
        	Profesor newCurso = new Profesor();
        	Profesor curso1 = profeDto.getItem();
        	newCurso.setNombre(curso1.getNombre());
        	newCurso.setEspecialidad(curso1.getEspecialidad());
        	System.out.println(newCurso);
        	executed = profeDao.agregar(newCurso, userId);
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