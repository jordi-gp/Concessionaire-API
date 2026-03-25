package com.jordi.services;

import org.springframework.http.ResponseEntity;

public interface ITypeService {
	ResponseEntity<?> getAllTypes();
	ResponseEntity<?> getTypeById(String id);
	ResponseEntity<?> addType(String type);
	ResponseEntity<?> updateType(String type);
	ResponseEntity<?> deleteType(String id);
}
