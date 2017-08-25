package com.cloudST.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaccion {

	//falta restricciones de anotaciones
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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