package com.cloudST.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudST.model.File;
import com.cloudST.repository.FileRepository;
import com.cloudST.service.FileService;
import com.cloudST.service.exception.FileException;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	private FileRepository fileRepository;
	
	@Override
	public File create(String oriName, String sysName, double size, String type, Integer idUser) {
		File file = new File(oriName,  sysName, true,  size, type, idUser);
		
		fileRepository.save(file);
		
		return file;
	}

	@Override
	public ArrayList<File> allUserFiles(Integer idUser) {
		return (ArrayList<File>) fileRepository.findByIdUser(idUser);
	}

	@Override
	public File delete(Integer idFile)throws FileException {
		File file = fileRepository.findOne(idFile);
		file.setStatus(false);
		fileRepository.save(file);
		return file;
	}
	
	@Override
	public ArrayList<File> allFiles(){
		return (ArrayList<File>) fileRepository.findAll();
	}

	@Override
	public ArrayList<File> filesLiberate(){
		return (ArrayList<File>) fileRepository.allTrueFiles();
	}

	@Override
	public File findByIdFile(Integer idFile) {
		return fileRepository.findOne(idFile);
	}
	
	@Override
	public void deleteAllFilesUser(Integer idUser){
		
		ArrayList<File> allFiles= allUserFiles(idUser);
		for (int i=0;i<allFiles.size();i++){
			fileRepository.delete(allFiles.get(i));
			
			
		}
		
	}
}
