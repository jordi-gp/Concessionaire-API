package com.jordi.services;

import org.springframework.http.ResponseEntity;

public interface IRoleService {
	ResponseEntity<?> getAllRoles();
	ResponseEntity<?> getRoleById(String id);
}
