package com.hcl.capstoneproject.RentAPlace.model;

import javax.persistence.Entity;
import javax.persistence.Table;


public class UserData {
	
	
	int id;
	int userName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserName() {
		return userName;
	}
	public void setUserName(int userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "UserData [id=" + id + ", userName=" + userName + "]";
	}
	
	

}
