package com.cloudST.model;

import java.util.Date;

public class Transaccion {
	
	//falta restricciones de anotaciones
	private int idTransaccion;
	private Date fecha;
	private int idArchivo;
	private int idUsuario;
	
	//Getters
	public int getIdTransaccion(){return this.idTransaccion;}
	public Date getFecha(){return this.fecha;}
	public int getIdArchivo(){return this.idArchivo;}
	public int getIdUsuario(){return this.idUsuario;}
	
	//Setters
	public void setIdTransaccion (int idTransaccion){this.idTransaccion = idTransaccion;}
	public void setFecha (Date fecha){this.fecha = fecha;}
	public void setIdArchivo (int idArchivo){this.idArchivo = idArchivo;}
	public void setidUsuario (int idUsuario){this.idUsuario = idUsuario;}
	
	

}
