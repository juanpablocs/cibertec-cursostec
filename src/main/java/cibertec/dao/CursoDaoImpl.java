package cibertec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cibertec.db.DBConnection;
import cibertec.models.Curso;
import cibertec.models.Profesor;

public class CursoDaoImpl implements CursoDao {
	
	Connection connection = null;
	
    public CursoDaoImpl() {
    	this.connection = DBConnection.getConnection();
	}

	public List<Curso> obtenerCursos() {
    	List<Curso> list = new ArrayList<Curso>();
    	Curso curso;
    	try {
    		String sql = "SELECT curso.*,profesor.nombre as profesor_name FROM curso inner join profesor on curso.profesor_id=profesor.id order by id desc";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				curso = new Curso();
				curso.setId(resultSet.getInt("id"));
				curso.setNombre(resultSet.getString("nombre"));
				curso.setDescripcion(resultSet.getString("descripcion"));
				curso.setImage(resultSet.getString("image"));
				Profesor profe = new Profesor();
				profe.setId(resultSet.getInt("profesor_id"));
				profe.setNombre(resultSet.getString("profesor_name"));
				curso.setProfesor(profe);
				list.add(curso);
			}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
        return list;
    }

	public boolean agregarCurso(Curso curso, String userId) {
	    boolean flag = false;
	    
	    try {
	        String sql = "INSERT INTO curso (nombre, descripcion, image, profesor_id, usuario_id) VALUES (?, ?, ?, ?, ?)";

	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, curso.getNombre());
	            statement.setString(2, curso.getDescripcion());
	            statement.setString(3, curso.getImage());
	            statement.setInt(4, curso.getProfesorId());
	            statement.setInt(5, 1);

	            int filasAfectadas = statement.executeUpdate();
	            flag = filasAfectadas > 0;
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return flag;
	}

	@Override
	public boolean eliminarCurso(int id, String userId) {
		boolean flag = false;
		try {
	        String sql = "DELETE FROM curso WHERE id = ?";
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
	public boolean actualizarCurso(Curso curso, String userId) {
		boolean flag = false;
	    try {
	        String sql = "UPDATE curso SET nombre = ?, descripcion = ?, image = ?, profesor_id = ? WHERE id = ?";

	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            // Configurar los parámetros para la sentencia SQL
	            statement.setString(1, curso.getNombre());
	            statement.setString(2, curso.getDescripcion());
	            statement.setString(3, curso.getImage());
	            statement.setInt(4, curso.getProfesorId()); // Asume que curso tiene un método getProfesorId
	            statement.setInt(5, curso.getId());
	   
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
    
}
