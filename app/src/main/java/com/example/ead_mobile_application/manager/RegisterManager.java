package com.example.ead_mobile_application.manager;

import com.example.ead_mobile_application.model.register.RegisterRequestBody;
import com.example.ead_mobile_application.model.register.RegisterResponse;
import com.example.ead_mobile_application.model.register.RegisterService;
import androidx.core.util.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
public class RegisterManager {

	private static RegisterManager singleton;

	private RegisterService registerService;

	public static RegisterManager getInstance() {
		if (singleton == null) {
			singleton = new RegisterManager();
		}
		return singleton;
	}

	private RegisterManager() {
		registerService = NetworkManager.getInstance().createService(RegisterService.class);
	}

	public void register(String nic, String name, String email, String password, Runnable onSuccess , Consumer<String> onError) {

		if (!NetworkManager.getInstance().isNetworkAvailable()) {
			onError.accept("No internet connectivity");
			return;
		}

		RegisterRequestBody body = new RegisterRequestBody(nic, name, email, password);
		registerService.register(body).enqueue(new Callback<RegisterResponse>() {
			@Override
			public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
				if (response.isSuccessful()) {
					RegisterResponse registerResponse = response.body();

					if (registerResponse != null) {
						onSuccess.run();
					}else{
						onError.accept("Invalid credentials");
						return;
					}

				} else {
					onError.accept("Invalid credentials");
				}
			}

			@Override
			public void onFailure(Call<RegisterResponse> call, Throwable t) {
				onError.accept("Something went wrong");
			}
		});
	}

}
