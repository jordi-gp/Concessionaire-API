package com.jordi.services;

import org.springframework.http.ResponseEntity;

public interface IBrandService {
	ResponseEntity<?> getAllBrands();
	ResponseEntity<?> getBrandById(String id);
	ResponseEntity<?> addBrand(String brand);
	ResponseEntity<?> updateBrand(String brand);
	ResponseEntity<?> deleteBrand(String id);
}
