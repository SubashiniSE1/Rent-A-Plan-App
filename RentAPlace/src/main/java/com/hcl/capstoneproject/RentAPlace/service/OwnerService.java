package com.hcl.capstoneproject.RentAPlace.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.hcl.capstoneproject.RentAPlace.model.Message;
import com.hcl.capstoneproject.RentAPlace.model.Owner;

public interface OwnerService {

	public String addOwner(Owner owner);

	public Integer loginOwner(Owner owner) throws Exception;

	public String editOwner(Owner owner);

	public String deleteOwner(String ownerName);

	public List<Owner> getOwners();

	public void addOwnername(String ownerName);

	public int getOwnerId();

	public List<Message> getMessage(int i);

	public int reservePlace(Message message)
			throws ParseException, AddressException, MessagingException, IOException, Exception;

	public Integer message(Message message);

	public List<Message> getMyMessage(int ownerId);

	public Integer rejectMessage(Message message);

	public Integer markAsReaded(Message message);

	public String getMailId(Message message);
}
