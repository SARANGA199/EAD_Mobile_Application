package com.example.ead_mobile_application.model.login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("user/login")
    Call<LoginResponse> login(@Body LoginRequestBody request);
}
