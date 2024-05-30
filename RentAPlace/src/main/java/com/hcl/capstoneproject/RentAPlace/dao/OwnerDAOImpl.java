package com.hcl.capstoneproject.RentAPlace.dao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.hcl.capstoneproject.RentAPlace.entity.Data1Entity;
import com.hcl.capstoneproject.RentAPlace.entity.DataEntity;
import com.hcl.capstoneproject.RentAPlace.entity.MessageEntity;
import com.hcl.capstoneproject.RentAPlace.entity.OwnerEntity;
import com.hcl.capstoneproject.RentAPlace.entity.ReservationEntity;
import com.hcl.capstoneproject.RentAPlace.entity.UserEntity;
import com.hcl.capstoneproject.RentAPlace.model.Message;
import com.hcl.capstoneproject.RentAPlace.model.Owner;
import com.hcl.capstoneproject.RentAPlace.model.User;

@Repository
public class OwnerDAOImpl implements OwnerDAO {

	@PersistenceContext
	private EntityManager entityManager;
	List<Integer> oddMonth = new ArrayList<Integer>();

	@Override
	public int addOwner(Owner owner) {
		OwnerEntity ue = new OwnerEntity();
		ue.setOwnerName(owner.getOwnerName());
		ue.setPassword(owner.getPassword());
		ue.setEmailId(owner.getEmailId());
		ue.setPhoneNo(owner.getPhoneNo());
		entityManager.persist(ue);

		System.out.println("persist");

		return 1;
	}

	@Override
	public Owner loginOwner(Owner owner) {

		String sql = " select u from OwnerEntity u where ownerName=?1";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, owner.getOwnerName());
		List<OwnerEntity> ownerData = query.getResultList();
		Owner owner1 = new Owner();
		owner1.setOwnerName(ownerData.get(0).getOwnerName());
		owner1.setPassword(ownerData.get(0).getPassword());

