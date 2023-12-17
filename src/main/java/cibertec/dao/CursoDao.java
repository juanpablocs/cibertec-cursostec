package cibertec.dao;

import java.util.*;

import cibertec.models.Curso;

public interface CursoDao {
    
	public List<Curso> obtenerCursos();
	public boolean agregarCurso(Curso curso, String userId);
	public boolean eliminarCurso(int id, String userId);
	public boolean actualizarCurso(Curso curso, String userId);
}