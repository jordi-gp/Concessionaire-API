package com.jordi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jordi.model.Role;
import com.jordi.pojos.MsgError;
import com.jordi.repository.RoleRepository;

@Service
public class IRoleServiceImp implements IRoleService {
	@Autowired
	private RoleRepository rRepository;

	@Override
	public ResponseEntity<?> getAllRoles() {
		try {
			return ResponseEntity.status(200).body(rRepository.findAll());
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor"));
		}
	}

	@Override
	public ResponseEntity<?> getRoleById(String id) {
		try {
			Integer tempId = Integer.parseInt(id);
			Role tempRole = rRepository.findById(tempId).orElse(new Role());
			
			return ResponseEntity.status(200).body(tempRole);
		} catch(NumberFormatException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error, el id no es un valor númerico"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error al obtener la marca"));
		}
	}
}
