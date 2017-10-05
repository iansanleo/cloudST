package com.cloudST.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloudST.model.Archive;

@Repository
public interface ArchiveRepository extends CrudRepository<Archive, Integer> {

	@Query("SELECT F FROM File F WHERE F.idUser=:idUser AND F.status=true")
	public Iterable<Archive> findByIdUser(@Param("idUser")Integer idUser);

	

}