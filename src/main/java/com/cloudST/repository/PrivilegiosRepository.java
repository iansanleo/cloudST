package com.cloudST.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloudST.model.Privilegios;

@Repository
public interface PrivilegiosRepository extends CrudRepository<Privilegios, Integer> {

	@Query("SELECT P FROM Privilegios P WHERE LOWER(P.idUsuario)=:idUsuario AND P.status=true")
	public Privilegios findByIdUser(@Param("idUsuario")Integer idUsuario);
	
}
