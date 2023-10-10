package com.example.ead_mobile_application;

public class TrainDetails {

	private String trainId;
	private String trainName;
	private String departure;
	private String arrival;
	private String departureTime;
	private String arrivalTime;
	private double tripTimeDuration;
	private int requestedSeatCount;
	private int availableSeatCount;
	private double amount;

	public TrainDetails(String trainId, String trainName, String departure, String arrival, String departureTime, String arrivalTime, double tripTimeDuration, int requestedSeatCount, int availableSeatCount, double amount) {
		this.trainId = trainId;
		this.trainName = trainName;
		this.departure = departure;
		this.arrival = arrival;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.tripTimeDuration = tripTimeDuration;
		this.requestedSeatCount = requestedSeatCount;
		this.availableSeatCount = availableSeatCount;
		this.amount = amount;
	}

	public String getTrainId() {
		return trainId;
	}

	public String getTrainName() {
		return trainName;
	}

	public String getDeparture() {
		return departure;
	}

	public String getArrival() {
		return arrival;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public double getTripTimeDuration() {
		return tripTimeDuration;
	}

	public int getRequestedSeatCount() {
		return requestedSeatCount;
	}

	public int getAvailableSeatCount() {
		return availableSeatCount;
	}

	public double getAmount() {
		return amount;
	}
}
