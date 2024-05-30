package com.hcl.capstoneproject.RentAPlace.dao;

import java.util.List;

import com.hcl.capstoneproject.RentAPlace.model.Property;
import com.hcl.capstoneproject.RentAPlace.model.User;

public interface PropertyDAO {

	public int addProperty(Property property);

	public int editProperty(Property property);

	public int deleteProperty(int propertyId);

	public List<Property> getProperties();

	public List<Property> getTopRatedProperties();

	public int getPropertyByCategory(Property property);

	public List<Property> getPropertyByAddress(Property property);

	public int getPropertyByRatings(Property property);

	public String getOwnerName();

	public List<Property> getOwnerProperties(String ownerName);

	public int getOwnerID(String ownerName);

	public int setProperty(Property property);

	public Property getProperty();

}
