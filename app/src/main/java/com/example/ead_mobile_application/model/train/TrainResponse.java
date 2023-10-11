package com.example.ead_mobile_application.model.train;

public class TrainResponse {

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

	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getTripTimeDuration() {
		return tripTimeDuration;
	}

	public void setTripTimeDuration(double tripTimeDuration) {
		this.tripTimeDuration = tripTimeDuration;
	}

	public int getRequestedSeatCount() {
		return requestedSeatCount;
	}

	public void setRequestedSeatCount(int requestedSeatCount) {
		this.requestedSeatCount = requestedSeatCount;
	}

	public int getAvailableSeatCount() {
		return availableSeatCount;
	}

	public void setAvailableSeatCount(int availableSeatCount) {
		this.availableSeatCount = availableSeatCount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
