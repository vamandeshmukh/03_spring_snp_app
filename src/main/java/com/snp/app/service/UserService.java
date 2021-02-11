package com.snp.app.service;

import java.util.List;
import java.util.Map;

import javax.security.sasl.AuthenticationException;

import com.snp.app.dtos.Users;

public interface UserService {

	String updateUser(String userId, Users userToUpdate);

	String deleteUser(String userId);

	String registerNewUser(Users userToRegister);
	
	List<Users> findAllUsers();
	
	Users findUserById(String userId);
	
	List<Users> findUserByEmail(String email);
	
	String updateUserPhotoId(Users userToUpdate);
	
	Map<String, Object> authenticateUser(Users userToLogin) throws AuthenticationException;
}
