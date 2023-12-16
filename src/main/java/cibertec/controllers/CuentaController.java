package cibertec.controllers;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cuenta")
public class CuentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String user = (String) request.getSession().getAttribute("user");
    	System.out.println(user);
    	if(user == null) {
    		response.sendRedirect(request.getContextPath()+"/");
    		return;
    	}
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cuenta.jsp");
        dispatcher.forward(request, response);
    }
}
