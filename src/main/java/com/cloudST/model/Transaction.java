package com.cloudST.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idTrans;
	@NotNull
	private Date created;
	@NotNull
	private String type;
	@NotNull
	private Integer idFile;
	@NotNull
	private Integer idUser;
	
	public Transaction(){}
	public Transaction(Date created, String type, Integer idFile, Integer idUser){
		
		this.created = created;
		this.type = type;
		this.idFile = idFile;
		this.idUser = idUser;
	}
	

	//Getters
	public Integer getIdTransaction(){return this.idTrans;}
	public Date getCreatedDate(){return this.created;}
	public String getType(){return this.type;}
	public Integer getIdArchivo(){return this.idFile;}
	public Integer getIdUser(){return this.idUser;}

	//Setters
	public void setIdTransaccion (Integer idTrans){this.idTrans = idTrans;}
	public void setCreatedDate (Date created){this.created = created;}
	public void setType(String type){this.type = type;}
	public void setIFile(Integer idFile){this.idFile = idFile;}
	public void setidUser(Integer idUser){this.idUser = idUser;}



}