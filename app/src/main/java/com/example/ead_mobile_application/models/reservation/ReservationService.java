package com.example.ead_mobile_application.models.reservation;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReservationService {

    @GET("Reservation")
    Call<ReservationResponse> getReservations();

    @POST("Reservation")
    Call<ReservationResponseBody> addReservation(@Body ReservationRequestBody request);

    //get reservations by nic number
    @GET("Reservation/nic/{nic}")
    Call<List<ReservationResponseBody>> getReservationsByNic(@Path("nic") String nic);

    //update reservation
    @PUT("Reservation/{id}")
    Call<MessageResponse> updateReservation(@Path("id") String id, @Body UpdateReservationBody request);

    //delete reservation
    @DELETE("Reservation/{id}")
    Call<MessageResponse> cancelReservation(@Path("id") String id);


}
