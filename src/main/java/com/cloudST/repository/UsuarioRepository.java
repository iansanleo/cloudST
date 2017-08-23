package com.cloudST.repository;

import com.cloudST.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	    //List<Usuario> findByEmail(String email);
}