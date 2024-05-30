package com.hcl.capstoneproject.RentAPlace.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.hcl.capstoneproject.RentAPlace.entity.Data1Entity;
import com.hcl.capstoneproject.RentAPlace.entity.DataPropertyEntity;
import com.hcl.capstoneproject.RentAPlace.entity.OwnerEntity;
import com.hcl.capstoneproject.RentAPlace.entity.PropertyEntity;
import com.hcl.capstoneproject.RentAPlace.entity.UserEntity;
import com.hcl.capstoneproject.RentAPlace.model.Property;
import com.hcl.capstoneproject.RentAPlace.model.User;

@Repository
public class PropertyDAOImpl implements PropertyDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int addProperty(Property property) {

		PropertyEntity proEntity = new PropertyEntity();
		proEntity.setOwnerId(property.getOwnerId());
		proEntity.setPropertyName(property.getPropertyName());
		proEntity.setPropertyType(property.getPropertyType());
		proEntity.setPropertyDesc(property.getPropertyDesc());
		proEntity.setRentAmount(property.getRentAmount());
		proEntity.setReservationStatus("UnOccupied");
		proEntity.setState(property.getState());
		proEntity.setCityName(property.getCityName());
		proEntity.setDistrict(property.getDistrict());
		proEntity.setFeature(property.getFeature());
		proEntity.setPincode(property.getPincode());
		proEntity.setStreetName(property.getStreetName());
		proEntity.setImageUrl(property.getImageUrl());
		proEntity.setRating(property.getRating());
		entityManager.persist(proEntity);

		System.out.println("persist");

