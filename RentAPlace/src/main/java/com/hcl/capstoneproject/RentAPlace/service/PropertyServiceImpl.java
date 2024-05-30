package com.hcl.capstoneproject.RentAPlace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.capstoneproject.RentAPlace.dao.PropertyDAO;
import com.hcl.capstoneproject.RentAPlace.model.Property;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {
	@Autowired
	PropertyDAO propertyDao;

	@Override
	public String addProperty(Property property) {
		propertyDao.addProperty(property);
		return null;
	}

	@Override
	public int editProperty(Property property) {
		return propertyDao.editProperty(property);

	}

	@Override
	public String deleteProperty(int propertyId) {
		int resp = propertyDao.deleteProperty(propertyId);
		return null;
	}

	@Override
	public List<Property> getProperties() {

		return propertyDao.getProperties();
	}

	@Override
	public List<Property> getTopRatedProperties() {

		return propertyDao.getTopRatedProperties();
	}

	@Override
	public int getPropertyByCategory(Property property) {

		return propertyDao.getPropertyByCategory(property);
	}

	@Override
	public List<Property> getPropertyByAddress(Property property) {
		return propertyDao.getPropertyByAddress(property);
	}

	@Override
	public int getPropertyByRatings(Property property) {
		return propertyDao.getPropertyByRatings(property);
	}

	@Override
	public String getOwnerName() {

		return propertyDao.getOwnerName();
	}

	@Override
	public List<Property> getOwnerProperties(String OwnerName) {

		return propertyDao.getOwnerProperties(OwnerName);
	}

	@Override
	public int getOwnerID(String ownerName) {
		int id = propertyDao.getOwnerID(ownerName);

		return id;
	}

	@Override
	public int setProperty(Property property) {

		int res = propertyDao.setProperty(property);
		return res;
	}

	@Override
	public Property getProperty() {
		return propertyDao.getProperty();

	}
}
