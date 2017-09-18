package com.cloudST.service;

import java.util.ArrayList;

import com.cloudST.model.Archivos;
import com.cloudST.service.exception.ArchivoException;

public interface ArchivoService {
	
	Archivos create(String nombreOri, String nombreSys, double tamanyo, String tipo, Integer idUsuario);
	
	ArrayList<Archivos> allUserFiles(Integer idUsuario);
	
	Archivos delete(Integer idUser) throws ArchivoException;

	ArrayList<Archivos> allFiles();

	ArrayList<Archivos> archivosLiberar();
}
