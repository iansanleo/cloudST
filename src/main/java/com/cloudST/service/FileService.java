package com.cloudST.service;

import java.util.ArrayList;

import com.cloudST.model.File;
import com.cloudST.service.exception.FileException;

public interface FileService {
	
	File create(String nombreOri, String nombreSys, double tamanyo, String tipo, Integer idUsuario);
	
	ArrayList<File> allUserFiles(Integer idUsuario);
	
	File delete(Integer idUser) throws FileException;

	ArrayList<File> allFiles();

	ArrayList<File> filesLiberate();

	File findByIdFile(int idFile);
}
