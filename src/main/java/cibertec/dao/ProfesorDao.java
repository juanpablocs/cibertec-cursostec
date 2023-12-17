package cibertec.dao;

import java.util.List;

import cibertec.models.Profesor;

public interface ProfesorDao {
	public List<Profesor> getProfesores();
	public boolean agregar(Profesor profe, String userId);
	public boolean eliminar(int id, String userId);
	public boolean actualizar(Profesor profe, String userId);
}
