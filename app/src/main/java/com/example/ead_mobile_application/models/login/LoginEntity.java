package com.example.ead_mobile_application.models.login;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "login")
public class LoginEntity {

    @PrimaryKey
    @NonNull
    public String nic;
    public String name;
    public String email;
    public String password;
    public boolean is_active;
    public int user_role;
    public String token;
    public String errorMessage;

    public static LoginEntity fromDto(LoginDto dto){
        LoginEntity entity = new LoginEntity();
        entity.nic = dto.nic;
        entity.name = dto.name;
        entity.email = dto.email;
        entity.password = dto.password;
        entity.is_active = dto.is_active;
        entity.user_role = dto.user_role;
        entity.token =dto.token;
        entity.errorMessage = dto.errorMessage; // set default state
        return entity;
    }
}
