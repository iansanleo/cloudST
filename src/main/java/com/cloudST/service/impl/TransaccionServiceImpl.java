package com.cloudST.service.impl;

import com.cloudST.model.Transaccion;
import com.cloudST.repository.TransaccionRespository;
import com.cloudST.service.TransaccionService;
import com.cloudST.utiles.FechaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransaccionServiceImpl implements TransaccionService {

	@Autowired
	private TransaccionRespository transaccionRepository;
	
	@Override
	public Transaccion createUpload(Integer idArchivo, Integer idUser) {
		//Transaccion transaccion = new Transaccion(fecha.fechaActual(),"upload",idArchivo,idUser);
		Transaccion transaccion = new Transaccion(FechaUtils.fechaActual(),"upload",idArchivo,idUser);
		return transaccionRepository.save(transaccion);
	}

	@Override
	public Transaccion createDelete(Integer idArchivo, Integer idUser) {
		//Transaccion transaccion = new Transaccion(fecha.fechaActual(),"delete",idArchivo,idUser);
		Transaccion transaccion = new Transaccion(FechaUtils.fechaActual(),"delete",idArchivo,idUser);
		return transaccionRepository.save(transaccion);
	}
}
