package com.cloudST.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Privilegios {

	//falta restricciones de anotaciones
	@Id
	private Calendar fecha;
	private Integer tipo;
	private boolean status;
	private Integer idUsuario;

	//Getters
	public Integer getTipo(){return this.tipo;}
	public Calendar getFecha(){return this.fecha;}
	public boolean getStatus(){return this.status;}
	public Integer getIdUsuario(){return this.idUsuario;}


	//Setters
	public void setTipo(Integer tipo){this.tipo = tipo;}
	public void setFecha(Calendar fecha){this.fecha = fecha;}
	public void setStatus(boolean status){this.status = status;}
	public void setIdUsuario(Integer idUsuario){this.idUsuario = idUsuario;}

}
