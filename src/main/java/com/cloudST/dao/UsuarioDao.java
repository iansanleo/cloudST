package com.cloudST.dao;

import java.util.List;

import com.cloudST.model.Usuario;

public interface UsuarioDao {
	public Usuario findById(int idUsuario);
	public void saveUsuario(Usuario usuario);
	public void deleteUsuarioById(String idUsuario);
	public List<Usuario> findAllUsuario();
	public Usuario findUsuarioById(String idUsuario);

}
