package com.cloudST.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudST.model.Privilege;
import com.cloudST.repository.PrivilegeRepository;
import com.cloudST.service.PrivilegeService;
import com.cloudST.utiles.DateUtils;

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
		Privilege privilege =privilegeRepository.findByIdUser(idUser);
		if(privilege.getType()==type){
			return privilege;
		}else{
			deleteUser(idUser);
			return create(type,idUser);
		}
	}
	
	@Override
	public Integer actualType(Integer idUser){
		return privilegeRepository.findByIdUser(idUser).getType();
	}
	
	@Override
	public Privilege deleteUser(Integer idUser){
		Privilege privilege = privilegeRepository.findByIdUser(idUser);
		privilege.setStatus(false);
		return privilegeRepository.save(privilege);
	}
}
