package com.jordi.services;

import org.springframework.http.ResponseEntity;

public interface IUserService {
	ResponseEntity<?> getAllUsers();
	ResponseEntity<?> getUserById(String id);
	ResponseEntity<?> getUserByUsername(String username);
	ResponseEntity<?> addUser(String user);
	ResponseEntity<?> updateUser(String user);
	ResponseEntity<?> deleteUser(String id);
	
	ResponseEntity<?> getAllUsersSanitized();
	ResponseEntity<?> validateUser(String user);
}
