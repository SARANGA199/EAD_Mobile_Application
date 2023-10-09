package com.example.ead_mobile_application.models.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.ead_mobile_application.models.reservation.ReservationDao;
import com.example.ead_mobile_application.models.reservation.ReservationEntity;
import com.example.ead_mobile_application.utilities.DatabaseTypeConverters;

@Database(entities = {ReservationEntity.class}, version = 1)
@TypeConverters({DatabaseTypeConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReservationDao ReservationDao();
}

