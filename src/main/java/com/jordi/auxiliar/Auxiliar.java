package com.jordi.auxiliar;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Auxiliar {
	public static String generateSalt(int bytes) {
		SecureRandom random = new SecureRandom();
		byte[] saltBytes = new byte[bytes];
		random.nextBytes(saltBytes);
		
		StringBuilder sb = new StringBuilder();
		for(byte b: saltBytes) {
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}
	
	public static String hash(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
			
			StringBuilder sb = new StringBuilder(digest.length * 2);
			
			for(byte b: digest) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch(NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-256 no disponible", e);
		}
	}
	
	public static Boolean isAdmin(int roleId) {
		if(roleId == 1) return true;
		return false;
	}
	
	public static Boolean isUser(int roleId) {
		if(roleId == 2) return true;
		return false;
	}
}
