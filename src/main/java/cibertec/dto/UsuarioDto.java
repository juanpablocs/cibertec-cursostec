package cibertec.dto;

import cibertec.models.Usuario;

public class UsuarioDto {

	  private String action;
	   private Usuario item;
	   
	// Constructor vacío
	    public UsuarioDto() {
	    }
	    
	   public UsuarioDto(String action, Usuario profesor) {
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

	    public Usuario getItem() {
	        return item;
	    }

	    public void setItem(Usuario curso) {
	        this.item = curso;
	    }

}

