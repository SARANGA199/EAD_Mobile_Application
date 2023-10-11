package com.example.ead_mobile_application.models.login;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface LoginDao {
    @Query("SELECT * FROM login")
    LoginEntity get();

    @Insert
    void insert(LoginEntity login);

    @Update
    void update(LoginEntity login);

    @Query("DELETE FROM login")
    void removeAll();
}
