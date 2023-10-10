package com.example.ead_mobile_application.model.register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {

	@POST("user")
	Call<RegisterResponse> register(@Body RegisterRequestBody registerRequestBody);

}
