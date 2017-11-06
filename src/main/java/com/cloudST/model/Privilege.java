package com.cloudST.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Privilege {

	@Id
	private Date date;
	@NotNull
	//3admin
	//0free
	//1pay
	private Integer type;
	private boolean status;
	@NotNull
	private Integer idUser;

	public Privilege(){}
	
	public Privilege(Date date, Integer type, boolean status, Integer idUser){
		this.date = date;
		this.type =type;
		this.status = status;
		this.idUser = idUser;
		
	}
	
	
	//Getters
	public Integer getType(){return this.type;}
	public Date getDateCreated(){return this.date;}
	public boolean getStatus(){return this.status;}
	public Integer getIdUsuario(){return this.idUser;}


	//Setters
	public void setType(Integer type){this.type = type;}
	public void setDateCreated(Date date){this.date = date;}
	public void setStatus(boolean status){this.status = status;}
	public void setIdUser(Integer idUser){this.idUser = idUser;}

}
