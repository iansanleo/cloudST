package com.cloudST.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Privilegios {

	@Id
	private Date fecha;
	@NotNull
	private Integer tipo;
	private boolean status;
	@NotNull
	private Integer idUsuario;

	//protected Privilegios(){}
	public Privilegios(){}
	
	public Privilegios(Date fecha, Integer tipo, boolean status, Integer idUsuario){
		this.fecha = fecha;
		this.tipo =tipo;
		this.status = status;
		this.idUsuario = idUsuario;
		
	}
	
	
	//Getters
	public Integer getTipo(){return this.tipo;}
	public Date getFecha(){return this.fecha;}
	public boolean getStatus(){return this.status;}
	public Integer getIdUsuario(){return this.idUsuario;}


	//Setters
	public void setTipo(Integer tipo){this.tipo = tipo;}
	public void setFecha(Date fecha){this.fecha = fecha;}
	public void setStatus(boolean status){this.status = status;}
	public void setIdUsuario(Integer idUsuario){this.idUsuario = idUsuario;}

}
