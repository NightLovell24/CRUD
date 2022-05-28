package com.example2.service;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordCryptService {

	public String encode(String password) {

		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	public boolean isCorrectPassword(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}

}
