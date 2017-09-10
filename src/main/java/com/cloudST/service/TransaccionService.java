package com.cloudST.service;

import com.cloudST.model.Transaccion;

public interface TransaccionService {
	
	Transaccion createUpload(Integer idArchivo, Integer idUser);
	
	Transaccion createDelete(Integer idArchivo, Integer idUser);

}
