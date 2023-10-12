package com.example.ead_mobile_application.models.reservation;

import java.util.List;

public class ReservationResponse {
    private List<ReservationDto> reservations;
    private boolean success;

    public List<ReservationDto> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
