package com.example2.dao.impl;

import com.example2.dao.DaoFactory;
import com.example2.dao.UserDao;

public class UserDaoFactory implements DaoFactory {

	@Override
	public UserDao getUserDao() {
		
		return new UserDaoImpl();
	}

}
