package com.example2.dao.impl;


import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example2.dao.UserDao;
import com.example2.model.User;
import com.example2.user_prototype.UserPrototype;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.*;

public class UserDaoTest {

	@Mock
	private static UserDao dao;
	private static User testUser;

	public UserDaoTest()
	{
		MockitoAnnotations.initMocks(this);
		testUser = UserPrototype.getUser();
	}
	

	@Test
	public void getUserByLogin_Should_Return_Not_Null() {
		String testLogin = testUser.getLogin();

		given(dao.getUserByLogin(testLogin)).willReturn(testUser);
		
		User user = dao.getUserByLogin(testLogin);
		String userName = user.getLogin();

		assertThat(user).isNotNull();
		assertThat(userName).isEqualTo(testLogin);

	}

	@Test
	public void getUserByLogin_Should_Return_Null() {
		String testLogin = "Jacky278";

		given(dao.getUserByLogin(testLogin)).willReturn(null);
		
		User user = dao.getUserByLogin(testLogin);

		assertThat(user).isNull();
	}

	@Test
	public void existUser_Should_Return_True() {
		String testLogin = testUser.getLogin();
		String testPassword = UserPrototype.getPassword();

		given(dao.existUser(testLogin, testPassword)).willReturn(true);
		
		boolean exist = dao.existUser(testLogin, testPassword);
		
		

		assertThat(exist).isTrue();
	}

	@Test
	public void existUser_Should_Return_False() {
		String testLogin = testUser.getLogin();
		String testPassword = "duckydo_228";

		given(dao.existUser(testLogin, testPassword)).willReturn(false);
		
		boolean exist = dao.existUser(testLogin, testPassword);

		assertThat(exist).isFalse();
	}

}
