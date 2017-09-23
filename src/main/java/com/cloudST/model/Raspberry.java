package com.cloudST.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Raspberry {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idRaspberry;
	@NotNull
	private String ip;
	@NotNull
	private String mac;
	//MB
	@NotNull
	private double totalSize;
	//MB
	@NotNull
	private double useSize;
	private Date conexionDate;
	@NotNull
	private boolean status;

	public Raspberry(){}
	public Raspberry(String ip, String mac, double totalSize, double useSize,
			Date conexionDate, boolean status){
		
		this.ip = ip;
		this.mac = mac;
		this.totalSize = totalSize;
		this.useSize = useSize;
		this.conexionDate = conexionDate;
		this.status = status;
		
	}
	
	//Getters
	public Integer getIdRaspberry(){return this.idRaspberry;}
	public String getIp(){return this.ip;}
	public String getMac(){return this.mac;}
	public double getTotalSize(){return this.totalSize;}
	public double getUseSize(){return this.useSize;}
	public Date getConexionDate(){return this.conexionDate;}
	public boolean getStatus(){return this.status;}

	//Setters
	public void setIdRaspberry(Integer idRaspberry){this.idRaspberry = idRaspberry;}
	public void setIp(String ip){this.ip = ip;}
	public void setMac(String mac){this.mac = mac;}
	public void setTotalSize(double totalSize){this.totalSize = totalSize;}
	public void setUseSize (double useSize){this.useSize = useSize;}
	public void setConexionDate(Date conexionDate){this.conexionDate = conexionDate;}
	public void setStatus (boolean status){this.status = status;}
}
