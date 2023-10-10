package com.example.ead_mobile_application.models.login;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ead_mobile_application.models.reservation.ReservationEntity;

import java.util.List;
@Dao
public interface LoginDao {
    @Query("SELECT * FROM LoginEntity")
    List<LoginEntity> getAll();

    @Update
    void update(LoginEntity login);

    @Query("DELETE FROM LoginEntity")
    void removeAll();
}
