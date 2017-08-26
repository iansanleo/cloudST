package com.cloudST.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {

	//falta restricciones de anotaciones
	private static final long serialVersionUID = 4864893484040058494L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idUsuario;
	private String username;
	private String password;
	private String nombre;
	private String email;
	private Date fechaInicio;
	private boolean status;

	//protected Usuario(){}
	public Usuario(){}

	public Usuario(String username, String password, String nombre, String email,
				   Date fechaInicio, boolean status){
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.email = email;
		this.fechaInicio = fechaInicio;
		this.status = status;
	}

	//Getters
	public Integer getIdUsusario(){return this.idUsuario;}
	public String getUsername(){return this.username;}
	public String getPassword(){return this.password;}
	public String getNombre(){return this.nombre;}
	public String getEmail(){return this.email;}
	public Date getFechaInicio(){return this.fechaInicio;}
	public boolean getStatus(){return this.status;}

	//Setters
	public void setIdUsuario(Integer idUsuario){this.idUsuario = idUsuario;}
	public void setUsername(String username){this.username = username;}
	public void setPassword(String password){this.password = password;}
	public void setNombre(String nombre){this.nombre = nombre;}
	public void setEmail(String email){this.email = email;}
	public void setFechaInicio(Date fechaInicio){this.fechaInicio = fechaInicio;}
	public void setStatus(boolean status){this.status = status;}





}
