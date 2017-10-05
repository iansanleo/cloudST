package com.cloudST.service.impl;

import com.cloudST.model.User;
import com.cloudST.repository.UserRepository;
import com.cloudST.service.UserService;
import com.cloudST.service.exception.UserException;
import com.cloudST.utiles.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    

    @Override
    public User authentication(String userName, String password) throws UserException {
        User user = userRepository.findByUser(userName);
        validate(password, user);
        user.setLastLogin(DateUtils.actualDate());
        userRepository.save(user);
        return user;
    }

    @Override
    public User findById(Integer idUser) {
        return userRepository.findOne(idUser);
    }
    
    @Override
    public User findByUsername(String username){
    	return userRepository.findByUser(username);
    }

    @Override
    public User update(Integer idUser, String name, String email, String oldPassword, String newPassword) throws UserException {
        User user = userRepository.findOne(idUser);

        user = validateNombreEmail(user,name,email);
        
        if(oldPassword!=""){
            if(!oldPassword.equals(newPassword)){
                throw new UserException("Passwords do not match");
            }
            user.setPassword(newPassword);
        }
        userRepository.save(user);
        return user;
    }

    @Override
	public User updateAdmin(Integer idUser, String name, String email, String username, String valid){
		User user = userRepository.findOne(idUser);

    	user.setValid(valid.equals("true"));

    	if(username != user.getUsername()){
            user.setUsername(username);
        }
    
    	user = validateNombreEmail(user,name,email);
    	
    	userRepository.save(user);
		return user;
	}

    @Override
    public User create(String userName, String name, String email, String password, String password2) throws UserException {
        validateDataToNewUser(userName, email, password, password2);
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setUsername(userName);
        user.setEmail(email);

        user.setStatus(true);
        user.setValid(false);

        user.setDateCreated(DateUtils.actualDate());
        userRepository.save(user);
        return user;
    
    }
    
    @Override
    public User delete(Integer idUser){
    	User user = userRepository.findOne(idUser);
    	user.setStatus(false);
    	userRepository.save(user);
    	return user;
    }
    
    @Override
    public ArrayList<User> listUser(){
    	return (ArrayList<User>) userRepository.findAll();
    }

    @Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findUserByResetToken(String resetToken) {
		return userRepository.findByResetToken(resetToken);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
    
	
    private void validateDataToNewUser(String userName, String email, String password, String password2) throws UserException {
        if(!password.equals(password2)){
            throw new UserException("Passwords doesn't mach");
        }

        if(userRepository.findByEmail(email)!=null){
            throw new UserException("The email you entered is currently in use");
        }

        if(userRepository.findByUser(userName)!=null){
            throw new UserException("The username you entered is currently in use");
        }
    }

    private void validate(String password, User user) throws UserException {
        if(user == null || user.getUsername()==null){
            throw new UserException("User does not exist, create one.");
        }

        if(!user.getPassword().equals(password)){
            throw new UserException("Password or invalid entered Username");
        }
    }
    
    private User validateNombreEmail(User user, String name, String email){
    	if(!name.equals(user.getName())){
            user.setName(name.toLowerCase());
        }
        if(!email.equals(user.getEmail())){
            user.setEmail(email);
            user.setValid(false);
        }
    	return user;
    }	
}
