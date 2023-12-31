package cibertec.controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cibertec.dao.CursoDao;
import cibertec.dao.CursoDaoImpl;
import cibertec.dao.ProfesorDaoImpl;
import cibertec.dto.CursoDto;
import cibertec.models.Curso;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/cursos")
public class CursoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private CursoDao cursoDao;
	private ObjectMapper objectMapper;
	private ProfesorDaoImpl profeDao;

    public void init() {
        cursoDao = new CursoDaoImpl();
        profeDao = new ProfesorDaoImpl();
        objectMapper = new ObjectMapper(); 
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cursos", cursoDao.obtenerCursos());
        request.setAttribute("profesores", profeDao.getProfesores());
        String user = (String) request.getSession().getAttribute("email");
    	System.out.println(user);
    	if(user == null) {
    		response.sendRedirect(request.getContextPath()+"/");
    		return;
    	}
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cursos.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO: fix session
    	//    	String userId = (String) request.getSession().getAttribute("id");
    	String userId = "1";
        CursoDto cursoDto = objectMapper.readValue(request.getInputStream(), CursoDto.class);
        Map<String, Object> respuesta = new HashMap<>();
        
        boolean executed = false;
        Curso data = new Curso();

        switch(cursoDto.getAction()) {
        case "delete":
        	executed = cursoDao.eliminarCurso(cursoDto.getCurso().getId(), userId);
        	break;
        case "edit":
        	Curso updCurso = new Curso();
        	Curso curso = cursoDto.getCurso();
        	updCurso.setId(curso.getId());
        	updCurso.setNombre(curso.getNombre());
        	updCurso.setDescripcion(curso.getDescripcion());
        	updCurso.setImage(curso.getImage());
        	updCurso.setProfesorId(curso.getProfesor().getId());
        	updCurso.setUsuarioId(1);
        	executed = cursoDao.actualizarCurso(updCurso, userId);
            data.setId(updCurso.getId());
        	break;
        case "add":
        	Curso newCurso = new Curso();
        	Curso curso1 = cursoDto.getCurso();
        	newCurso.setNombre(curso1.getNombre());
        	newCurso.setDescripcion(curso1.getDescripcion());
        	newCurso.setImage(curso1.getImage());
        	newCurso.setProfesorId(curso1.getProfesorId());
        	newCurso.setUsuarioId(1);
        	System.out.println(newCurso);
        	executed = cursoDao.agregarCurso(newCurso, userId);
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