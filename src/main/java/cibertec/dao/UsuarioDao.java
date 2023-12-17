package cibertec.dao;

import java.util.List;

import cibertec.models.Usuario;

public interface UsuarioDao {

    public Usuario validarUsuario(String username, String password);
    public List<Usuario> getUsuarios();
    public boolean agregar(Usuario user, String userId);
	public boolean eliminar(int id, String userId);
	public boolean actualizar(Usuario user, String userId);
}