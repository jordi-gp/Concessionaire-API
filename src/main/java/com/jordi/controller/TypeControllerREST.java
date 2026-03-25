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

import com.jordi.services.ITypeService;
import com.jordi.model.Type;
import com.jordi.pojos.MsgError;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/types")
public class TypeControllerREST {
	@Autowired
	private ITypeService tService;

	@Operation(summary = "Obtén un listado de todas los tipos de vehiculos registrados")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "Tipos obtenidos con éxito",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = Type.class)
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
	public ResponseEntity<?> getAllTypes() {
		return tService.getAllTypes();
	}
	
	@GetMapping("/type/{id}")
	public ResponseEntity<?> getTypeById(@PathVariable String id) {
		return tService.getTypeById(id);
	}
	
	@PostMapping("/type/new")
	public ResponseEntity<?> addType(@RequestBody String type) {
		return tService.addType(type);
	}
	
	@PutMapping("/type/update")
	public ResponseEntity<?> updateType(@RequestBody String type) {
		return tService.addType(type);
	}
	
	@DeleteMapping("/type/remove/{id}")
	public ResponseEntity<?> removeType(@PathVariable String id) {
		return tService.deleteType(id);
	}
}
