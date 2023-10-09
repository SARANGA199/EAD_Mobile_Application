package com.example.ead_mobile_application.models.reservation;

public enum ReservationStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED;

    public static ReservationStatus fromInteger(int status){
        switch(status){
            case 1: return ReservationStatus.IN_PROGRESS;
            case 2: return ReservationStatus.COMPLETED;
            default: return ReservationStatus.PENDING;
        }
    }

    public int toInteger(){
        switch(this){
            case IN_PROGRESS: return 1;
            case COMPLETED: return 2;
            default: return 0;
        }
    }

}
