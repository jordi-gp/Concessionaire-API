package com.jordi.services;

import org.springframework.http.ResponseEntity;

public interface IVehicleService {
	ResponseEntity<?> getAllVehicles();
	ResponseEntity<?> getVehicleById(String id);
	ResponseEntity<?> addVehicle(String vehicle);
	ResponseEntity<?> updateVehicle(String vehicle);
	ResponseEntity<?> deleteVehicle(String id);
}
