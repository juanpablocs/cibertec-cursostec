package cibertec.dao;

public class UsuarioDao {

    public boolean validarUsuario(String username, String password) {
        // En un caso real, aquí consultarías a una base de datos
        // Por simplicidad, usamos valores estáticos
        return "admin".equals(username) && "password".equals(password);
    }
}