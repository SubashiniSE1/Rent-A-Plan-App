package com.hcl.capstoneproject.RentAPlace.model;

public class Data1 {
	
	int id;
	int ownerName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(int ownerName) {
		this.ownerName = ownerName;
	}
	@Override
	public String toString() {
		return "Data1 [id=" + id + ", ownerName=" + ownerName + "]";
	}
	
	

}
