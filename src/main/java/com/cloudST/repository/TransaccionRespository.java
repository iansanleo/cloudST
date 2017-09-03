package com.cloudST.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloudST.model.Transaccion;

@Repository
public interface TransaccionRespository extends CrudRepository<Transaccion, Integer> {


}