package com.cloudST.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudST.model.Transaccion;
import com.cloudST.repository.TransaccionRespository;
import com.cloudST.service.TransaccionService;

@Service
public class TransaccionServiceImpl implements TransaccionService {

	@Autowired
	private TransaccionRespository transaccionRepository;
	//@Autowired
	//private Fecha fecha;
	
	@Override
	public Transaccion createUpload(Integer idArchivo, Integer idUser) {
		//Transaccion transaccion = new Transaccion(fecha.fechaActual(),"upload",idArchivo,idUser);
		Transaccion transaccion = new Transaccion(fechaActual(),"upload",idArchivo,idUser);
		return transaccionRepository.save(transaccion);
	}

	@Override
	public Transaccion createDelete(Integer idArchivo, Integer idUser) {
		//Transaccion transaccion = new Transaccion(fecha.fechaActual(),"delete",idArchivo,idUser);
		Transaccion transaccion = new Transaccion(fechaActual(),"delete",idArchivo,idUser);
		return transaccionRepository.save(transaccion);
	}
	//
	public Date fechaActual(){
		Date fecha = new java.util.Date(); //fecha actual
		Timestamp sqlTimestamp = new Timestamp(fecha.getTime());//en milisegundos
		fecha = new Date(sqlTimestamp.getTime());
		
		return fecha;
	}
}
