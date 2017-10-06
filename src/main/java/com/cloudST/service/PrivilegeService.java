package com.cloudST.service;

import com.cloudST.model.Privilege;


public interface PrivilegeService {
	
	Privilege findByIdUser(Integer idUser);
	
	Privilege create(Integer type, Integer idUser);

	Privilege update(Integer idUser, Integer type);

	Integer actualType(Integer idUser);

}
