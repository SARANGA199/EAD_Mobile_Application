package com.example.ead_mobile_application.managers;

import com.example.ead_mobile_application.models.reservation.ReservationDto;
import com.example.ead_mobile_application.models.reservation.ReservationEntity;
import com.example.ead_mobile_application.models.reservation.ReservationService;

import java.util.ArrayList;
import java.util.List;

public class RegisterManager {

    private static RegisterManager singleton;
    private DatabaseManager databaseManager;
    private ReservationService reservationService;

    public static RegisterManager getInstance(){
        if (singleton == null)
            singleton = new RegisterManager();
        return singleton;
    }

    private RegisterManager(){
        databaseManager = DatabaseManager.getInstance();
        reservationService = NetworkManager.getInstance().createService(ReservationService.class);
    }
}
