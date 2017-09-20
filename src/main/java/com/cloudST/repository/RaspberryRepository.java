package com.cloudST.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloudST.model.Raspberry;

@Repository
public interface RaspberryRepository extends CrudRepository<Raspberry, Integer> {	
	
}