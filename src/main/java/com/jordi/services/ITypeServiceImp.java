package com.jordi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jordi.model.Type;
import com.jordi.pojos.MsgError;
import com.jordi.repository.TypeRepository;

@Service
public class ITypeServiceImp implements ITypeService {
	@Autowired
	private TypeRepository tRepository;
	
	@Override
	public ResponseEntity<?> getAllTypes() {
		try {
			return ResponseEntity.status(200).body(tRepository.findAll());
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error al conseguir las marcas"));
		}
	}

	@Override
	public ResponseEntity<?> getTypeById(String id) {
		try {
			Integer tempId = Integer.parseInt(id);
			Type tempType = tRepository.findById(tempId).orElse(new Type());
			
			return ResponseEntity.status(200).body(tempType);
		} catch(NumberFormatException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error, el id no es un valor númerico"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error al obtener el tipo"));
		}
	}

	@Override
	public ResponseEntity<?> addType(String type) {
		try {
			Gson gs = new Gson();
			Type tempType = gs.fromJson(type, Type.class);
			
			if(tempType instanceof Type) {
				return ResponseEntity.status(201).body(tRepository.save(tempType));
			}
			
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor"));
		} catch(JsonSyntaxException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error al actualizar la marca, revisa el formato"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor al actualizar el tipo"));
		}
	}

	@Override
	public ResponseEntity<?> updateType(String type) {
		Gson gs = new Gson();
		Type tempType = gs.fromJson(type, Type.class);
		
		if(tempType instanceof Type && tempType.getId() > 0) {
			Optional<Type> existsType = tRepository.findById(tempType.getId());
			
			if(existsType.isPresent()) {
				Type updateType = existsType.get();
				updateType.setName(tempType.getName());
				
				return ResponseEntity.status(200).body(tRepository.save(updateType));
			} else {
				return ResponseEntity.status(404).body(new MsgError(404, "Tipo no encontrado"));
			}
		}
		
		return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor"));
	}

	@Override
	public ResponseEntity<?> deleteType(String id) {
		try {
			Integer tempId = Integer.parseInt(id);
			tRepository.deleteById(tempId);
			
			ResponseEntity<?> type = getTypeById(String.valueOf(tempId));
			
			Type tempType = (Type)type.getBody();
			
			if(tempType.getId() == 0) {
				return ResponseEntity.status(200).body(true);
			}
			
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor al eliminar el tipo"));
		} catch(NumberFormatException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error, el id no es un valor númerico"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error al obtener el tipo"));
		}
	}

}
