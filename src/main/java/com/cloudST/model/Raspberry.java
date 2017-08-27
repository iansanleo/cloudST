package com.cloudST.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Raspberry {

	//falta restricciones de anotaciones
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idRaspberry;
	@NotNull
	private String ip;
	@NotNull
	private String mac;
	//MB
	@NotNull
	private double tamanyoTotal;
	//MB
	@NotNull
	private double tamanyoUso;
	private Date fechaConexion;
	@NotNull
	private boolean status;

	//Getters
	public Integer getIdRaspberry(){return this.idRaspberry;}
	public String getIp(){return this.ip;}
	public String getMac(){return this.mac;}
	public double getTamanyoTotal(){return this.tamanyoTotal;}
	public double getTamanyoUso(){return this.tamanyoUso;}
	public Date getFechaConexion(){return this.fechaConexion;}
	public boolean getStatus(){return this.status;}

	//Setters
	public void setIdRaspberry(Integer idRaspberry){this.idRaspberry = idRaspberry;}
	public void setIp(String ip){this.ip = ip;}
	public void setMac(String mac){this.mac = mac;}
	public void setTamanyoTotal(double tamanyoTotal){this.tamanyoTotal = tamanyoTotal;}
	public void setTamanyoUso (double tamanyoUso){this.tamanyoUso = tamanyoUso;}
	public void setFechaConexion(Date fechaConexion){this.fechaConexion = fechaConexion;}
	public void sertStatus (boolean status){this.status = status;}
}
