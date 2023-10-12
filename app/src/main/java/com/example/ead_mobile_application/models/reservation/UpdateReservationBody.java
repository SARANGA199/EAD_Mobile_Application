package com.example.ead_mobile_application.models.reservation;

public class UpdateReservationBody {

	public int passengersCount;
	public String date;
	public String depature;
	public String arrival;


	public UpdateReservationBody(int passengersCount, String date, String depature, String arrival ) {
		this.passengersCount = passengersCount;
		this.date = date;
		this.depature = depature;
		this.arrival = arrival;
	}
}
