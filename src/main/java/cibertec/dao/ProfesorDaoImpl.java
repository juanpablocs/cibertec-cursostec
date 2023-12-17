package cibertec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cibertec.db.DBConnection;
import cibertec.models.Profesor;

public class ProfesorDaoImpl implements ProfesorDao {

	
	private Connection connection;

	public ProfesorDaoImpl() {
		this.connection = DBConnection.getConnection();
	}

	@Override
	public List<Profesor> getProfesores() {
		List<Profesor> list = new ArrayList<Profesor>();
    	Profesor profe;
    	try {
    		String sql = "SELECT * FROM profesor";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				profe = new Profesor();
				profe.setId(resultSet.getInt("id"));
				profe.setNombre(resultSet.getString("nombre"));
				profe.setEspecialidad(resultSet.getString("especialidad"));
				list.add(profe);
			}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
        return list;
	}

	@Override
	public boolean agregar(Profesor profe, String userId) {
		boolean flag = false;
	    
	    try {
	        String sql = "INSERT INTO profesor (nombre, especialidad) VALUES (?, ?)";

	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, profe.getNombre());
	            statement.setString(2, profe.getEspecialidad());

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
	        String sql = "DELETE FROM profesor WHERE id = ?";
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
	public boolean actualizar(Profesor profe, String userId) {
		boolean flag = false;
	    try {
	        String sql = "UPDATE profesor SET nombre = ?, especialidad = ? WHERE id = ?";

	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            // Configurar los parámetros para la sentencia SQL
	            statement.setString(1, profe.getNombre());
	            statement.setString(2, profe.getEspecialidad()); // Asume que curso tiene un método getProfesorId
	            statement.setInt(3, profe.getId());
	   
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
