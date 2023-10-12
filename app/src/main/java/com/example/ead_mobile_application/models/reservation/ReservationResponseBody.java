package com.example.ead_mobile_application.models.reservation;

import java.io.Serializable;

public class ReservationResponseBody  implements Serializable {
	public String id;
	public String referenceId;

	public String nic;
	public String trainId;
	public int passengersCount;
	public String date;
	public String depature;
	public String arrival;
	public String depatureTime;
	public String arrivalTime;
	public String averageTimeDuration;
	public double totalAmount;
	public String createdAt;
	public String updatedAt;

	public ReservationResponseBody(String id, String referenceId, String nic, String trainId, int passengersCount,
			String date, String depature, String arrival, String depatureTime, String arrivalTime,
			String averageTimeDuration, double totalAmount, String createdAt, String updatedAt) {
		this.id = id;
		this.referenceId = referenceId;
		this.nic = nic;
		this.trainId = trainId;
		this.passengersCount = passengersCount;
		this.date = date;
		this.depature = depature;
		this.arrival = arrival;
		this.depatureTime = depatureTime;
		this.arrivalTime = arrivalTime;
		this.averageTimeDuration = averageTimeDuration;
		this.totalAmount = totalAmount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getId() {
		return id;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public String getNic() {
		return nic;
	}

	public String getTrainId() {
		return trainId;
	}

	public int getPassengersCount() {
		return passengersCount;
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

	public String getDepatureTime() {
		return depatureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public String getAverageTimeDuration() {
		return averageTimeDuration;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}
}
