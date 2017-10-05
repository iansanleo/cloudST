package com.cloudST.service;

import java.util.ArrayList;

import com.cloudST.model.User;
import com.cloudST.service.exception.UserException;

public interface UserService {

    User authentication(String userName, String password) throws UserException;

    User findById(Integer id);

    User update(Integer id, String name, String email, String oldPassword, String newPassword) throws UserException;

    User updateAdmin(Integer idUser, String name,String email, String username, String valid)throws UserException;
    
    User create(String userName, String name, String email, String password, String password2) throws UserException;
    
    User delete(Integer idUser);
    
    User save(User user);
    
    ArrayList<User> listUser();
    
    User findUserByEmail(String email);
   
    User findUserByResetToken(String resetToken);

	User findByUsername(String username);
    
    
}
