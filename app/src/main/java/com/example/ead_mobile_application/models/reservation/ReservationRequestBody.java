package com.example.ead_mobile_application.models.reservation;

public class ReservationRequestBody {

	public String referenceId;
	public String nic;
	public String trainId;
	public int passengersCount;
	public String date;
	public double totalAmount;
	public String departure;
	public String arrival;
	public String departureTime;
	public String arrivalTime;
	public String averageTimeDuration;


	public ReservationRequestBody(String referenceId, String nic, String trainId, int passengersCount, String date,
			double totalAmount, String departure, String arrival, String departureTime, String arrivalTime,
			String averageTimeDuration) {

		this.referenceId = referenceId;
		this.nic = nic;
		this.trainId = trainId;
		this.passengersCount = passengersCount;
		this.date = date;
		this.totalAmount = totalAmount;
		this.departure = departure;
		this.arrival = arrival;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.averageTimeDuration = averageTimeDuration;
	}

}
