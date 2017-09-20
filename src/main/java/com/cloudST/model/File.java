package com.cloudST.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class File {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idFile;
	private String oriName;
	@NotNull
	private String sysName;
	private boolean status;
	//MB
	private double size;
	private String type;
	@NotNull
	private Integer idUser;
	
	public File(){}

	public File(String oriName, String sysName, boolean status, double size,
			String type, Integer idUser){
		
		this.oriName = oriName;
		this.sysName = sysName;
		this.status = status;
		this.size = size;
		this.type = type;
		this.idUser = idUser;
	}

	//Getters
	public Integer getIdFile(){return this.idFile;}
	public String getOriName(){return this.oriName;}
	public String getSysName(){return this.sysName;}
	public boolean getStatus(){return this.status;}
	public double getSize(){return this.size;}
	public String getType(){return this.type;}
	public Integer getIdUser(){return this.idUser;}

	//Setters
	public void setIdFile(Integer idFile){this.idFile = idFile;}
	public void setOriName(String oriName){this.oriName = oriName;}
	public void setNombreSys(String sysName){this.sysName = sysName;}
	public void setStatus(boolean status){this.status = status;}
	public void setSize(double size){this.size = size;}
	public void setType(String type){this.type = type;}
	public void setIdUser(Integer idUser){this.idUser = idUser;}
}
