package cibertec.models;

public class Curso {
	private int id;
    private String nombre;
    private String descripcion;
    private String image;
    private Usuario usuario; // Usuario que crea el curso
    private Profesor profesor; // Profesor asociado al curso

    // Constructor, getters y setters
    public Curso() {}

    public Curso(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
}