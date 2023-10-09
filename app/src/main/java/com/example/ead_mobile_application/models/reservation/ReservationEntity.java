package com.example.ead_mobile_application.models.reservation;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.ead_mobile_application.utilities.DatabaseTypeConverters;

import java.util.Date;

@Entity
public class ReservationEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String referenceId;

    public String nic;
    public String trainId;
    public int passengersCount;
    public double totalAmount;
    @ColumnInfo(name = "book_date")
    @TypeConverters({DatabaseTypeConverters.class})
    public Date date;
    public String departure;

    public ReservationStatus status;

    public static ReservationEntity fromDto(ReservationDto dto){
        ReservationEntity entity = new ReservationEntity();
        entity.id = 0; // room db will autogenerate for us after insert()
        entity.referenceId = dto.referenceId;
        entity.nic = dto.nic;
        entity.trainId = dto.trainId;
        entity.passengersCount = dto.passengersCount;
        entity.totalAmount = dto.totalAmount;
        entity.date = dto.date;
        entity.status = ReservationStatus.PENDING; // set default state
        return entity;
    }

    public String getDueDateAsString(){
        return date == null ? null : DatabaseTypeConverters.dateToString(date);
    }

}
