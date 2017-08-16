package com.cloudST.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cloudST.model.Usuario;

public interface UsuarioMapper {
	
	@Results({
		@Result(property = "idUsuario", column = "idUsuario"),
		@Result(property = "username", column ="username"),
		@Result(property = "password", column ="password"),
		@Result(property = "nombre", column ="nombre"),
		@Result(property = "email", column ="email"),
		@Result(property = "fechaInicio", column ="fecha_inicio"),
		@Result(property = "status", column ="status")
		
	})
	
	
	@Insert("INSERT into usuario (idUsuario,username,password,nombre,email,"
			+ "fecha_inicio,status)"
			+ "VALUES(#{idUsuario},#{password},#{nombre},#{email},"
			+ "#{fechaInicio},#{status})")
	void insertUsuario(Usuario usuario);
	
	@Select("SELECT idUsuario,username,password,nombre,email,"
			+ "fecha_inicio,status "
			+ "form usuario "
			+ "WHERE idUsuario = #(idUsuario)")
	Usuario selectUsuario(int idUsuario);
	
	@Update ("UPDATE usuario"
			+ "SET idUsuario = #{idUsuario}, password =#{password},nombre = #{nombre},"
			+ "email = #{email}, fecha_inicio#{fechaInicio},status = #{status}")
	void updateUsuario(Usuario usuario);
	
	@Delete("DELETE FROM usuario WHERE idUsuario = #{idUsuario}")
	void deleteUsuario(int idUsuario);
}