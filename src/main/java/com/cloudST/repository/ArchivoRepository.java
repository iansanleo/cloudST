package com.cloudST.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloudST.model.Archivos;

@Repository
public interface ArchivoRepository extends CrudRepository<Archivos, Integer> {

	@Query("SELECT A FROM Archivos A WHERE A.idUsuario=:idUsuario AND A.status=true")
	public Iterable<Archivos> findByIdUser(@Param("idUsuario")Integer idUsuario);
}