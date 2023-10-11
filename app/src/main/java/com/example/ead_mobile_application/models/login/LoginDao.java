package com.example.ead_mobile_application.models.login;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LoginDao {
    @Query("SELECT * FROM login")
    List<LoginEntity> get();

    //get the user and limit it to 1
    @Query("SELECT * FROM login LIMIT 1")
    LoginEntity getOne();

    @Insert
    void insert(LoginEntity login);

    @Update
    void update(LoginEntity login);

    @Query("DELETE FROM login")
    void removeAll();
}