		return owner1;
	}

	@Override
	public int editOwner(Owner owner) {

		String query = " update OwnerEntity set emailId=?1,password=?2,phoneNo=?3 where ownerName=?4";
		Query query1 = entityManager.createQuery(query);

		query1.setParameter(1, owner.getEmailId());
		query1.setParameter(2, owner.getPassword());
		query1.setParameter(3, owner.getPhoneNo());
		query1.setParameter(4, owner.getOwnerName());
		System.out.println(query1);
		return query1.executeUpdate();
	}

	@Override
	public int deleteOwner(String ownerName) {

		String query = "delete from OwnerEntity where ownerName=?1";

		Query query1 = entityManager.createQuery(query);
		query1.setParameter(1, ownerName);
		return query1.executeUpdate();
	}

	@Override
	public List<Owner> getOwner() {

		String query = "select o from OwnerEntity o";
		List<Owner> ownerList = new ArrayList<Owner>();
		Query query1 = entityManager.createQuery(query);
		List<OwnerEntity> ownerEntityList = query1.getResultList();
		for (OwnerEntity ownerent : ownerEntityList) {
			Owner owner = new Owner();
			owner.setEmailId(ownerent.getEmailId());
			owner.setPassword(ownerent.getPassword());
			owner.setPhoneNo(ownerent.getPhoneNo());
			owner.setOwnerName(ownerent.getOwnerName());
			ownerList.add(owner);

		}

		return ownerList;
	}

	@Override
	public void addOwnername(String ownerName) {
		Data1Entity da = entityManager.find(Data1Entity.class, 1);
		da.setOwnerName(ownerName);
		System.out.println(da.getOwnerName());
		System.out.println(da.getId());
		String sql = "update Data1Entity set ownerName=?1 where id=?2";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, ownerName);
		int id = 1;
		query.setParameter(2, 1);
		query.executeUpdate();
	}

	@Override
	public int getOwnerId() {
		// TODO Auto-generated method stub
		System.out.println("DAO Reserve +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		we need to get userId  from db because its null 
		String sql = "select c from Data1Entity c where id =1";
		Query query = entityManager.createQuery(sql);
		List<Data1Entity> dentitys = query.getResultList();
		System.out.println("result getted");
		Data1Entity de = dentitys.get(0);
		String OwnerName = de.getOwnerName();
		// getting userId
		String sql1 = "Select c from OwnerEntity c where ownerName=?1";
		Query query1 = entityManager.createQuery(sql1);
		query1.setParameter(1, OwnerName);

		List<OwnerEntity> userentities = query1.getResultList();
		System.out.println(OwnerName);
		System.out.println(userentities);
		OwnerEntity userent = userentities.get(0);
		return userent.getOwnerId();
	}

	@Override
	public List<Message> getMessage(int ownerId) {
		String sql = "select c from MessageEntity c where ownerId = ?1 and messageStatus = ?2 and messageType=?3";
		Query q = entityManager.createQuery(sql);
		List<Message> modelMessage = new ArrayList<Message>();
		q.setParameter(1, ownerId);
		q.setParameter(2, "active");
		q.setParameter(3, "Reserve");
		List<MessageEntity> messages = q.getResultList();
		for (MessageEntity msg : messages) {
			Message m = new Message();
			m.setMessageId(msg.getMessageId());
			m.setCheckInDate(msg.getCheckInDate());
			m.setCheckOutDate(msg.getCheckOutDate());
			m.setMessage(msg.getMessage());
			m.setMessageType(msg.getMessageType());
			m.setNoOfPersons(msg.getNoOfPersons());
			m.setOwnerId(msg.getOwnerId());
			m.setPropertyId(msg.getPropertyId());
			m.setUserId(msg.getUserId());
			m.setRentAmount(msg.getRentAmount());
			m.setMessageStatus(msg.getMessageStatus());
			modelMessage.add(m);

			System.out.println(msg.getRentAmount()
					+ "tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");
		}
		// TODO Auto-generated method stub
		return modelMessage;
	}

	@Override
	public int reservePlace(Message message) throws Exception {
		// calculate no of days
		long diff = 0;
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		String inputString1 = message.getCheckInDate();
		String inputString2 = message.getCheckOutDate();

		try {
			Date date1 = myFormat.parse(inputString1);
			Date date2 = myFormat.parse(inputString2);
			diff = date2.getTime() - date1.getTime();

		} catch (ParseException e) {
			e.printStackTrace();
		}
		ReservationEntity reserveent = new ReservationEntity();
		reserveent.setCheckInDate(message.getCheckInDate());
		reserveent.setCheckOutDate(message.getCheckOutDate());
		reserveent.setMessageType(message.getMessageType());
		reserveent.setNoOfGuests(message.getNoOfPersons());
		reserveent.setRentAmount(message.getRentAmount());
		reserveent.setTotalRent((int) ((Integer) message.getRentAmount() * diff));
		reserveent.setPropertyId(message.getPropertyId());
		reserveent.setOwnerId(message.getOwnerId());
		reserveent.setUserId(message.getUserId());
		entityManager.persist(reserveent);

		System.out.println("persist");

		String query = "update PropertyEntity set bookedUserId=?1 , reservationStatus=?2  where propertyId=?3";
		Query query1 = entityManager.createQuery(query);
		query1.setParameter(1, message.getUserId());
		query1.setParameter(2, "Occupied");
		query1.setParameter(3, message.getPropertyId());

		System.out.println(query);

		String query2 = "update MessageEntity set messageStatus=?1  where messageId=?2";
		Query query3 = entityManager.createQuery(query2);
		query3.setParameter(1, "accepted");
		query3.setParameter(2, message.getMessageId());
		query3.executeUpdate();
//		System.out.println("before object creation=========================================================================================================");
//		MailSender mailSender =new MailSender();
//		System.out.println("after object creation=========================================================================================================");
//		mailSender.sendEmail();
		return query1.executeUpdate();

	}

	@Override
	public Integer message(Message message) {
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
		message.setUserId(userent.getUserId()); // finally userId is obtained from database and setted in message

		MessageEntity ue = new MessageEntity();

		ue.setPropertyId(message.getPropertyId());

		ue.setRentAmount(message.getRentAmount());
		ue.setMessage(message.getMessage());
		ue.setMessageType(message.getMessageType());
		ue.setOwnerId(message.getOwnerId());
		ue.setUserId(message.getUserId());
		ue.setMessageStatus("active");
		entityManager.persist(ue);

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Message> getMyMessage(int ownerId) {
		System.out.println("get normal message ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		String sql = "select c from MessageEntity c where ownerId = ?1 and messageStatus = ?2 and messageType=?3";
		Query q = entityManager.createQuery(sql);
		List<Message> modelMessage = new ArrayList<Message>();
		q.setParameter(1, ownerId);
		q.setParameter(2, "active");
		q.setParameter(3, "Normal");
		List<MessageEntity> messages = q.getResultList();
		for (MessageEntity msg : messages) {
			Message m = new Message();
			m.setMessageId(msg.getMessageId());
			m.setCheckInDate(msg.getCheckInDate());
			m.setCheckOutDate(msg.getCheckOutDate());
			m.setMessage(msg.getMessage());
			m.setMessageType(msg.getMessageType());
			m.setNoOfPersons(msg.getNoOfPersons());
			m.setOwnerId(msg.getOwnerId());
			m.setPropertyId(msg.getPropertyId());
			m.setUserId(msg.getUserId());
			m.setRentAmount(msg.getRentAmount());
			m.setMessageStatus(msg.getMessageStatus());
			modelMessage.add(m);

			System.out.println(msg.getRentAmount()
					+ "tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");
		}
		// TODO Auto-generated method stub
		return modelMessage;
	}

	@Override
	public Integer rejectMessage(Message message) {
		// TODO Auto-generated method stub
		String query2 = "update MessageEntity set messageStatus=?1  where messageId=?2";
		Query query3 = entityManager.createQuery(query2);
		query3.setParameter(1, "reject");
		query3.setParameter(2, message.getMessageId());
		return query3.executeUpdate();

	}

	@Override
	public Integer markAsReaded(Message message) {
		// normal message marked as readed
		// TODO Auto-generated method stub
		String query2 = "update MessageEntity set messageStatus=?1  where messageId=?2";
		Query query3 = entityManager.createQuery(query2);
		query3.setParameter(1, "markAsReaded");
		query3.setParameter(2, message.getMessageId());
		return query3.executeUpdate();

	}

	@Override
	public String getMailId(Message message) {
		UserEntity ue = entityManager.find(UserEntity.class, message.getUserId());
		return ue.getEmailId();

	}

}
