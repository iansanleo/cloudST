package com.cloudST.service;

import java.util.ArrayList;
import java.util.Date;

import com.cloudST.model.Raspberry;

public interface RaspberryService {

	double totalSizeRasps();
	double totalSizeUsed();
	ArrayList<Raspberry> listDevicesOn();
	Raspberry delete(Integer idRaspberry);
	Raspberry findById(Integer idRaspberry);
	Raspberry create(String ip, String mac, double totalSize, double useSize);
	Raspberry findByMac(String mac);
	Raspberry update(Integer idRaspberry, String ip, String mac, double totalSize, double useSize, Date conexionDate,
			boolean status);
	Raspberry create(Raspberry raspberry);

}
