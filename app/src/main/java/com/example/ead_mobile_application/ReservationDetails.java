package com.example.ead_mobile_application;

public class ReservationDetails {
    private String _id;
	private String reference_id;
	private String nic;
	private String train_id;
	private int passengers_count;
	private String date;
	private String depature;
	private String arrival;
	private String departure_time;
	private String arrival_time;
	private String average_time_duration;
	private double amount;

	public ReservationDetails(String _id,String reference_id, String nic, String train_id, int passengers_count, String date, String depature, String arrival, String departure_time, String arrival_time, String average_time_duration, double amount) {
		this._id = _id;
		this.reference_id = reference_id;
		this.nic = nic;
		this.train_id = train_id;
		this.passengers_count = passengers_count;
		this.date = date;
		this.depature = depature;
		this.arrival = arrival;
		this.departure_time = departure_time;
		this.arrival_time = arrival_time;
		this.average_time_duration = average_time_duration;
		this.amount = amount;
	}

	public String get_id() {
		return _id;
	}
	public String getReference_id() {
		return reference_id;
	}

	public String getNic() {
		return nic;
	}

	public String getTrain_id() {
		return train_id;
	}

	public int getPassengers_count() {
		return passengers_count;
	}

	public String getDate() {
		return date;
	}

	public String getDepature() {
		return depature;
	}

	public String getArrival() {
		return arrival;
	}

	public String getDeparture_time() {
		return departure_time;
	}

	public String getArrival_time() {
		return arrival_time;
	}

	public String getAverage_time_duration() {
		return average_time_duration;
	}

	public double getAmount() {
		return amount;
	}
}
