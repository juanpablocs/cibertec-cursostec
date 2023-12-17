package cibertec.dto;

import cibertec.models.Curso;

public class CursoDto {
    private String action;
    private Curso curso; // Objeto Curso anidado

    // Constructor vacío
    public CursoDto() {
    }

    // Constructor con todos los campos
    public CursoDto(String action, Curso curso) {
        this.action = action;
        this.curso = curso;
    }

    // Getters y setters
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    // Método toString(), si es necesario
}

