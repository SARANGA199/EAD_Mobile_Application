package com.example.ead_mobile_application.models.login;

import com.example.ead_mobile_application.models.login.LoginRequestBody;
import com.example.ead_mobile_application.models.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("user/login")
    Call<LoginResponse> login(@Body LoginRequestBody request);
}
