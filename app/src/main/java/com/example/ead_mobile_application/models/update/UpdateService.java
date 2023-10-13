package com.example.ead_mobile_application.models.update;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UpdateService {
    @PUT("user/{nic}")
    Call<UpdateResponse> update(@Body UpdateRequestBody updateRequestBody, @Path("nic") String nic);
}
