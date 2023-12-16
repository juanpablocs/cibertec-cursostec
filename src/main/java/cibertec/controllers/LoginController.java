package cibertec.controllers;

import java.io.IOException;

import cibertec.dao.UsuarioDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// otras importaciones

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private UsuarioDao usuarioDao;

    public void init() {
        usuarioDao = new UsuarioDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (usuarioDao.validarUsuario(username, password)) {
            // Login exitoso
            request.getSession().setAttribute("user", username);
            response.sendRedirect(request.getContextPath()+"/"); // Redireccionar a la página de inicio
        } else {
            // Login fallido
            request.setAttribute("error", "Usuario o contraseña inválidos");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
