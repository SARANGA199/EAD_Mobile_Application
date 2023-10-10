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

    public static ReservationManager getInstance(){
        if (singleton == null)
            singleton = new ReservationManager();
        return singleton;
    }

    private ReservationManager(){
        databaseManager = DatabaseManager.getInstance();
        reservationService = NetworkManager.getInstance().createService(ReservationService.class);
    }

    private void saveServerReservations(List<ReservationDto> reservations){
        databaseManager.db().ReservationDao().removeAll();

        List<ReservationEntity> tempEntities = new ArrayList<>();
        for(ReservationDto t : reservations){
            tempEntities.add(ReservationEntity.fromDto(t));
        }

        databaseManager.db().ReservationDao().insertAll(tempEntities);
    }
}
