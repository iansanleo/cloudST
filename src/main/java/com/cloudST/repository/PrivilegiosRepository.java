package com.cloudST.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloudST.model.Privilegios;

@Repository
public interface PrivilegiosRepository extends CrudRepository<Privilegios, Integer> {


}
