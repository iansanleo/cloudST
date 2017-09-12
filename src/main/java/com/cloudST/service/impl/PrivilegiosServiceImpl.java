package com.cloudST.service.impl;

import com.cloudST.utiles.FechaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudST.model.Privilegios;
import com.cloudST.repository.PrivilegiosRepository;
import com.cloudST.service.PrivilegiosService;

@Service
public class PrivilegiosServiceImpl implements PrivilegiosService {
	
	@Autowired
    private PrivilegiosRepository privilegiosRepository;
    //@Autowired
    //private FechaUtils fecha;
	
	@Override
	public Privilegios findByIdUser(Integer idUsuario){
		
		return privilegiosRepository.findByIdUser(idUsuario);
	}
	
	@Override
	public Privilegios create(Integer tipo, Integer idUsuario){
		//Privilegios privilegios = new Privilegios(fecha.fechaActual(),tipo, true, idUsuario);
		Privilegios privilegios = new Privilegios(FechaUtils.fechaActual(),tipo, true, idUsuario);
		privilegiosRepository.save(privilegios);
		return privilegios;
	}

}
