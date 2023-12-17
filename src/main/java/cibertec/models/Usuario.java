package cibertec.models;

import java.util.List;

public class Usuario {
	private int id;
    private String nombre;
    private String email;
    private String password;
    private Rol rol; // Relación con Rol
    private List<Curso> cursos; // Lista de cursos creados por el usuario

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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
