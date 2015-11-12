package com.lenve.httpurlconnection;

import java.io.Serializable;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String password;
	private String username;
	private String nickname;

	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Person(String password, String username, String nickname) {
		super();
		this.password = password;
		this.username = username;
		this.nickname = nickname;
	}

	public Person() {
	}

}
