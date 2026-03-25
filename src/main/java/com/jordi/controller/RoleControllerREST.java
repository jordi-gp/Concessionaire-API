package com.jordi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jordi.services.IRoleService;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleControllerREST {
	@Autowired
	private IRoleService rService;

	@GetMapping("/")
	public ResponseEntity<?> getAllUsers() {
		return rService.getAllRoles();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable String id) {
		return rService.getRoleById(id);
	}
}
