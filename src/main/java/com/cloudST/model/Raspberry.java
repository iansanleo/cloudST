package com.cloudST.model;

import java.util.Date;

public class Raspberry {

	//falta restricciones de anotaciones
	private int idRaspberry;
	private String ip;
	private String mac;
	//MB
	private double tamanyoTotal;
	//MB
	private double tamanyoUso;
	private Date fechaConexion;
	private boolean status;
	
	//Getters
	public int getIdRaspberry(){return this.idRaspberry;}
	public String getIp(){return this.ip;}
	public String getMac(){return this.mac;}
	public double getTamanyoTotal(){return this.tamanyoTotal;}
	public double getTamanyoUso(){return this.tamanyoUso;}
	public Date getFechaConexion(){return this.fechaConexion;}
	public boolean getStatus(){return this.status;}
	
	//Setters
}
