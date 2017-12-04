package com.cloudST.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloudST.model.Privilege;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Integer> {

	@Query("SELECT P FROM Privilege P WHERE (P.idUser)=:idUser AND P.status=true")
	public Privilege findByIdUser(@Param("idUser")Integer idUser);
	
	@Query ("SELECT P FROM Privilege P WHERE P.status=true")
	public ArrayList<Privilege> findAllTrue();
}
