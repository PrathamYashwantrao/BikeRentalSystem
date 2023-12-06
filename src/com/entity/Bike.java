package com.entity;

public class Bike {
	private String bikeId;
	private String modelName;
	private boolean isAvailable;
	private String rentedBy;

	public Bike(String bikeId, String modelName, boolean isAvailable) {
		this.bikeId = bikeId;
		this.modelName = modelName;
		this.isAvailable = isAvailable;
		this.rentedBy = null;
	}

	public String getBikeId() {
		return bikeId;
	}

	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	public String getRentedBy() {
		return rentedBy;
	}

	public void setRentedBy(String rentedBy) {
		this.rentedBy = rentedBy;
	}
}
