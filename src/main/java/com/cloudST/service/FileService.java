package com.cloudST.service;

import java.util.ArrayList;

import com.cloudST.model.File;
import com.cloudST.service.exception.FileException;

public interface FileService {
	
	File create(String oriName, String sysName, double size, String type, Integer idUser);
	
	ArrayList<File> allUserFiles(Integer idUser);
	
	File delete(Integer idUser) throws FileException;

	ArrayList<File> allFiles();

	ArrayList<File> filesLiberate();

	File findByIdFile(Integer idFile);

	void deleteAllFilesUser(Integer idUser);

}
