package com.dao;

import com.entity.Bike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BikeDAO {
	private Map<String, Bike> bikeMap = new HashMap<>();
	private List<String> rentalBikeList = new ArrayList<>();

	public void addBike(Bike bike) {
		bikeMap.put(bike.getBikeId(), bike);
	}

	public Bike searchBike(String bikeId) {
		return bikeMap.get(bikeId);
	}

	public List<Bike> getAvailableBikes() {
		List<Bike> availableBikes = new ArrayList<>();
		for (Bike bike : bikeMap.values()) {
			if (bike.isAvailable()) {
				availableBikes.add(bike);
			}
		}
		return availableBikes;
	}

	public void rentBike(String bikeId, String modelName, String customerDetails) {
		Bike bike = bikeMap.get(bikeId);
		if (bike != null && bike.isAvailable()) {
			bike.setAvailable(false);
			bike.setRentedBy(customerDetails);
			rentalBikeList.add(bikeId + " (" + modelName + ") - Rented by " + customerDetails);
		}
	}

	public List<String> getRentalBikeList() {
		return rentalBikeList;
	}

	public void updateBike(String bikeId, Bike updatedBike) {
		if (bikeMap.containsKey(bikeId)) {
			bikeMap.put(bikeId, updatedBike);
		}
	}
}
