package com.jordi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jordi.model.Vehicle;
import com.jordi.pojos.MsgError;
import com.jordi.repository.VehicleRepository;

@Service
public class IVehicleServiceImp implements IVehicleService {
	@Autowired
	private VehicleRepository vRepository;

	@Override
	public ResponseEntity<?> getAllVehicles() {
		try {
			return ResponseEntity.status(200).body(vRepository.findAll());
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error al conseguir los vehículos"));
		}
	}

	@Override
	public ResponseEntity<?> getVehicleById(String id) {
		try {
			Integer tempId = Integer.parseInt(id);
			Vehicle tempVehicle = vRepository.findById(tempId).orElse(new Vehicle());
			
			return ResponseEntity.status(200).body(tempVehicle);
		} catch(NumberFormatException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error, el id no es un valor númerico"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error al obtener el vehículo"));
		}
	}

	@Override
	public ResponseEntity<?> addVehicle(String vehicle) {
		try {
			Gson gs = new Gson();
			Vehicle tempVehicle = gs.fromJson(vehicle, Vehicle.class);
			
			
			
			if(tempVehicle instanceof Vehicle) {
				return ResponseEntity.status(201).body(vRepository.save(tempVehicle));
			}
			
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor"));
		} catch(JsonSyntaxException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error al crear el vehículo, revisa el formato"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor al crear el vehículo"));
		}
	}

	@Override
	public ResponseEntity<?> updateVehicle(String vehicle) {
		try {
			Gson gs = new Gson();
			Vehicle tempVehicle = gs.fromJson(vehicle, Vehicle.class);
			
			if(tempVehicle instanceof Vehicle) {
				return ResponseEntity.status(200).body(vRepository.save(tempVehicle));
			}
			
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor"));
		} catch(NumberFormatException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error al crear el vehículo, revisa el formato"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor al actualizar el vehículo"));
		}
	}

	@Override
	public ResponseEntity<?> deleteVehicle(String id) {
		try {
			Integer tempId = Integer.parseInt(id);
			vRepository.deleteById(tempId);
			
			ResponseEntity<?> vehicle = getVehicleById(String.valueOf(tempId));
			
			Vehicle tempVehicle = (Vehicle)vehicle.getBody();
			
			if(tempVehicle.getId() == 0) {
				return ResponseEntity.status(200).body(true);
			}
			
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor al eliminar el vehículo"));
		} catch(NumberFormatException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error, el id no es un valor númerico"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error al eliminar el vehículo"));
		}
	}
}
