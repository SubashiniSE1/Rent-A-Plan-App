package com.hcl.capstoneproject.RentAPlace.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.capstoneproject.RentAPlace.dao.OwnerDAO;
import com.hcl.capstoneproject.RentAPlace.model.Message;
import com.hcl.capstoneproject.RentAPlace.model.Owner;
import com.hcl.capstoneproject.RentAPlace.model.User;

@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {
	@Autowired
	OwnerDAO ownerDao;

	@Override
	public String addOwner(Owner owner) {
		ownerDao.addOwner(owner);
		return null;
	}

	@Override
	public Integer loginOwner(Owner owner) throws Exception {

		Owner ownerOrginal = ownerDao.loginOwner(owner);
		if (owner.getOwnerName().equals(ownerOrginal.getOwnerName())) {
			if (owner.getPassword().equals(ownerOrginal.getPassword())) {
				return 0;
			} else {
				throw new Exception("Invalid Data");
			}

		} else {
			throw new Exception("Invalid Data");
		}

	}

	@Override
	public String editOwner(Owner owner) {
		ownerDao.editOwner(owner);
		return null;
	}

	@Override
	public String deleteOwner(String ownerName) {
		int resp = ownerDao.deleteOwner(ownerName);
		return null;
	}

	@Override
	public List<Owner> getOwners() {

		return ownerDao.getOwner();
	}

	@Override
	public void addOwnername(String ownerName) {
		ownerDao.addOwnername(ownerName);

	}

	@Override
	public int getOwnerId() {

		return ownerDao.getOwnerId();
	}

	@Override
	public List<Message> getMessage(int ownerId) {

		return ownerDao.getMessage(ownerId);
	}

	@Override
	public int reservePlace(Message message) throws Exception {

		return ownerDao.reservePlace(message);
	}

	@Override
	public Integer message(Message message) {

		return ownerDao.message(message);
	}

	@Override
	public List<Message> getMyMessage(int ownerId) {

		return ownerDao.getMyMessage(ownerId);
	}

	@Override
	public Integer rejectMessage(Message message) {

		return ownerDao.rejectMessage(message);
	}

	@Override
	public Integer markAsReaded(Message message) {

		return ownerDao.markAsReaded(message);
	}

	@Override
	public String getMailId(Message message) {
		return ownerDao.getMailId(message);
	}

}
