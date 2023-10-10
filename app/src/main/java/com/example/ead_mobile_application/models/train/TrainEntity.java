package com.example.ead_mobile_application.models.train;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.ead_mobile_application.models.reservation.ReservationDto;
import com.example.ead_mobile_application.models.reservation.ReservationEntity;
import com.example.ead_mobile_application.models.reservation.ReservationStatus;

import java.util.Date;

@Entity(tableName = "trains")
public class TrainEntity {

    @PrimaryKey
    @NonNull
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

    public static TrainEntity fromDto(TrainDto dto){
        TrainEntity entity = new TrainEntity();
        entity.trainId = dto.trainId;
        entity.trainName = dto.trainName;
        entity.departure = dto.departure;
        entity.arrival = dto.arrival;
        entity.departureTime = dto.departureTime;
        entity.arrivalTime = dto.arrivalTime;
        entity.tripTimeDuration = dto.tripTimeDuration;
        entity.requestedSeatCount = dto.requestedSeatCount;
        entity.availableSeatCount = dto.availableSeatCount;
        entity.amount = dto.amount;
        return entity;
    }


}

