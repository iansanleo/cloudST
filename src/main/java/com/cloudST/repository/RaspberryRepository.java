package com.cloudST.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloudST.model.Raspberry;

@Repository
public interface RaspberryRepository extends CrudRepository<Raspberry, Integer> {	
	
	@Query("SELECT R FROM Raspberry R WHERE R.mac=:mac AND R.status=true")
	public Raspberry findRaspberryMac(@Param("mac")String mac);

	@Query("SELECT R FROM Raspberry R WHERE  R.status=true")
	public Iterable<Raspberry> findAllOn();
}