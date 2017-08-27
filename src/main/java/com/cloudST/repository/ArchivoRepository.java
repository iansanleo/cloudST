package com.cloudST.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloudST.model.Archivos;

@Repository
public interface ArchivoRepository extends CrudRepository<Archivos, Integer> {

	    //List<Usuario> findByEmail(String email);
}