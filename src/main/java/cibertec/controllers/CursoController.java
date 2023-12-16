package cibertec.controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cibertec.dao.CursoDao;
import cibertec.dao.CursoDaoImpl;
import cibertec.models.Curso;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/curso")
public class CursoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private CursoDao cursoDao;
	private ObjectMapper objectMapper;

    public void init() {
        cursoDao = new CursoDaoImpl();
        objectMapper = new ObjectMapper(); 
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cursos", cursoDao.obtenerCursos());
        RequestDispatcher dispatcher = request.getRequestDispatcher("curso.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lee el JSON del cuerpo de la solicitud
        Curso curso = objectMapper.readValue(request.getInputStream(), Curso.class);
        Map<String, Object> respuesta = new HashMap<>();
        
        // Guarda el curso en la base de datos
        boolean guardo = cursoDao.agregarCurso(curso);
        
        respuesta.put("error", !guardo);
        respuesta.put("data", guardo? curso : null);
        
        // Prepara la respuesta en formato JSON
        String jsonRespuesta = objectMapper.writeValueAsString(respuesta); // O algún otro objeto de respuesta
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonRespuesta);
    } 
}