package com.example.ead_mobile_application.models.register;

import com.example.ead_mobile_application.models.register.RegisterRequestBody;
import com.example.ead_mobile_application.models.register.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {

	@POST("user")
	Call<RegisterResponse> register(@Body RegisterRequestBody registerRequestBody);

}
