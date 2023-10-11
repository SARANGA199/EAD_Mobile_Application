package com.example.ead_mobile_application.models.train;

import java.util.Date;

public class TrainDto {

    public String trainId;
    public String trainName;
    public String departure;
    public String arrival;
    public Date departureTime;
    public Date arrivalTime;
    public int tripTimeDuration; // Assuming trip time duration is in minutes
    public int requestedSeatCount;
    public int availableSeatCount;
    public int amount;
}
