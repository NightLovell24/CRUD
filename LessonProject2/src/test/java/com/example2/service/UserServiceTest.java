package com.example2.service;

import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.*;

import com.example2.dao.UserDao;
import com.example2.model.User;

public class UserServiceTest {

	@Mock
	private UserDao userDao;

	private UserService userService;
	private PasswordCryptService cryptService;

	public UserServiceTest() {
		MockitoAnnotations.initMocks(this);
		userService = new UserService(userDao);
		cryptService = new PasswordCryptService();
	}

	@Test
	public void changePassword_Should_Return_True() {
		String testLogin = "Jack";
		String testOldPassword = "Marlyn1201";
		String testNewPassword = "Farli1234";

		given(userDao.existUser(testLogin, testOldPassword)).willReturn(true);
		given(userDao.getUserByLogin(testLogin))
				.willReturn(new User(0, testLogin, cryptService.encode(testOldPassword)));

		boolean passwordChanged = userService.changePassword(testLogin, testOldPassword, testNewPassword);

		assertThat(passwordChanged).isTrue();
	}

	@Test
	public void changePassword_Should_Return_False() {
		String testLogin = "Jack";
		String testOldPassword = "Marlyn1201";
		String testNewPassword = "Farli1234";

		given(userDao.existUser(testLogin, testOldPassword)).willReturn(false);

		boolean passwordChanged = userService.changePassword(testLogin, testOldPassword, testNewPassword);

		assertThat(passwordChanged).isFalse();
	}

	@Test
	public void deleteUser_Should_Return_Null() {
		String testLogin = "Adolf";

		given(userDao.getUserByLogin(testLogin)).willReturn(new User(0, testLogin, null));
		userService.deleteUser(testLogin);
		given(userDao.getUserByLogin(testLogin)).willReturn(null);
		User user = userDao.getUserByLogin(testLogin);

		assertThat(user).isNull();
	}

	@Test
	public void loginUser_Should_Return_True() {
		String testLogin = "Jacarka45343";
		String testPassword = "Antanario1230";

		given(userDao.existUser(testLogin, testPassword)).willReturn(true);

		boolean logged = userService.loginUser(testLogin, testPassword);

		assertThat(logged).isTrue();
	}

	@Test
	public void loginUser_Should_Return_False() {
		String testLogin = "Jacarka45343";
		String testPassword = "Antanario1230";

		given(userDao.existUser(testLogin, testPassword)).willReturn(false);

		boolean logged = userService.loginUser(testLogin, testPassword);

		assertThat(logged).isFalse();
	}

	@Test
	public void registerUser_Should_Return_True() {
		String testLogin = "Jacarka45343";
		String testPassword = "Antanario1230";
		
		given(userDao.getUserByLogin(testLogin)).willReturn(null);
		
		boolean registered = userService.registerUser(testLogin, testPassword);
		
		given(userDao.getUserByLogin(testLogin)).willReturn(new User(0, testLogin, cryptService.encode(testPassword)));
		
		User user = userDao.getUserByLogin(testLogin);
		
		assertThat(registered).isTrue();
		assertThat(user).isNotNull();
	}
	@Test
	public void registerUser_Should_Return_False() {
		String testLogin = "Jacarka45343";
		String testPassword = "Antanario1230";
		
		given(userDao.getUserByLogin(testLogin)).willReturn(new User(0, testLogin, cryptService.encode(testPassword)));
		
		boolean registered = userService.registerUser(testLogin, testPassword);
		
		
		
		assertThat(registered).isFalse();
		
	}

}
