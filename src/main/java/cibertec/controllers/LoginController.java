package cibertec.controllers;

import java.io.IOException;

import cibertec.dao.UsuarioDao;
import cibertec.dao.UsuarioDaoImpl;
import cibertec.models.Usuario;
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
        usuarioDao = new UsuarioDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("username");
        String password = request.getParameter("password");

        Usuario usuario = usuarioDao.validarUsuario(email, password);

        if (usuario != null) {
            // Login exitoso
            request.getSession().setAttribute("email", usuario.getEmail());
            request.getSession().setAttribute("id", usuario.getId());
            request.getSession().setAttribute("nombre", usuario.getNombre());
            request.getSession().setAttribute("rol", usuario.getRol().getNombre());
            response.sendRedirect(request.getContextPath()+"/"); // Redireccionar a la página de inicio
        } else {
            // Login fallido
            request.setAttribute("error", "Usuario o contraseña inválidos");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
