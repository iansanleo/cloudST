package com.cloudST.dao;

import java.util.List;

import com.cloudST.model.Usuario;
import com.cloudST.repository.UsuarioRepository;

public class UsuarioDaoImpl implements UsuarioDao {

	private UsuarioRepository repository;
	
	@Override
	public Usuario findById(int idUsuario) {
		return repository.findOne((long) idUsuario);
	}

	@Override
	public void saveUsuario(Usuario usuario) {
		
		
	}

	@Override
	public void deleteUsuarioById(String idUsuario) {
	
		
	}

	@Override
	public List<Usuario> findAllUsuario() {
		
		return null;
	}

	@Override
	public Usuario findUsuarioById(String idUsuario) {
		
		return null;
	}

}
