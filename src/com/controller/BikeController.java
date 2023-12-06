package com.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.entity.Bike;
import com.service.BikeService;

public class BikeController {
	private BikeService bikeService = new BikeService();
	private Scanner scanner = new Scanner(System.in);

	public void run() {
		int choice;
		do {
			System.out.println("1. Add Bike");
			System.out.println("2. View add Bikes");
			System.out.println("3. Update Bike");
			System.out.println("4. Search Bike");
			System.out.println("5. Rent Bike");
			System.out.println("6. Rental Bike List");
			System.out.println("7. Customers");
			System.out.println("8. Exit");
			System.out.print("Enter your choice: ");
			try {
				choice = scanner.nextInt();
				scanner.nextLine();
				switch (choice) {
				case 1:
					addBike();
					break;
				case 2:
					viewAddedBikes();
					break;
				case 3:
					updateBike();
					break;
				case 4:
					searchBike();
					break;
				case 5:
					rentBike();
					break;
				case 6:
					displayRentalBikeList();
					break;

				case 7:
					displayCustomers();
					break;
				case 8:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine(); // Consume the invalid input
				choice = 0; // Reset choice to continue the loop
			}
		} while (choice != 8);
	}

	private void addBike() {
		try {
			System.out.print("Enter Bike ID: ");
			String bikeId = scanner.nextLine();
			System.out.print("Enter Model Name: ");
			String modelName = scanner.nextLine();

			Bike bike = new Bike(bikeId, modelName, true);
			bikeService.addBike(bike);
			System.out.println("Bike added successfully. :)");
		} catch (Exception e) {
			System.out.println("Error adding bike: " + e.getMessage());
		}
	}

	private void viewAddedBikes() {
		try {
			List<Bike> addedBikes = bikeService.getAvailableBikes();
			if (addedBikes.isEmpty()) {
				System.out.println("No bikes added yet.");
			} else {
				System.out.println("Bike List:--");
				for (Bike bike : addedBikes) {
					System.out.println("--------------------");
					System.out.println("Bike ID: " + bike.getBikeId());
					System.out.println("Bike Model Name: " + bike.getModelName());
					System.out.println("--------------------");
				}
			}
		} catch (Exception e) {
			System.out.println("Error addes bike list: " + e.getMessage());
		}
	}

	private void updateBike() {
		try {
			System.out.print("Enter Bike ID to update: ");
			String bikeId = scanner.nextLine();
			Bike existingBike = bikeService.searchBike(bikeId);

			if (existingBike != null) {
				System.out.println("Current Bike Information:");
				System.out.println("--------------------");
				System.out.println("Bike ID: " + existingBike.getBikeId());
				System.out.println("Model Name: " + existingBike.getModelName());
				System.out.println("--------------------");
				System.out.println("Availability: " + (existingBike.isAvailable() ? "Available" : "Rented"));

				System.out.println("\nEnter Updated Information:");

				System.out.print("Enter New Model Name: ");
				String newModelName = scanner.nextLine();
				System.out.print("Is Bike Available? (true/false): ");
				boolean newAvailability = scanner.nextBoolean();

				Bike updatedBike = new Bike(bikeId, newModelName, newAvailability);
				bikeService.updateBike(bikeId, updatedBike);
				System.out.println("Bike updated successfully. :)");
			} else {
				System.out.println("Bike not found. :(");
			}
		} catch (Exception e) {
			System.out.println("Error updating bike: " + e.getMessage());
		}
	}

	private void searchBike() {
		try {
			System.out.print("Enter Bike ID to search: ");
			String bikeId = scanner.nextLine();

			Bike bike = bikeService.searchBike(bikeId);
			if (bike != null) {
				System.out.println("Bike found:");
				System.out.println("--------------------");
				System.out.println("Bike ID: " + bike.getBikeId());
				System.out.println("Model Name: " + bike.getModelName());
				System.out.println("--------------------");
				System.out.println("Availability: " + (bike.isAvailable() ? "Available" : "Rented"));
			} else {
				System.out.println("Bike not found. :(");
			}
		} catch (Exception e) {
			System.out.println("Error searching bike: " + e.getMessage());
		}
	}

	private void rentBike() {
		try {
			System.out.print("Enter Bike ID to rent: ");
			String bikeId = scanner.nextLine();
			Bike bike = bikeService.searchBike(bikeId);

			if (bike != null && bike.isAvailable()) {
				System.out.println("Enter Model Name: ");
				String modelName = scanner.nextLine();
				System.out.print("Enter Customer Details: ");
				String customerDetails = scanner.nextLine();

				bikeService.rentBike(bikeId, modelName, customerDetails);
				System.out.println("Bike rented successfully. :) ");
			} else {
				System.out.println("Bike not available for rent.. :( ");
			}
		} catch (Exception e) {
			System.out.println("Error renting bike: " + e.getMessage());
		}
	}

	private void displayRentalBikeList() {
		try {
			List<String> rentalBikeList = bikeService.getRentalBikeList();
			if (rentalBikeList.isEmpty()) {
				System.out.println("No bikes rented yet.");
			} else {
				System.out.println("Rental Bike List:");
				for (String bikeInfo : rentalBikeList) {
					System.out.println("--------------------");
					System.out.println(bikeInfo);
					System.out.println("--------------------");
				}
			}
		} catch (Exception e) {
			System.out.println("Error displaying rental bike list: " + e.getMessage());
		}
	}

	private void displayCustomers() {
		try {
			List<String> rentalBikeList = bikeService.getRentalBikeList();
			if (rentalBikeList.isEmpty()) {
				System.out.println("No bikes rented yet.");
			} else {
				System.out.println("Rented Bikes and Customers:");
				for (String rentalInfo : rentalBikeList) {
					System.out.println(rentalInfo);
				}
			}
		} catch (Exception e) {
			System.out.println("Error displaying customers: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		BikeController bikeController = new BikeController();
		bikeController.run();
	}
}
