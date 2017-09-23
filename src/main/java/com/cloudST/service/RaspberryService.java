package com.cloudST.service;

import java.util.ArrayList;

import com.cloudST.model.Raspberry;

public interface RaspberryService {

	double totalSizeRasps();
	double totalSizeUsed();
	ArrayList<Raspberry> listDevicesOn();
	Raspberry delete(Integer idRaspberry);

}
