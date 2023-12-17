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
	
	public void setProfesorId(int id) {
		if (this.profesor == null) {
	        this.profesor = new Profesor();
	    }
	    this.profesor.setId(id);
	}
	public int getProfesorId() {
		if(this.profesor == null) {
			return 0;
		}
		return profesor.getId();
	}

	public void setUsuarioId(int id) {
		if (this.usuario == null) {
	        this.usuario = new Usuario();
	    }
	    this.usuario.setId(id);
	}
	public int getUsuarioId() {
		if(this.usuario == null) {
			return 0;
		}
		return usuario.getId();
	}
	
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}