package com.example2.service;




import com.example2.dao.UserDao;
import com.example2.model.User;

public class UserService {

	private UserDao userDao;
	private PasswordCryptService cryptService;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
		cryptService = new PasswordCryptService();
	}

	public boolean changePassword(String login, String oldPassword, String newPassword) {

		if (userDao.existUser(login, oldPassword)) {
			User tempUser = userDao.getUserByLogin(login);
			String password = cryptService.encode(newPassword);
			tempUser.setPasswordHash(password);
			userDao.update(tempUser);
			return true;
		}
		return false;

	}

	public void deleteUser(String login) {
		User tempUser = userDao.getUserByLogin(login);
		userDao.delete(tempUser.getId());
	}

	public boolean loginUser(String login, String password) {
		return userDao.existUser(login, password);
	}

	public boolean registerUser(String login, String password) {
		if (userDao.getUserByLogin(login) == null) {
			String passwordHash = cryptService.encode(password);
			userDao.create(new User(0, login, passwordHash));
			return true;
		}
		return false;
	}
}
