package com.cloudST.model;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {

	//falta restricciones de anotaciones
	private static final long serialVersionUID = 4864893484040058494L;
	private int idUsuario;
	private String userName;
	private String password;
	private String nombre;
	private String email;
	private Date fechaInicio;
	private boolean status;
	
	//Getters
	public int getIdUsusario(){return this.idUsuario;}
	public String getUserName(){return this.userName;}
	public String getPassword(){return this.password;}
	public String getNombre(){return this.nombre;}
	public String getEmail(){return this.email;}
	public Date getFechaInicio(){return this.fechaInicio;}
	public boolean getStatus(){return this.status;}
	
	//Setters
	public void setIdUsuario(int idUsuario){this.idUsuario = idUsuario;}
	public void setUserName(String username){this.userName = username;}
	public void setPassword(String password){this.password = password;}
	public void setNombre(String nombre){this.nombre = nombre;}
	public void setEmail(String email){this.email = email;}
	public void setFechaInicio(Date fechaInicio){this.fechaInicio = fechaInicio;}
	public void setStatus(boolean status){this.status = status;}

	
	
	

}
