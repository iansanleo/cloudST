package com.cloudST.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloudST.model.File;

@Repository
public interface FileRepository extends CrudRepository<File, Integer> {

	@Query("SELECT F FROM File F WHERE F.idUser=:idUser AND F.status=true")
	public Iterable<File> findByIdUser(@Param("idUser")Integer idUser);
}