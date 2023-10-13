package com.example.ead_mobile_application.managers;

import com.example.ead_mobile_application.models.register.RegisterRequestBody;
import com.example.ead_mobile_application.models.register.RegisterResponse;
import com.example.ead_mobile_application.models.register.RegisterService;
import com.example.ead_mobile_application.models.update.UpdateRequestBody;
import com.example.ead_mobile_application.models.update.UpdateResponse;
import com.example.ead_mobile_application.models.update.UpdateService;

import androidx.core.util.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterManager {

	private static RegisterManager singleton;

	private RegisterService registerService;

	private NetworkManager networkManager;

	private UpdateService updateService;

	public static RegisterManager getInstance() {
		if (singleton == null) {
			singleton = new RegisterManager();
		}
		return singleton;
	}

	private RegisterManager() {
		registerService = networkManager.getInstance().createService(RegisterService.class);
		updateService = networkManager.getInstance().createService(UpdateService.class);
		networkManager = NetworkManager.getInstance();
	}

	public void register(String nic, String name, String email, String password, Runnable onSuccess , Consumer<String> onError) {

		if (!networkManager.getInstance().isNetworkAvailable()) {
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

	public void updateProfile(String nic, String name, String email, String password, Runnable onSuccess , Consumer<String> onError) {

		if (!networkManager.getInstance().isNetworkAvailable()) {
			onError.accept("No internet connectivity");
			return;
		}

		UpdateRequestBody body = new UpdateRequestBody(nic, name, email, password);
		//set the nic as the path parameter and pass the body as the request body
		updateService.update(body, body.nic).enqueue(new Callback<UpdateResponse>() {
			@Override
			public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
				if (response.isSuccessful()) {
					UpdateResponse updateResponse = response.body();

					if (updateResponse != null) {
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
			public void onFailure(Call<UpdateResponse> call, Throwable t) {
				onError.accept("Something went wrong");
			}
		});

	}
}
