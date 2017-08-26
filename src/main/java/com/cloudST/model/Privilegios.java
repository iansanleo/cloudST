package com.cloudST.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Privilegios {

	//falta restricciones de anotaciones
	@Id
	private Date fecha;
	private Integer tipo;
	private boolean status;
	private Integer idUsuario;

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
