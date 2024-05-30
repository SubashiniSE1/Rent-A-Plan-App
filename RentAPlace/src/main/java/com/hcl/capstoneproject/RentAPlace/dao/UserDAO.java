package com.hcl.capstoneproject.RentAPlace.dao;

import java.util.List;

import com.hcl.capstoneproject.RentAPlace.model.Message;
import com.hcl.capstoneproject.RentAPlace.model.User;

public interface UserDAO {

	public int addUser(User user);

	public User loginUser(User users);

	public int editUser(User user);

	public int deleteUser(String userName);

	public List<User> getUser();

	public void addUsername(String userName);

	public int reserveProperty(Message message);
}
