package com.example.ead_mobile_application.models.reservation;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reservations")
public class ReservationEntity {

    @PrimaryKey
    @NonNull
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

    public static ReservationEntity fromDto(ReservationResponseBody dto){
        ReservationEntity entity = new ReservationEntity();
        entity.id = dto.id;
        entity.referenceId = dto.referenceId;
        entity.nic = dto.nic;
        entity.trainId = dto.trainId;
        entity.passengersCount = dto.passengersCount;
        entity.totalAmount = dto.totalAmount;
        entity.date = dto.date;
        entity.averageTimeDuration = dto.averageTimeDuration; // set default state
        return entity;
    }

//    public String getDueDateAsString(){
//        return date == null ? null : DatabaseTypeConverters.dateToString(date);
//    }

}
