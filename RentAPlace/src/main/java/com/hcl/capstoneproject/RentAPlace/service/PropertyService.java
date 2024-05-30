package com.hcl.capstoneproject.RentAPlace.service;

import java.util.List;

import com.hcl.capstoneproject.RentAPlace.model.Property;
import com.hcl.capstoneproject.RentAPlace.model.User;

public interface PropertyService {

	public String addProperty(Property property);

	public int editProperty(Property property);

	public String deleteProperty(int propertyId);

	public List<Property> getProperties();

	public List<Property> getOwnerProperties(String OwnerName);

	public List<Property> getTopRatedProperties();

	public int getPropertyByCategory(Property property);

	public List<Property> getPropertyByAddress(Property property);

	public int getPropertyByRatings(Property property);

	public String getOwnerName();

	public int getOwnerID(String ownerName);

	public int setProperty(Property property);

	public Property getProperty();

}
