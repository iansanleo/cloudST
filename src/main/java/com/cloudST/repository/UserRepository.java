package com.cloudST.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloudST.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByEmail(String email);
	public User findByResetToken(String resetToken);
	
	@Query("SELECT U FROM User U WHERE LOWER(U.username)=:username")
	public User findByUser(@Param("username")String username);
	
}