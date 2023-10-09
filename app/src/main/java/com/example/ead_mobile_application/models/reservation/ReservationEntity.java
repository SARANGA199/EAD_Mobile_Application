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

    public String title;

    @ColumnInfo(name = "due_date")
    @TypeConverters({DatabaseTypeConverters.class})
    public Date dueDate;

    public ReservationStatus status;

    public static ReservationEntity fromDto(ReservationDto dto){
        ReservationEntity entity = new ReservationEntity();
        entity.id = 0; // room db will autogenerate for us after insert()
        entity.title = dto.title;
        entity.dueDate = DatabaseTypeConverters.fromTimestamp(dto.dueDate);
        entity.status = ReservationStatus.PENDING; // set default state
        return entity;
    }

    public String getDueDateAsString(){
        return dueDate == null ? null : DatabaseTypeConverters.dateToString(dueDate);
    }

}
