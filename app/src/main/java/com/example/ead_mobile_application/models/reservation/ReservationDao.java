package com.example.ead_mobile_application.models.reservation;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReservationDao {
    @Query("SELECT * FROM ReservationEntity")
    List<ReservationEntity> getAll();

    @Insert
    void insertAll(List<ReservationEntity> Reservations);

    @Update
    void update(ReservationEntity Reservation);

    @Query("DELETE FROM ReservationEntity")
    void removeAll();

}
