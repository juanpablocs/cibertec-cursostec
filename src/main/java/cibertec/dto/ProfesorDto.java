package cibertec.dto;
import cibertec.models.Profesor;

public class ProfesorDto {

	  private String action;
	   private Profesor item;
	   
	// Constructor vacío
	    public ProfesorDto() {
	    }
	    
	   public ProfesorDto(String action, Profesor profesor) {
	        this.action = action;
	        this.item = profesor;
	    }

	    // Getters y setters
	    public String getAction() {
	        return action;
	    }

	    public void setAction(String action) {
	        this.action = action;
	    }

	    public Profesor getItem() {
	        return item;
	    }

	    public void setItem(Profesor curso) {
	        this.item = curso;
	    }

}