		return 1;
	}

	@Override
	public int editProperty(Property property) {

		String query = " update PropertyEntity set propertyName=?1 ,propertyType=?2,feature=?3,rentAmount=?4,imageUrl=?5,rating=?6 ,propertyDesc=?7 where propertyId=?8";
		Query query1 = entityManager.createQuery(query);

		query1.setParameter(1, property.getPropertyName());
		query1.setParameter(2, property.getPropertyType());
		query1.setParameter(3, property.getFeature());
		query1.setParameter(4, property.getRentAmount());
		query1.setParameter(5, property.getImageUrl());
		query1.setParameter(6, property.getRating());
		query1.setParameter(7, property.getPropertyDesc());
		query1.setParameter(8, property.getPropertyId());
		
		System.out.println(query1);
		return query1.executeUpdate();
	}

	@Override
	public int deleteProperty(int propertyId) {

		String query = "delete from PropertyEntity where propertyId=?1";

		Query query1 = entityManager.createQuery(query);
		query1.setParameter(1, propertyId);
		return query1.executeUpdate();
	}

	@Override
	public List<Property> getProperties() {

		String query = "select pr from PropertyEntity pr  where reservationStatus=?1";
		List<Property> propertyList = new ArrayList<Property>();
		Query query1 = entityManager.createQuery(query);
		query1.setParameter(1, "UnOccupied");
		List<PropertyEntity> propertyEntityList = query1.getResultList();
		for (PropertyEntity propent : propertyEntityList) {
			Property property = new Property();
			property.setPropertyId(propent.getPropertyId());
			property.setOwnerId(propent.getOwnerId());
			property.setPropertyName(propent.getPropertyName());
			property.setPropertyType(propent.getPropertyType());
			property.setPropertyDesc(propent.getPropertyDesc());
			property.setImageUrl(propent.getImageUrl());
			property.setFeature(propent.getFeature());
			property.setRentAmount(propent.getRentAmount());
			property.setState(propent.getState());
			property.setPincode(propent.getPincode());
			property.setDistrict(propent.getDistrict());
			property.setCityName(propent.getCityName());
			property.setStreetName(propent.getStreetName());
			property.setReservationStatus(propent.getReservationStatus());
			property.setRating(propent.getRating());
			propertyList.add(property);

		}

		return propertyList;
	}

	@Override
	public List<Property> getTopRatedProperties() {

		String query = "select pr from PropertyEntity pr WHERE rating>=8";
		List<Property> propertyList = new ArrayList<Property>();
		Query query1 = entityManager.createQuery(query);
		List<PropertyEntity> propertyEntityList = query1.getResultList();
		for (PropertyEntity propent : propertyEntityList) {
			Property property = new Property();
			property.setOwnerId(propent.getOwnerId());
			property.setPropertyName(propent.getPropertyName());
			property.setPropertyType(propent.getPropertyType());
			property.setPropertyDesc(propent.getPropertyDesc());

			property.setImageUrl(propent.getImageUrl());
			property.setFeature(propent.getFeature());
			property.setRentAmount(propent.getRentAmount());
			property.setState(propent.getState());
			property.setPincode(propent.getPincode());
			property.setDistrict(propent.getDistrict());
			property.setCityName(propent.getCityName());
			property.setStreetName(propent.getStreetName());
			property.setReservationStatus(propent.getReservationStatus());
			property.setRating(propent.getRating());

			propertyList.add(property);

		}

		return propertyList;

	}

	@Override
	public int getPropertyByCategory(Property property) {
		String query = "select pr from PropertyEntity pr WHERE state=?1 AND cityName=?2 AND district=?3 AND streetName=?4 AND feature=?5 AND propertyType=?6";
		Query query1 = entityManager.createQuery(query);
		query1.setParameter(1, property.getState());
		query1.setParameter(2, property.getCityName());
		query1.setParameter(3, property.getDistrict());
		query1.setParameter(4, property.getStreetName());
		query1.setParameter(5, property.getFeature());
		query1.setParameter(6, property.getPropertyType());

		System.out.println(query1);
		return query1.executeUpdate();

	}

	@Override
	public List<Property> getPropertyByAddress(Property property) {

		String query = "select pr from PropertyEntity pr WHERE state=?1 AND cityName=?2 AND feature=?3 AND propertyType=?4 AND reservationStatus=?5";
		Query query1 = entityManager.createQuery(query);
		query1.setParameter(1, property.getState());
		query1.setParameter(2, property.getCityName());
		query1.setParameter(3, property.getFeature());
		query1.setParameter(4, property.getPropertyType());
		query1.setParameter(5, "UnOccupied");
		List<Property> propertylist = new ArrayList<Property>();
		System.out.println(query1);
		List<PropertyEntity> PropertyEntity = query1.getResultList();
		for (PropertyEntity propent : PropertyEntity) {
			Property p = new Property();
			p.setCityName(propent.getCityName());
			p.setBookedUserId(propent.getBookedUserId());
			p.setFeature(propent.getFeature());
			p.setDistrict(propent.getDistrict());
			p.setImageUrl(propent.getImageUrl());
			p.setOwnerId(propent.getOwnerId());
			p.setPincode(propent.getPincode());
			p.setPropertyDesc(propent.getPropertyDesc());
			p.setPropertyId(propent.getPropertyId());
			p.setPropertyName(propent.getPropertyName());
			p.setPropertyType(propent.getPropertyType());
			p.setRating(propent.getRating());
			p.setRentAmount(propent.getRentAmount());
			p.setReservationStatus(propent.getReservationStatus());
			p.setState(propent.getState());
			p.setStreetName(propent.getStreetName());
			propertylist.add(p);

//			System.out.println(PropertyEntity);
		}
		System.out.println(propertylist
				+ "90-----------------------------------------====0=---0=-=0-==-==0=0=0-=0[]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]][[[");
		return propertylist;
	}

	@Override
	public int getPropertyByRatings(Property property) {

		String query = "select pr from PropertyEntity pr WHERE state=?1 AND cityName=?2 AND rating=?3";
		Query query1 = entityManager.createQuery(query);
		query1.setParameter(1, property.getState());
		query1.setParameter(2, property.getCityName());
		query1.setParameter(3, property.getRating());

		System.out.println(query1);
		return query1.executeUpdate();

	}

	@Override
	public String getOwnerName() {
		String query = "select pr from Data1Entity pr WHERE id=?1";
		Query query1 = entityManager.createQuery(query);
		query1.setParameter(1, 1);
		List<Data1Entity> ownerent = query1.getResultList();
		// TODO Auto-generated method stub
		return ownerent.get(0).getOwnerName();
	}

	@Override
	public List<Property> getOwnerProperties(String ownerName) {
		String sql = "select o from OwnerEntity o where ownerName = ?1";
		Query sql1 = entityManager.createQuery(sql);
		sql1.setParameter(1, ownerName);
		System.out.println(ownerName
				+ "in my   dao =============================-========================================================");
		List<OwnerEntity> owner = sql1.getResultList();
		int id = owner.get(0).getOwnerId();
		String query = "select pr from PropertyEntity pr where ownerId=?1";
		List<Property> propertyList = new ArrayList<Property>();
		Query query1 = entityManager.createQuery(query);
		query1.setParameter(1, id);
		List<PropertyEntity> propertyEntityList = query1.getResultList();
		for (PropertyEntity propent : propertyEntityList) {
			Property property = new Property();
			property.setPropertyId(propent.getPropertyId());
			property.setOwnerId(propent.getOwnerId());
			property.setPropertyName(propent.getPropertyName());
			property.setPropertyType(propent.getPropertyType());
			property.setPropertyDesc(propent.getPropertyDesc());

			property.setImageUrl(propent.getImageUrl());
			property.setFeature(propent.getFeature());
			property.setRentAmount(propent.getRentAmount());
			property.setState(propent.getState());
			property.setPincode(propent.getPincode());
			property.setDistrict(propent.getDistrict());
			property.setCityName(propent.getCityName());
			property.setStreetName(propent.getStreetName());
			property.setReservationStatus(propent.getReservationStatus());
			property.setRating(propent.getRating());
			propertyList.add(property);

		}

		return propertyList;

	}

	@Override
	public int getOwnerID(String ownerName) {
		String sql = "select o from OwnerEntity o where ownerName = ?1";
		Query sql1 = entityManager.createQuery(sql);
		sql1.setParameter(1, ownerName);
		System.out.println(ownerName
				+ "in my   dao =============================-========================================================");
		List<OwnerEntity> owner = sql1.getResultList();
		int id = owner.get(0).getOwnerId();
		return id;
	}

	@Override
	public int setProperty(Property property) {
		DataPropertyEntity da = entityManager.find(DataPropertyEntity.class, 1);
		da.setPropertyId(property.getPropertyId());
		System.out.println(da.getPropertyId());
		System.out.println(da.getId());
		String sql = "update DataPropertyEntity set propertyId=?1 where id=?2";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, property.getPropertyId());
		int id = 1;
		query.setParameter(2, 1);
		query.executeUpdate();

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Property getProperty() {
		System.out.println("getting property=======================================================");
		String sql = "select c from DataPropertyEntity c where id =1";
		Query query = entityManager.createQuery(sql);
		List<DataPropertyEntity> li = query.getResultList();

		String sql1 = "select a from PropertyEntity a where id =?1";
		Query query1 = entityManager.createQuery(sql1);
		query1.setParameter(1, li.get(0).getPropertyId());
		List<PropertyEntity> propertyentList = query1.getResultList();
		PropertyEntity propent = propertyentList.get(0);

		System.out.println(
				"getted propertyEntity from list ++++++++++++++++++++++++++++++++++++++++++++++++++++=============================="
						+ propertyentList.get(0));
		Property pr = new Property();
		pr.setBookedUserId(propent.getBookedUserId());
		pr.setCityName(propent.getCityName());
		pr.setDistrict(propent.getDistrict());
		pr.setFeature(propent.getFeature());
		pr.setPropertyDesc(propent.getPropertyDesc());
		pr.setImageUrl(propent.getImageUrl());
		pr.setOwnerId(propent.getOwnerId());
		pr.setPincode(propent.getPincode());
		pr.setPropertyId(propent.getPropertyId());
		pr.setPropertyName(propent.getPropertyName());
		pr.setPropertyType(propent.getPropertyType());
		pr.setRating(propent.getRating());
		pr.setRentAmount(propent.getRentAmount());
		pr.setReservationStatus(propent.getReservationStatus());
		pr.setState(propent.getState());
		pr.setStreetName(propent.getStreetName());
		System.out.println(pr.getCityName() + "==============================================");
		return pr;

	}

}
