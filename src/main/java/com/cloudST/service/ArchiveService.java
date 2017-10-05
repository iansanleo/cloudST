package com.cloudST.service;

import java.util.ArrayList;

import com.cloudST.model.Archive;
import com.cloudST.service.exception.ArchiveException;

public interface ArchiveService {
	
	Archive create(String nombreOri, String nombreSys, double tamanyo, String tipo, Integer idUsuario);
	
	ArrayList<Archive> allUserFiles(Integer idUsuario);
	
	Archive delete(Integer idUser) throws ArchiveException;

	ArrayList<Archive> allFiles();

	ArrayList<Archive> filesLiberate();

	Archive findByIdFile(Integer idFile);
}
