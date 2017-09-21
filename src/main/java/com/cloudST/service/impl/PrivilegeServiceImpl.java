package com.cloudST.service.impl;

import com.cloudST.utiles.DateUtils;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudST.model.Privilege;
import com.cloudST.repository.PrivilegeRepository;
import com.cloudST.service.PrivilegeService;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
	
	@Autowired
    private PrivilegeRepository privilegeRepository;
	
	@Override
	public Privilege findByIdUser(Integer idUser){
		return privilegeRepository.findByIdUser(idUser);
	}
	
	@Override
	public Privilege create(Integer type, Integer idUser){
		Privilege privilege = new Privilege(DateUtils.actualDate(),type, true, idUser);
		privilegeRepository.save(privilege);
		return privilege;
	}

	@Override
	public Privilege update(Integer idUser, Integer type) {
		ArrayList<Privilege> listPrivilege = (ArrayList<Privilege>)privilegeRepository.findAll();
		
		for(int i=0;i<listPrivilege.size();i++){
			if(listPrivilege.get(i).getIdUsuario()==idUser && listPrivilege.get(i).getStatus()== true){
				if(listPrivilege.get(i).getType()==type){
					return listPrivilege.get(i);
				}else{
					listPrivilege.get(i).setStatus(false);
					return create(type,idUser);
				}
			}
		}
	return null;
	}
}
