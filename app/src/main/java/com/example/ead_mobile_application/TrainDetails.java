package com.example.ead_mobile_application;

import android.os.Parcel;
import android.os.Parcelable;

public class TrainDetails implements Parcelable {

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

	public String date;

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



	protected TrainDetails(Parcel in) {
		trainId = in.readString();
		trainName = in.readString();
		departure = in.readString();
		arrival = in.readString();
		departureTime = in.readString();
		arrivalTime = in.readString();
		tripTimeDuration = in.readDouble();
		requestedSeatCount = in.readInt();
		availableSeatCount = in.readInt();
		amount = in.readDouble();
	}

	public static final Creator<TrainDetails> CREATOR = new Creator<TrainDetails>() {
		@Override
		public TrainDetails createFromParcel(Parcel in) {
			return new TrainDetails(in);
		}

		@Override
		public TrainDetails[] newArray(int size) {
			return new TrainDetails[size];
		}
	};

	public String getTrainId() {
		return trainId;
	}

	public String getTrainName() {
		return trainName;
	}

	public String getDeparture() {
		return departure;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setTripTimeDuration(double tripTimeDuration) {
		this.tripTimeDuration = tripTimeDuration;
	}

	public void setRequestedSeatCount(int requestedSeatCount) {
		this.requestedSeatCount = requestedSeatCount;
	}

	public void setAvailableSeatCount(int availableSeatCount) {
		this.availableSeatCount = availableSeatCount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(trainId);
		dest.writeString(trainName);
		dest.writeString(departure);
		dest.writeString(arrival);
		dest.writeString(departureTime);
		dest.writeString(arrivalTime);
		dest.writeDouble(tripTimeDuration);
		dest.writeInt(requestedSeatCount);
		dest.writeInt(availableSeatCount);
		dest.writeDouble(amount);
	}
}