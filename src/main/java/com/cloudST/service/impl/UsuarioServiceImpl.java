package com.cloudST.service.impl;

import com.cloudST.model.Usuario;
import com.cloudST.repository.UsuarioRepository;
import com.cloudST.service.UsuarioService;
import com.cloudST.service.exception.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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
    public Usuario update(Integer id, String nombre, String email, String oldPassword, String newPassword) throws UsuarioException {
        Usuario usuario = usuarioRepository.findOne(id);
        if(nombre != usuario.getNombre()){
            usuario.setNombre(nombre);
        }
        if(email != usuario.getEmail()){
            usuario.setEmail(email);
            usuario.setValido(false);
        }

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
    public Usuario create(String userName, String name, String email, String password, String password2) throws UsuarioException {
        Usuario usuario = new Usuario();
        if(!password.equals(password2)){
            throw new UsuarioException("Passwords doesn't mach");
        }

        if(usuarioRepository.findByEmail(email)!=null){
            throw new UsuarioException("The email you entered is currently in use");
        }

        if(usuarioRepository.findByUser(userName)!=null){
            throw new UsuarioException("The username you entered is currently in use");
        }

        usuario.setNombre(name);
        usuario.setPassword(password);
        usuario.setUsername(userName);
        usuario.setEmail(email);

        usuario.setStatus(true);
        usuario.setValido(false);

        Date fechaInicio = new java.util.Date(); //fecha actual
        Timestamp sqlTimestamp = new Timestamp(fechaInicio.getTime());//en milisegundos
        fechaInicio = new Date(sqlTimestamp.getTime());
        usuario.setFechaInicio(fechaInicio);
        usuarioRepository.save(usuario);
        return usuario;
    }

    private void validate(String password, Usuario usuario) throws UsuarioException {
        if(usuario == null || usuario.getUsername()==null){
            throw new UsuarioException("User does not exist, create one.");
        }

        if(!usuario.getPassword().equals(password)){
            throw new UsuarioException("Password or invalid entered Username");
        }
    }
}
