package com.jordi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jordi.auxiliar.Auxiliar;
import com.jordi.components.JwtUtil;
import com.jordi.model.User;
import com.jordi.pojos.MsgError;
import com.jordi.repository.UserRepository;

@Service
public class IUserServiceImp implements IUserService {
	@Autowired
	private UserRepository uRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public ResponseEntity<?> getAllUsers() {
		try {
			List<User> userList = uRepository.findAll();
			
			if(!userList.isEmpty()) return ResponseEntity.status(200).body(userList);
			return ResponseEntity.status(204).build();
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error al conseguir los usuarios"));
		}
	}

	@Override
	public ResponseEntity<?> getUserById(String id) {
		try {
			Integer tempId = Integer.parseInt(id);
			User tempUser = uRepository.findById(tempId).orElse(new User());
			
			return ResponseEntity.status(200).body(tempUser);
		} catch(NumberFormatException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error, el id no es un valor númerico"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error al obtener la marca"));
		}
	}

	@Override
	public ResponseEntity<?> getUserByUsername(String username) {
		try {
			User tempUser = uRepository.findByUsername(username);
			return ResponseEntity.status(200).body(tempUser);
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error al obtener la marca"));
		}
	}

	@Override
	public ResponseEntity<?> addUser(String user) {
		try {
			Gson gs = new Gson();
			User tempUser = gs.fromJson(user, User.class);
			
			if(tempUser instanceof User) {
				return ResponseEntity.status(201).body(uRepository.save(tempUser));
			}
			
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor"));
		} catch(JsonSyntaxException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error al insertar el usuario, revisa el formato"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor al insertar el usuario"));
		}
	}

	@Override
	public ResponseEntity<?> updateUser(String user) {
		try {
			Gson gs = new Gson();
			User tempUser = gs.fromJson(user, User.class);
			
			if(tempUser instanceof User) {
				return ResponseEntity.status(201).body(uRepository.save(tempUser));
			}
			
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor"));
		} catch(JsonSyntaxException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error al insertar el usuario, revisa el formato"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor al insertar el usuario"));
		}
	}

	@Override
	public ResponseEntity<?> deleteUser(String id) {
		try {
			Integer tempId = Integer.parseInt(id);
			uRepository.deleteById(tempId);
			
			ResponseEntity<?> user = getUserById(String.valueOf(tempId));
			User tempUser = (User)user.getBody();
			
			if(tempUser.getId() == 0) return ResponseEntity.status(200).body(true);
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor al eliminar el usuario"));
		} catch(NumberFormatException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error, el id no es un valor númerico"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor al eliminar el usuario"));
		}
	}

	@Override
	public ResponseEntity<?> getAllUsersSanitized() {
		try {
			return ResponseEntity.status(200).body(uRepository.findAllSanitized());
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error al obtener todos los usuarios"));
		}
	}

	@Override
	public ResponseEntity<?> validateUser(String user) {
		try {
			Gson gs = new Gson();
			User jsonUser = gs.fromJson(user, User.class);
			User tempUser = uRepository.findByUsername(jsonUser.getUsername());
			
			if(tempUser != null) {
				String tempPass = jsonUser.getPassword()+tempUser.getSalt();
				String tempHashedPassword = Auxiliar.hash(tempPass);
				Integer tempUserRoleId = tempUser.getRole().getId();
				
				if(tempHashedPassword.equalsIgnoreCase(tempUser.getPassword())
						&& Auxiliar.isAdmin(tempUserRoleId) || Auxiliar.isUser(tempUserRoleId)) {	
					String token = jwtUtil.generateToken(tempUser.getUsername(),  tempUser.getRole().getName());
					return ResponseEntity.status(200).body(token);
				} else {
					return ResponseEntity.status(500).body(new MsgError(500,"Contraseña incorrecta"));
				}
			} else {
				return ResponseEntity.status(500).body(new MsgError(500,"Usuario no encontrado"));
			}
			
		} catch (NumberFormatException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error, mal formateado"));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error al obtener El usuario"));
		}
	}

}
