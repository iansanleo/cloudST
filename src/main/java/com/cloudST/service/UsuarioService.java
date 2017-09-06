package com.cloudST.service;

import com.cloudST.model.Usuario;
import com.cloudST.service.exception.UsuarioException;

public interface UsuarioService {

    Usuario authentication(String userName, String password) throws UsuarioException;

    Usuario findById(Integer id);

    Usuario update(Integer id, String name, String email, String oldPassword, String newPassword) throws UsuarioException;

    Usuario create(String userName, String name, String email, String password, String password2) throws UsuarioException;
}
