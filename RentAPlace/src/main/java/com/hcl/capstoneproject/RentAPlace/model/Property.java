package com.hcl.capstoneproject.RentAPlace.model;

public class Property {
	
	int propertyId;
	int bookedUserId;
	int ownerId;
	String propertyName;
	String propertyDesc;
	String imageUrl;
	String propertyType;
	int rentAmount;
	String feature;
	String cityName;
	String state;
	String district;
	String pincode;
	String streetName;
	String reservationStatus;
	int rating;
	public int getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}
	public int getBookedUserId() {
		return bookedUserId;
	}
	public void setBookedUserId(int bookedUserId) {
		this.bookedUserId = bookedUserId;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public int getRentAmount() {
		return rentAmount;
	}
	public void setRentAmount(int rentAmount) {
		this.rentAmount = rentAmount;
	}
	
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getReservationStatus() {
		return reservationStatus;
	}
	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	public String getPropertyDesc() {
		return propertyDesc;
	}
	public void setPropertyDesc(String propertyDesc) {
		this.propertyDesc = propertyDesc;
	}
	@Override
	public String toString() {
		return "Property [propertyId=" + propertyId + ", bookedUserId=" + bookedUserId + ", ownerId=" + ownerId
				+ ", propertyName=" + propertyName + ", imageUrl=" + imageUrl + ", propertyType=" + propertyType
				+ ", rentAmount=" + rentAmount + ", feature=" + feature + ", cityName=" + cityName + ", state="
				+ state + ", district=" + district + ", pincode=" + pincode + ", streetName=" + streetName
				+ ", reservationStatus=" + reservationStatus + ", rating=" + rating + "]";
	}
	
	
	
	
	
	
	

}
