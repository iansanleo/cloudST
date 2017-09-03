package com.cloudST.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Transaccion {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idTransaccion;
	@NotNull
	private Date fecha;
	@NotNull
	private String tipo;
	@NotNull
	private Integer idArchivo;
	@NotNull
	private Integer idUsuario;
	
	//protected Transaccion(){}
	public Transaccion(){}
	
	public Transaccion(Date fecha, String tipo, Integer idArchivo, Integer idUsuario){
		
		this.fecha = fecha;
		this.tipo = tipo;
		this.idArchivo = idArchivo;
		this.idUsuario = idUsuario;
	}
	

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