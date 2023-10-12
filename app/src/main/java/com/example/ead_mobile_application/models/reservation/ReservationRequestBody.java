package com.example.ead_mobile_application.models.reservation;

public class ReservationRequestBody {

	public String referenceId;
	public String nic;
	public String trainId;
	public int passengersCount;
	public String date;
	public double totalAmount;
	public String depature;
	public String arrival;
	public String depatureTime;
	public String arrivalTime;
	public String averageTimeDuration;


	public ReservationRequestBody(String referenceId, String nic, String trainId, int passengersCount, String date,
			double totalAmount, String depature, String arrival, String depatureTime, String arrivalTime,
			String averageTimeDuration) {

		this.referenceId = referenceId;
		this.nic = nic;
		this.trainId = trainId;
		this.passengersCount = passengersCount;
		this.date = date;
		this.totalAmount = totalAmount;
		this.depature = depature;
		this.arrival = arrival;
		this.depatureTime = depatureTime;
		this.arrivalTime = arrivalTime;
		this.averageTimeDuration = averageTimeDuration;
	}

}
