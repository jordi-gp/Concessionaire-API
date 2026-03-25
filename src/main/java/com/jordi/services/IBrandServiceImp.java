package com.jordi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jordi.model.Brand;
import com.jordi.pojos.MsgError;
import com.jordi.repository.BrandRepository;

@Service
public class IBrandServiceImp implements IBrandService {
	@Autowired
	private BrandRepository bRepository;

	@Override
	public ResponseEntity<?> getAllBrands() {
		try {
			List<Brand> brandList = bRepository.findAll();
			
			if(!brandList.isEmpty()) return ResponseEntity.status(200).body(brandList);
			return ResponseEntity.status(204).build();
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error al conseguir las marcas"));
		}
	}

	@Override
	public ResponseEntity<?> getBrandById(String id) {
		try {
			Integer tempId = Integer.parseInt(id);
			Brand tempBrand = bRepository.findById(tempId).orElse(new Brand());
			
			return ResponseEntity.status(200).body(tempBrand);
		} catch(NumberFormatException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error, el id no es un valor númerico"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error al obtener la marca"));
		}
	}

	@Override
	public ResponseEntity<?> addBrand(String brand) {
		try {
			Gson gs = new Gson();
			Brand tempBrand = gs.fromJson(brand, Brand.class);
			
			if(tempBrand instanceof Brand) {
				return ResponseEntity.status(200).body(bRepository.save(tempBrand));
			}

			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor"));
		} catch(JsonSyntaxException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error al insertar la marca, revisa el formato"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor al insertar la marca"));
		}
	}

	@Override
	public ResponseEntity<?> updateBrand(String brand) {
		try {
			Gson gs = new Gson();
			Brand tempBrand = gs.fromJson(brand, Brand.class);
			
			if(tempBrand instanceof Brand) {
				return ResponseEntity.status(200).body(bRepository.save(tempBrand));
			}
			
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor"));
		} catch(JsonSyntaxException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error al actualizar la marca, revisa el formato"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor al actualizar la marca"));
		}
	}

	@Override
	public ResponseEntity<?> deleteBrand(String id) {
		try {
			Integer tempId = Integer.parseInt(id);
			bRepository.deleteById(tempId);
			
			ResponseEntity<?> brand = getBrandById(String.valueOf(tempId));
		
			Brand tempBrand = (Brand)brand.getBody();
			
			if(tempBrand.getId() == 0) {
				return ResponseEntity.status(200).body(true);
			}
			
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor al eliminar la marca"));
		} catch(NumberFormatException e) {
			return ResponseEntity.status(400).body(new MsgError(400, "Error, el id no es un valor númerico"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(new MsgError(500, "Error interno del servidor al eliminar la marca"));
		}
	}
}
