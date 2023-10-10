package com.example.ead_mobile_application.models.login;

import androidx.room.PrimaryKey;

import com.example.ead_mobile_application.models.reservation.ReservationDto;
import com.example.ead_mobile_application.models.reservation.ReservationEntity;
import com.example.ead_mobile_application.models.reservation.ReservationStatus;

public class LoginEntity {


    @PrimaryKey(autoGenerate = true)
    public int id;
    private String nic;
    private String name;
    private String email;
    private String password;
    private boolean is_active;
    private int user_role;
    private String errorMessage;

    public static LoginEntity fromDto(LogingDto dto){
        LoginEntity entity = new LoginEntity();
        entity.id = 0; // room db will autogenerate for us after insert()
        entity.nic = dto.nic;
        entity.email = dto.name;
        entity.password = dto.password;
        entity.is_active = dto.is_active;
        entity.user_role = dto.user_role;
        entity.errorMessage = dto.errorMessage; // set default state
        return entity;
    }
}
