package cibertec.controllers;

import java.io.IOException;

import cibertec.dao.CursoDao;
import cibertec.dao.CursoDaoImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "IndexController", urlPatterns = {"/", "/home"})
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CursoDao cursoDao;

	@Override
    public void init() {
		System.out.println("IndexController iniciado");
        cursoDao = new CursoDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = requestURI.substring(contextPath.length());

        System.out.println("requestURI: " + requestURI);

        if (!path.equals("/") && !path.equals("")) { // Verificar si la ruta no es la raíz
             System.out.println("Redirigiendo a la raíz");
             response.sendRedirect(contextPath + "/");
             return;
        }
        request.setAttribute("cursos", cursoDao.obtenerCursos());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
        dispatcher.forward(request, response);
    }
}
