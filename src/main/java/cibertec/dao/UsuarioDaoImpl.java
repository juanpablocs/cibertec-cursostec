package cibertec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cibertec.db.DBConnection;
import cibertec.models.Rol;
import cibertec.models.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

	Connection connection = null;
	
	public UsuarioDaoImpl() {
		this.connection = DBConnection.getConnection();
	}

	public Usuario validarUsuario(String username, String password) {
	    // Ajusta la consulta para incluir información del rol
	    String sql = "SELECT usuario.*, roles.nombre as rol_nombre FROM usuario INNER JOIN roles ON usuario.rol_id = roles.id WHERE usuario.email = ? AND usuario.password = ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, username);
	        statement.setString(2, password);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                Usuario usuario = new Usuario();
	                usuario.setId(resultSet.getInt("id"));
	                usuario.setNombre(resultSet.getString("nombre"));
	                usuario.setEmail(resultSet.getString("email"));

	                // Crear y configurar el objeto Rol
	                Rol rol = new Rol();
	                rol.setId(resultSet.getInt("rol_id"));
	                rol.setNombre(resultSet.getString("rol_nombre"));
	                usuario.setRol(rol);

	                return usuario;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public boolean agregar(Usuario user, String userId) {
boolean flag = false;
	    
	    try {
	        String sql = "INSERT INTO usuario (nombre, email, password, rol_id) VALUES (?, ?, ?, ?)";

	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, user.getNombre());
	            statement.setString(2, user.getEmail());
	            statement.setString(3, user.getPassword());
	            statement.setInt(4, user.getRol().getId());

	            int filasAfectadas = statement.executeUpdate();
	            flag = filasAfectadas > 0;
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return flag;
	}

	@Override
	public boolean eliminar(int id, String userId) {
		boolean flag = false;
		try {
	        String sql = "DELETE FROM usuario WHERE id = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            // Establecer el ID en la sentencia preparada
	            statement.setInt(1, id);
	            // Ejecutar la sentencia
	            int filasAfectadas = statement.executeUpdate();
	            // Si filasAfectadas es mayor a 0, entonces la eliminación fue exitosa
	            flag = filasAfectadas > 0;
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		return flag;
	}

	@Override
	public boolean actualizar(Usuario user, String userId) {
		boolean flag = false;
	    try {
	        String sql = "UPDATE usuario SET nombre = ?, email = ? WHERE id = ?";

	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            // Configurar los parámetros para la sentencia SQL
	            statement.setString(1, user.getNombre());
	            statement.setString(2, user.getEmail()); // Asume que curso tiene un método getProfesorId
	            statement.setInt(3, user.getId());
	   
	            // Ejecutar la sentencia
	            int filasAfectadas = statement.executeUpdate();

	            // Si filasAfectadas es mayor a 0, entonces la actualización fue exitosa
	            flag = filasAfectadas > 0;
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return flag;
	}

	@Override
	public List<Usuario> getUsuarios() {
		List<Usuario> list = new ArrayList<Usuario>();
    	Usuario user;
    	try {
    		String sql = "SELECT usuario.*, roles.nombre as rol_nombre FROM usuario INNER JOIN roles ON usuario.rol_id = roles.id order by id desc";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				user = new Usuario();
				user.setId(resultSet.getInt("id"));
				user.setNombre(resultSet.getString("nombre"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				Rol rol = new Rol();
                rol.setId(resultSet.getInt("rol_id"));
                rol.setNombre(resultSet.getString("rol_nombre"));
                user.setRol(rol);
				list.add(user);
			}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
        return list;
	}

}
