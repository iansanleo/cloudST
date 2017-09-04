package com.cloudST.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloudST.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	public Usuario findByEmail(String email);
	public Usuario findByUsername(String usuario);
	
}