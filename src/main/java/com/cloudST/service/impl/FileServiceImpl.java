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
	public File delete(Integer idUser)throws FileException {
		File file = fileRepository.findOne(idUser);
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
		ArrayList<File> listFile = allFiles();
		for(int i=0;i<listFile.size();i++){
			if(!listFile.get(i).getStatus()){
				listFile.remove(i);
			}
		}
		return listFile;
	}

	@Override
	public File findByIdFile(int idFile) {
		return fileRepository.findOne(idFile);
	}
}
