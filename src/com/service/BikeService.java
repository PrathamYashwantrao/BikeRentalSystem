package com.service;

import com.dao.BikeDAO;
import com.entity.Bike;

import java.util.List;

public class BikeService {
	private BikeDAO bikeDAO = new BikeDAO();

	public void addBike(Bike bike) {
		bikeDAO.addBike(bike);
	}

	public Bike searchBike(String bikeId) {
		return bikeDAO.searchBike(bikeId);
	}

	public List<Bike> getAvailableBikes() {
		return bikeDAO.getAvailableBikes();
	}

	public void rentBike(String bikeId, String modelName, String customerDetails) {
		bikeDAO.rentBike(bikeId, modelName, customerDetails);
	}

	public List<String> getRentalBikeList() {
		return bikeDAO.getRentalBikeList();
	}

	public void updateBike(String bikeId, Bike updatedBike) {
		bikeDAO.updateBike(bikeId, updatedBike);
	}

}
