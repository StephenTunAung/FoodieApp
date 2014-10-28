package com.keyit.bean;

import java.io.Serializable;

public class AdminUser implements Serializable {

	private static final long serialVersionUID = -7286623187856504577L;
	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
