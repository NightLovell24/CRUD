package com.example2.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.example2.dao.DatabaseConnection;
import com.example2.dao.UserDao;
import com.example2.model.User;
import com.example2.service.PasswordCryptService;


public class UserDaoImpl implements UserDao {

	private static final String CREATE_QUERY = "INSERT INTO USERS(login, password_hash) VALUES(?, ?);";
	private static final String READ_QUERY = "SELECT * FROM USERS WHERE id = ?;";
	private static final String UPDATE_QUERY = "UPDATE USERS" + " SET login = ?, password_hash = ? WHERE id = ?;";
	private static final String DELETE_QUERY = "DELETE FROM USERS WHERE id = ?;";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM USERS;";
	private static final String GET_USER_BY_LOGIN = "SELECT * FROM USERS WHERE login = ?;";
	
	private PasswordCryptService cryptService;
	
	public UserDaoImpl()
	{
		cryptService = new PasswordCryptService();
	}
	

	@Override
	public void create(User t) {
		try {
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(CREATE_QUERY);
			preparedStatement.setString(1, t.getLogin());
			preparedStatement.setString(2, t.getPasswordHash());
			preparedStatement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public User read(int id) {
		User user = null;
		try {
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(READ_QUERY);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String login = resultSet.getString("login");
				String passwordHash = resultSet.getString("password_hash");
				user = new User(id, login, passwordHash);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void update(User t) {
		try {
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, t.getLogin());
			preparedStatement.setString(2, t.getPasswordHash());
			preparedStatement.setInt(3, t.getId());
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		try {
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_QUERY);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		try {
			Statement statement = DatabaseConnection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String login = resultSet.getString("login");
				String passwordHash = resultSet.getString("password_hash");
				users.add(new User(id, login, passwordHash));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public boolean existUser(String login, String password) {
		boolean exist = false;
	
		try {
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_USER_BY_LOGIN);
			preparedStatement.setString(1, login);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next())
			{
				String passwordHash = set.getString("password_hash");
				
				exist = cryptService.isCorrectPassword(password, passwordHash);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return exist;
	}

	@Override
	public User getUserByLogin(String login) {
		User user = null;
		
		try {
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_USER_BY_LOGIN);
			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				int id = resultSet.getInt("id");
				String passwordHash = resultSet.getString("password_hash");
				user = new User(id, login, passwordHash);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

}
