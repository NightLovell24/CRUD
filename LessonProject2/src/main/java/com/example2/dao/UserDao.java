package com.example2.dao;

import com.example2.model.User;


public interface UserDao extends AbstractDao<User> {

	boolean existUser(String login,String password);
	
	User getUserByLogin(String login);
}
