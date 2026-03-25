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

import com.jordi.model.Vehicle;
import com.jordi.pojos.MsgError;
import com.jordi.services.IVehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleControllerREST {
	@Autowired
	private IVehicleService vService;
	
	@Operation(summary = "Obtén un listado de todos los vehiculos registrados")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "Vehiculos obtenidos con éxito",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = Vehicle.class)
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
	public ResponseEntity<?> getAllVehicles() {
		return vService.getAllVehicles();
	}
	
	@GetMapping("/vehicle/{id}")
	public ResponseEntity<?> getVehicleById(@PathVariable String id) {
		return vService.getVehicleById(id);
	}
	
	@PostMapping("/vehicle/new")
	public ResponseEntity<?> addVehicle(@RequestBody String vehicle) {
		return vService.addVehicle(vehicle);
	}
	
	@PutMapping("/vehicle/update")
	public ResponseEntity<?> updateVehicle(@RequestBody String vehicle) {
		return vService.addVehicle(vehicle);
	}
	
	@DeleteMapping("/vehicle/remove/{id}")
	public ResponseEntity<?> removeVehicle(@PathVariable String id) {
		return vService.deleteVehicle(id);
	}
}
