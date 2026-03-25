package com.jordi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jordi.services.IUserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserControllerREST {
	@Autowired
	private IUserService uService;
	
	@PostMapping("/user/validate")
	public ResponseEntity<?> validateUser(@RequestBody String user) {
		return uService.validateUser(user);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllUsers() {
		return uService.getAllUsers();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable String id) {
		return uService.getUserById(id);
	}
	
	@PostMapping("/user/new")
	public ResponseEntity<?> addUser(@RequestBody String user) {
		return uService.addUser(user);
	}
	
	@PutMapping("/user/update")
	public ResponseEntity<?> updateUser(@RequestBody String user) {
		return uService.updateUser(user);
	}
	
	@DeleteMapping("/user/remove/{id}")
	public ResponseEntity<?> removeUser(@PathVariable String id) {
		return uService.deleteUser(id);
	}
}
