package com.cloudST.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudST.model.Usuario;
import com.cloudST.repository.UsuarioRepository;
import com.cloudST.service.UsuarioService;
import com.cloudST.service.exception.UsuarioException;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
   // private static Fecha fecha;

    @Override
    public Usuario authentication(String userName, String password) throws UsuarioException {
        Usuario usuario = usuarioRepository.findByUser(userName);
        validate(password, usuario);
        return usuario;
    }

    @Override
    public Usuario findById(Integer id) {
        return usuarioRepository.findOne(id);
    }

    @Override
    public Usuario update(Integer idUser, String nombre, String email, String oldPassword, String newPassword) throws UsuarioException {
        Usuario usuario = usuarioRepository.findOne(idUser);

        usuario = validateNombreEmail(usuario,nombre,email);
        
        if(oldPassword!=""){
            if(!oldPassword.equals(newPassword)){
                throw new UsuarioException("Passwords do not match");
            }
            usuario.setPassword(newPassword);
        }
        usuarioRepository.save(usuario);
        return usuario;
    }

    @Override
	public Usuario updateAdmin(Integer idUser, String nombre, String email, String username, String valido){
		Usuario usuario = usuarioRepository.findOne(idUser);

    	usuario.setValido(valido.equals("true"));

    	if(username != usuario.getUsername()){
            usuario.setUsername(username);
        }
    
    	usuario = validateNombreEmail(usuario,nombre,email);
    	
    	usuarioRepository.save(usuario);
		return usuario;
	}

    @Override
    public Usuario create(String userName, String name, String email, String password, String password2) throws UsuarioException {
        validateDataToNewUser(userName, email, password, password2);
        Usuario usuario = new Usuario();
        usuario.setNombre(name);
        usuario.setPassword(password);
        usuario.setUsername(userName);
        usuario.setEmail(email);

        usuario.setStatus(true);
        usuario.setValido(false);

        //usuario.setFechaInicio(fecha.fechaActual());
        usuario.setFechaInicio(fechaActual());
        usuarioRepository.save(usuario);
        return usuario;
    
    }
    
    @Override
    public Usuario delete(Integer idUsuario){
    	Usuario usuario = usuarioRepository.findOne(idUsuario);
    	usuario.setStatus(false);
    	usuarioRepository.save(usuario);
    	return usuario;
    	
    }
    
    @Override
    public ArrayList<Usuario> listaUsuario(){
    	return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

   
    
    private void validateDataToNewUser(String userName, String email, String password, String password2) throws UsuarioException {
        if(!password.equals(password2)){
            throw new UsuarioException("Passwords doesn't mach");
        }

        if(usuarioRepository.findByEmail(email)!=null){
            throw new UsuarioException("The email you entered is currently in use");
        }

        if(usuarioRepository.findByUser(userName)!=null){
            throw new UsuarioException("The username you entered is currently in use");
        }
    }

    private void validate(String password, Usuario usuario) throws UsuarioException {
        if(usuario == null || usuario.getUsername()==null){
            throw new UsuarioException("User does not exist, create one.");
        }

        if(!usuario.getPassword().equals(password)){
            throw new UsuarioException("Password or invalid entered Username");
        }
    }
    private Usuario validateNombreEmail(Usuario usuario, String nombre, String email){
    	if(!nombre.equals(usuario.getNombre())){
            usuario.setNombre(nombre.toLowerCase());
        }
        if(!email.equals(usuario.getEmail())){
            usuario.setEmail(email);
            usuario.setValido(false);
        }
    	return usuario;
    }
    //
    public Date fechaActual(){
		Date fecha = new java.util.Date(); //fecha actual
		Timestamp sqlTimestamp = new Timestamp(fecha.getTime());//en milisegundos
		fecha = new Date(sqlTimestamp.getTime());
		
		return fecha;
	}
	
}
