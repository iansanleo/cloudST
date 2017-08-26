package com.cloudST.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Archivos {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idArchivo;
	private String nombreOri;
	private String nombreSys;
	private boolean status;
	//MB
	private double tamanyo;
	private String tipo;
	private Integer idUsuario;

	//Getters
	public Integer getIdArchivo(){return this.idArchivo;}
	public String getNombreOri(){return this.nombreOri;}
	public String getNombreSys(){return this.nombreSys;}
	public boolean getStatus(){return this.status;}
	public double getTamanyo(){return this.tamanyo;}
	public String getTipo(){return this.tipo;}
	public Integer getIdUsuario(){return this.idUsuario;}

	//Setters
	public void setIdArchivo(Integer idArchivo){this.idArchivo = idArchivo;}
	public void setNombreOri(String nombreOri){this.nombreOri = nombreOri;}
	public void setNombreSys(String nombreSys){this.nombreSys = nombreSys;}
	public void setStatus(boolean status){this.status = status;}
	public void setTamanyo(double tamanyo){this.tamanyo = tamanyo;}
	public void setString(String tipo){this.tipo = tipo;}
	public void setIdUsuario (Integer idUsuario){this.idUsuario = idUsuario;}
}
