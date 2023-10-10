package com.example.ead_mobile_application.models.train;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ead_mobile_application.models.reservation.ReservationEntity;

import java.util.List;

public interface TrainDao {
    @Query("SELECT * FROM TrainEntity")
    List<TrainEntity> getAll();

    @Insert
    void insertAll(List<TrainEntity> Trains);

    @Update
    void update(TrainEntity Trains);

    @Query("DELETE FROM TrainEntity")
    void removeAll();
}
