package com.example.ead_mobile_application.models.reservation;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ReservationService {
    Gson gson = new Gson();
    @GET("Reservation")
    Call<ReservationResponse> getReservations();
}
