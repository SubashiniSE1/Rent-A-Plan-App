package com.hcl.capstoneproject.RentAPlace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.capstoneproject.RentAPlace.dao.UserDAO;
import com.hcl.capstoneproject.RentAPlace.model.Message;
import com.hcl.capstoneproject.RentAPlace.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired

	UserDAO userDao;

	@Override
	public String addUser(User users) {
		userDao.addUser(users);
		return null;
	}

	@Override
	public Integer loginUser(User users) throws Exception {

		User userOrginal = userDao.loginUser(users);
		if (users.getUserName().equals(userOrginal.getUserName())) {
			if (users.getPassword().equals(userOrginal.getPassword())) {
				return 0;
			} else {
				throw new Exception("Invalid Data");
			}

		} else {
			throw new Exception("Invalid Data");
		}

	}

	@Override
	public String editUser(User users) {
		userDao.editUser(users);

		return null;
	}

	@Override
	public String deleteUser(String userName) {
		int resp = userDao.deleteUser(userName);
		return null;
	}

	@Override
	public List<User> getUsers() {
		return userDao.getUser();
	}

	@Override
	public void addUsername(String userName) {
		userDao.addUsername(userName);

	}

	@Override
	public int reserveProperty(Message message) {

		return userDao.reserveProperty(message);
	}

	@Override
	public int getUserId() {

		return 0;
	}

}
