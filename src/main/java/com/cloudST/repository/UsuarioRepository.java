package com.cloudST.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cloudST.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	    List<Usuario> findByEmail(String email);
	}