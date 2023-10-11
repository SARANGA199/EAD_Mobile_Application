package com.example.ead_mobile_application.model.train;

public class TrainRequestBody {

	public String departure;
	public String arrival;
	public int seatCount;
	public String date;

	public TrainRequestBody(String departure, String arrival, int seatCount, String date) {
		this.departure = departure;
		this.arrival = arrival;
		this.seatCount = seatCount;
		this.date = date;
	}
}
