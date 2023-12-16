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

public class CursoDaoImpl implements CursoDao {
	
	Connection connection = null;
	ResultSet resultSet = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	
    public CursoDaoImpl() {
    	this.connection = DBConnection.getConnection();
	}

	public List<Curso> obtenerCursos() {
    	List<Curso> list = new ArrayList<Curso>();
    	Curso curso;
    	try {
    		String sql = "SELECT * FROM curso";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				curso = new Curso();
				curso.setId(resultSet.getInt("id"));
				curso.setNombre(resultSet.getString("nombre"));
				curso.setDescripcion(resultSet.getString("descripcion"));
				list.add(curso);
			}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
        return list;
    }

    public boolean agregarCurso(Curso curso) {
    	boolean flag = false;
		try {
			String sql = "INSERT INTO curso(nombre, descripcion)VALUES"
					+ "('"+curso.getNombre()+"', '"+curso.getDescripcion()+"')";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
    }
    
}
