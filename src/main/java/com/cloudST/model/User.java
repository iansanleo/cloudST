package com.cloudST.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
public class User implements Serializable {

	
	private static final long serialVersionUID = 4864893484040058494L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idUser;
	@NotNull
	@Size(min = 1, max = 20)
	private String username;
	@NotNull
	@Size(min = 1, max = 20)
	private String password;
	@Size(min = 1, max = 50)
	private String name;
	@Email 
	@NotNull
	private String email;
	@NotNull
	private Date created;
	@NotNull
	private boolean status;
	@NotNull
	private boolean valid;
	private Date lastLogin;
	private String resetToken;
	
	public User(){}

	public User(String username, String password, String name, String email,
				   Date created, boolean status){
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.created = created;
		this.status = status;
		this.valid = false;
	}

	//Getters
	public Integer getIdUser(){return this.idUser;}
	public String getUsername(){return this.username;}
	public String getPassword(){return this.password;}
	public String getName(){return this.name;}
	public String getEmail(){return this.email;}
	public Date getDateCreated(){return this.created;}
	public boolean getStatus(){return this.status;}
	public boolean getValid(){return this.valid;}
	public Date getLastLogin(){return this.lastLogin;};
	public String getResetToken(){return this.resetToken;}

	//Setters
	public void setIdUser(Integer idUser){this.idUser = idUser;}
	public void setUsername(String username){this.username = username;}
	public void setPassword(String password){this.password = password;}
	public void setName(String name){this.name = name;}
	public void setEmail(String email){this.email = email;}
	public void setDateCreated(Date created){this.created = created;}
	public void setStatus(boolean status){this.status = status;}
	public void setValid(boolean valid){this.valid = valid;}
	public void setLastLogin(Date lastLogin){this.lastLogin = lastLogin;}
	public void setResetToken(String resetToken){this.resetToken = resetToken;}

}
