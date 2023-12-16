package cibertec.models;

import java.util.List;

public class Profesor {
	private int id;
    private String nombre;
    private String especialidad;
    private List<Curso> cursos; // Lista de cursos impartidos por el profesor

    // getters y setters
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
}
