package com.cloudST.model;

import java.util.Date;

public class Privilegios {
	
	//falta restricciones de anotaciones
	private int tipo;
	private Date fecha;
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
