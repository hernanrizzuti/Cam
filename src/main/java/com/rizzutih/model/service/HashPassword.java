package com.rizzutih.model.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class HashPassword {

	/**
	 * Encrypt a string
	 * @param password	String to encrypt
	 * @return			Encrypted string
	 * @throws NoSuchAlgorithmException
	 */

	public void setPassword(String password){
	}

	public String encrypt(String password){
		byte buf[] = password.getBytes();
		byte[] input;
		MessageDigest algorithm = null;
		try {
			algorithm = MessageDigest.getInstance("SHA-1");
			algorithm.reset();
			algorithm.update(buf);
			input = algorithm.digest();
			password = byteToBase64(input);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return password;
	}

	public String byteToBase64(byte[] data){
		return DatatypeConverter.printBase64Binary(data);
	}

	public static void main(String[] args) {
		System.out.println(new HashPassword().encrypt("54637"));
	}

}
