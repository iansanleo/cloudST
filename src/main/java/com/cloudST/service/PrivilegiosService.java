package com.cloudST.service;

import com.cloudST.model.Privilegios;


public interface PrivilegiosService {
	
	Privilegios findByIdUser(Integer idUsuario);
	
	Privilegios create(Integer tipo, Integer idUsuario);

}
