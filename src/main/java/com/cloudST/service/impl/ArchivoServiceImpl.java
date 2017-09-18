package com.cloudST.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudST.model.Archivos;
import com.cloudST.repository.ArchivoRepository;
import com.cloudST.service.ArchivoService;
import com.cloudST.service.exception.ArchivoException;

@Service
public class ArchivoServiceImpl implements ArchivoService {
	
	@Autowired
	private ArchivoRepository archivoRepository;
	
	@Override
	public Archivos create(String nombreOri, String nombreSys, double tamanyo, String tipo, Integer idUsuario) {
		Archivos archivo = new Archivos(nombreOri,  nombreSys, true,  tamanyo, tipo, idUsuario);
		
		archivoRepository.save(archivo);
		
		return archivo;
	}

	@Override
	public ArrayList<Archivos> allUserFiles(Integer idUsuario) {
		ArrayList<Archivos> listArchivos = (ArrayList<Archivos>) archivoRepository.findByIdUser(idUsuario);
		return listArchivos;
	}

	@Override
	public Archivos delete(Integer idUser)throws ArchivoException {
		Archivos archivo = archivoRepository.findOne(idUser);
		archivo.setStatus(false);
		archivoRepository.save(archivo);
		return archivo;
	}
	
	@Override
	public ArrayList<Archivos> allFiles(){
		return (ArrayList<Archivos>) archivoRepository.findAll();
	}

	@Override
	public ArrayList<Archivos> archivosLiberar(){
		ArrayList<Archivos> listArchivos = allFiles();
		for(int i=0;i<listArchivos.size();i++){
			if(!listArchivos.get(i).getStatus()){
				listArchivos.remove(i);
			}
		}
		return listArchivos;
	}
}
