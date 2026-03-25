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

import com.jordi.model.Brand;
import com.jordi.pojos.MsgError;
import com.jordi.services.IBrandService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandControllerREST {
	@Autowired
	private IBrandService bService;
	
	@Operation(summary = "Obtén un listado de todas las marcas registradas")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "Marcas obtenidas con éxito",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = Brand.class)
			)
		),
		@ApiResponse(
			responseCode = "500",
			description = "Error interno en el servidor",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = MsgError.class)
			)
		)
	})
	@GetMapping("/")
	public ResponseEntity<?> getAllBrandsRest() {
		return bService.getAllBrands();
	}
	
	@GetMapping("/brand/{id}")
	public ResponseEntity<?> getBrandById(@PathVariable String id) {
		return bService.getBrandById(id);
	}
	
	@PostMapping("/brand/new")
	public ResponseEntity<?> addBrand(@RequestBody String brand) {
		return bService.addBrand(brand);
	}
	
	@PutMapping("/brand/update")
	public ResponseEntity<?> updateBrand(@RequestBody String brand) {
		return bService.updateBrand(brand);
	}
	
	@DeleteMapping("/brand/remove/{id}")
	public ResponseEntity<?> removeBrand(@PathVariable String id) {
		return bService.deleteBrand(id);
	}
}
