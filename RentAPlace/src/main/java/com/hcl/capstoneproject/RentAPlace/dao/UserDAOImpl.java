package com.hcl.capstoneproject.RentAPlace.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.hcl.capstoneproject.RentAPlace.entity.DataEntity;
import com.hcl.capstoneproject.RentAPlace.entity.MessageEntity;
import com.hcl.capstoneproject.RentAPlace.entity.UserEntity;
import com.hcl.capstoneproject.RentAPlace.model.Message;
import com.hcl.capstoneproject.RentAPlace.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int addUser(User user) {

		UserEntity ue = new UserEntity();
		ue.setUserName(user.getUserName());
		ue.setPassword(user.getPassword());
		ue.setEmailId(user.getEmailId());
		ue.setFirstName(user.getFirstName());
		ue.setLastName(user.getLastName());
		ue.setPhoneNo(user.getPhoneNo());
		entityManager.persist(ue);

		System.out.println("persist");

		return 1;
	}

	@Override
	public User loginUser(User users) {
		// TODO Auto-generated method stub
		String sql = " select u from UserEntity u where userName=?1";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, users.getUserName());
		List<UserEntity> userData = query.getResultList();
		User user = new User();
		user.setUserName(userData.get(0).getUserName());
		user.setPassword(userData.get(0).getPassword());

		return user;
	}

	@Override
	public int editUser(User user) {
		String query = " update UserEntity set firstName=?1 ,lastName=?2,emailId=?3,password=?4,phoneNo=?5 where userName=?6";
		Query query1 = entityManager.createQuery(query);
		query1.setParameter(1, user.getFirstName());
		query1.setParameter(2, user.getLastName());
		query1.setParameter(3, user.getEmailId());
		query1.setParameter(4, user.getPassword());
		query1.setParameter(5, user.getPhoneNo());
		query1.setParameter(6, user.getUserName());
		System.out.println(query1);
		return query1.executeUpdate();

	}

	@Override
	public int deleteUser(String userName) {

		String query = "delete from UserEntity where userName=?1";

		Query query1 = entityManager.createQuery(query);
		query1.setParameter(1, userName);
		return query1.executeUpdate();
	}

	@Override
	public List<User> getUser() {
		String query = "select ue from UserEntity ue";
		List<User> userList = new ArrayList<User>();
		Query query1 = entityManager.createQuery(query);
		List<UserEntity> userEntityList = query1.getResultList();
		for (UserEntity userent : userEntityList) {
			User user = new User();
			user.setEmailId(userent.getEmailId());
			user.setFirstName(userent.getFirstName());
			user.setLastName(userent.getLastName());
			user.setPassword(userent.getPassword());
			user.setPhoneNo(userent.getPhoneNo());
			user.setUserName(userent.getUserName());
			userList.add(user);

		}

		return userList;
	}

	@Override
	public void addUsername(String userName) {
		System.out.println("adding username +++++++++++++++++++++++++++++++++++++++++!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		DataEntity da = entityManager.find(DataEntity.class, 1);
		da.setUserName(userName);
		System.out.println(da.getUserName());
		System.out.println(da.getId());
		String sql = "update DataEntity set userName=?1 where id=?2";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, userName);
		int id = 1;
		query.setParameter(2, id);
		System.out.println(query.executeUpdate() + "updated username in database " + userName);
	}

	@Override
	public int reserveProperty(Message message) {
		System.out.println("DAO Reserve +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		String sql = "select c from DataEntity c where id =1";
		Query query = entityManager.createQuery(sql);
		List<DataEntity> dentitys = query.getResultList();
		System.out.println("result getted");
		DataEntity de = dentitys.get(0);
		String UserName = de.getUserName();
		// getting userId
		String sql1 = "Select c from UserEntity c where userName=?1";
		Query query1 = entityManager.createQuery(sql1);
		query1.setParameter(1, UserName);

		List<UserEntity> userentities = query1.getResultList();
		System.out.println(UserName);
		System.out.println(userentities);
		UserEntity userent = userentities.get(0);
		message.setUserId(userent.getUserId());
		MessageEntity ue = new MessageEntity();
		ue.setNoOfPersons(message.getNoOfPersons());
		ue.setPropertyId(message.getPropertyId());
		ue.setCheckInDate(message.getCheckInDate());
		ue.setCheckOutDate(message.getCheckOutDate());
		ue.setRentAmount(message.getRentAmount());
		ue.setMessage(message.getMessage());
		ue.setMessageType(message.getMessageType());
		ue.setOwnerId(message.getOwnerId());
		ue.setUserId(message.getUserId());
		ue.setMessageStatus("active");
		entityManager.persist(ue);
		return 0;
	}

}
