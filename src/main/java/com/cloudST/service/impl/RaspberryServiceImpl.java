package com.cloudST.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudST.model.Raspberry;
import com.cloudST.repository.RaspberryRepository;
import com.cloudST.service.RaspberryService;
import com.cloudST.utiles.DateUtils;

@Service
public class RaspberryServiceImpl implements RaspberryService {
	
	@Autowired
	RaspberryRepository raspberryRepository;
	
	@Override
	public double totalSizeRasps() {
		ArrayList<Raspberry> listRaspberry = (ArrayList<Raspberry>) raspberryRepository.findAll();
		double totalSize=1;
		
		for(int i=0; i<listRaspberry.size();i++){
			totalSize=+listRaspberry.get(i).getTotalSize();
		}
		return totalSize;
	}

	@Override
	public Raspberry findByMac(String mac){
		return raspberryRepository.findRaspberryMac(mac);
	}
	
	@Override
	public double totalSizeUsed() {
		ArrayList<Raspberry> listRaspberry = (ArrayList<Raspberry>) raspberryRepository.findAll();
		double totalSize=1;
		for(int i=0; i<listRaspberry.size();i++){
			totalSize=+listRaspberry.get(i).getUseSize();
		}
		return totalSize;
	}
	
	@Override
	public ArrayList<Raspberry> listDevicesOn(){
		ArrayList<Raspberry> listRaspberry = (ArrayList<Raspberry>)raspberryRepository.findAllOn();
		for(int i=0;i<listRaspberry.size();i++){
			if(!listRaspberry.get(i).getStatus()){
				listRaspberry.remove(i);
			}
		}
		return listRaspberry;
	}
	@Override
	public Raspberry create(String ip, String mac, double totalSize, double useSize){
		Raspberry raspberry = new Raspberry( ip, mac, totalSize, useSize, DateUtils.actualDate(), true);
		raspberryRepository.save(raspberry);
		return raspberry;
	}
	
	@Override
	public Raspberry update(Integer idRaspberry,String ip, String mac, double totalSize, double useSize,
			Date conexionDate, boolean status){
		Raspberry raspberry = raspberryRepository.findOne(idRaspberry);
		raspberry.setIp(ip);
		raspberry.setMac(mac);
		raspberry.setTotalSize(totalSize);
		raspberry.setConexionDate(conexionDate);
		raspberry.setStatus(status);
		
		raspberryRepository.save(raspberry);
		
		return raspberry;
	}

	@Override
	public Raspberry delete(Integer idRaspberry) {
		Raspberry raspberry = raspberryRepository.findOne(idRaspberry);
		raspberry.setStatus(false);
		raspberryRepository.save(raspberry);
		return raspberry;
	}

	@Override
	public Raspberry findById(Integer idRaspberry) {
		return raspberryRepository.findOne(idRaspberry);
	}
}
