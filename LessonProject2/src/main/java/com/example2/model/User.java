package com.example2.model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 143290757930935052L;
	private int id;
	private String login;
	private String passwordHash;

	public User(int id, String login, String passwordHash) {

		this.id = id;
		this.login = login;
		this.passwordHash = passwordHash;
	}

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	

}
