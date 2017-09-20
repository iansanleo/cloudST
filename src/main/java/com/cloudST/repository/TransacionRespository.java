package com.cloudST.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloudST.model.Transaction;

@Repository
public interface TransacionRespository extends CrudRepository<Transaction, Integer> {

}