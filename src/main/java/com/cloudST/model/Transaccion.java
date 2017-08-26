package com.cloudST.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaccion {

	//falta restricciones de anotaciones
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idTransaccion;
	private Calendar fecha;
	private Integer idArchivo;
	private Integer idUsuario;

	//Getters
	public Integer getIdTransaccion(){return this.idTransaccion;}
	public Calendar getFecha(){return this.fecha;}
	public Integer getIdArchivo(){return this.idArchivo;}
	public Integer getIdUsuario(){return this.idUsuario;}

	//Setters
	public void setIdTransaccion (Integer idTransaccion){this.idTransaccion = idTransaccion;}
	public void setFecha (Calendar fecha){this.fecha = fecha;}
	public void setIdArchivo (Integer idArchivo){this.idArchivo = idArchivo;}
	public void setidUsuario (Integer idUsuario){this.idUsuario = idUsuario;}



}