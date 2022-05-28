package com.example2.user_prototype;

import com.example2.model.User;
import com.example2.service.PasswordCryptService;

public class UserPrototype {

	private static PasswordCryptService cryptService;
	private static final String password = "test_password";
	
	static
	{
		cryptService = new PasswordCryptService();
	}
	
	public static User getUser()
	{
		return new User(0, "test_user", cryptService.encode(password));
	}
	public static String getPassword()
	{
		return password;
	}
}
