package com.hcl.capstoneproject.RentAPlace.dao;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.hcl.capstoneproject.RentAPlace.model.Message;
import com.hcl.capstoneproject.RentAPlace.model.Owner;
import com.hcl.capstoneproject.RentAPlace.model.User;

public interface OwnerDAO {

	public int addOwner(Owner owner);

	public Owner loginOwner(Owner owner);

	public int editOwner(Owner owner);

	public int deleteOwner(String ownerName);

	public List<Owner> getOwner();

	public void addOwnername(String ownerName);

	public int getOwnerId();

	public List<Message> getMessage(int ownerId);

	public int reservePlace(Message message)
			throws ParseException, AddressException, MessagingException, IOException, Exception;

	public Integer message(Message message);

	public List<Message> getMyMessage(int ownerId);

	public Integer rejectMessage(Message message);

	public Integer markAsReaded(Message message);

	public String getMailId(Message message);
}
