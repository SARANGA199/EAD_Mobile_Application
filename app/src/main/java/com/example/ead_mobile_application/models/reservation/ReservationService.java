package com.example.ead_mobile_application.models.reservation;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ReservationService {
    @GET("tasks")
    Call<ReservationResponse> tasks();

}
