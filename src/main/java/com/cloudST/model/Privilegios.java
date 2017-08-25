package com.cloudST.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Privilegios {

	//falta restricciones de anotaciones
	@Id
	private Date fecha;
	private int tipo;
	private boolean status;
	private int idUsuario;

	//Getters
	public int getTipo(){return this.tipo;}
	public Date getFecha(){return this.fecha;}
	public boolean getStatus(){return this.status;}
	public int getIdUsuario(){return this.idUsuario;}


	//Setters
	public void setTipo(int tipo){this.tipo = tipo;}
	public void setFecha(Date fecha){this.fecha = fecha;}
	public void setStatus(boolean status){this.status = status;}
	public void setIdUsuario(int idUsuario){this.idUsuario = idUsuario;}

}
