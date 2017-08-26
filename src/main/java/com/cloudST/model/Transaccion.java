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
	private Integer idTransaccion;
	private Date fecha;
	private String tipo;
	private Integer idArchivo;
	private Integer idUsuario;

	//Getters
	public Integer getIdTransaccion(){return this.idTransaccion;}
	public Date getFecha(){return this.fecha;}
	public String getTipo(){return this.tipo;}
	public Integer getIdArchivo(){return this.idArchivo;}
	public Integer getIdUsuario(){return this.idUsuario;}

	//Setters
	public void setIdTransaccion (Integer idTransaccion){this.idTransaccion = idTransaccion;}
	public void setFecha (Date fecha){this.fecha = fecha;}
	public void setTipo(String tipo){this.tipo = tipo;}
	public void setIdArchivo (Integer idArchivo){this.idArchivo = idArchivo;}
	public void setidUsuario (Integer idUsuario){this.idUsuario = idUsuario;}



}