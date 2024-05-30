package com.hcl.capstoneproject.RentAPlace.service;

import java.util.List;

import com.hcl.capstoneproject.RentAPlace.model.Message;
import com.hcl.capstoneproject.RentAPlace.model.User;

public interface UserService {

	public String addUser(User users);

	public Integer loginUser(User users) throws Exception;

	public String editUser(User users);

	public String deleteUser(String userName);

	public List<User> getUsers();

	public void addUsername(String userName);

	public int reserveProperty(Message message);

	public int getUserId();
}
