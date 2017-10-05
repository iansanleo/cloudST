package com.cloudST.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudST.model.Archive;
import com.cloudST.repository.ArchiveRepository;
import com.cloudST.service.ArchiveService;
import com.cloudST.service.exception.ArchiveException;

@Service
public class ArchiveServiceImpl implements ArchiveService {
	
	@Autowired
	private ArchiveRepository fileRepository;

	
	@Override
	public Archive create(String oriName, String sysName, double size, String type, Integer idUser) {
		Archive file = new Archive(oriName,  sysName, true,  size, type, idUser);
		
		fileRepository.save(file);
		
		return file;
	}
	
	@Override
	public ArrayList<Archive> allUserFiles(Integer idUser) {
		return (ArrayList<Archive>) fileRepository.findByIdUser(idUser);
	}

	@Override
	public Archive delete(Integer idUser)throws ArchiveException {
		Archive file = fileRepository.findOne(idUser);
		file.setStatus(false);
		fileRepository.save(file);
		return file;
	}
	
	@Override
	public ArrayList<Archive> allFiles(){
		return (ArrayList<Archive>) fileRepository.findAll();
	}

	@Override
	public ArrayList<Archive> filesLiberate(){
		ArrayList<Archive> listFile = allFiles();
		for(int i=0;i<listFile.size();i++){
			if(!listFile.get(i).getStatus()){
				listFile.remove(i);
			}
		}
		return listFile;
	}

	@Override
	public Archive findByIdFile(Integer idFile) {
		fileRepository.findOne(idFile);
		return null;
	}
}
