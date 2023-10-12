package com.example.ead_mobile_application.managers;

import com.example.ead_mobile_application.TrainDetails;
import com.example.ead_mobile_application.models.train.TrainRequestBody;
import com.example.ead_mobile_application.models.train.TrainService;
import androidx.core.util.Consumer;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

public class TrainManager {

	private static TrainManager singleton;

	private TrainService trainService;

	public static TrainManager getInstance() {
		if (singleton == null) {
			singleton = new TrainManager();
		}
		return singleton;
	}

	private TrainManager() {
		trainService = NetworkManager.getInstance().createService(TrainService.class);
	}

	public void searchTrain(String departure, String arrival, int seatCount, String date, Consumer<List<TrainDetails>> onSuccess , Consumer<String> onError) {

		if (!NetworkManager.getInstance().isNetworkAvailable()) {
			onError.accept("No internet connectivity");
			return;
		}

		TrainRequestBody body = new TrainRequestBody(departure, arrival, seatCount, date);

		trainService.searchTrain(body).enqueue(new Callback<List<TrainDetails>>() {
			@Override
			public void onResponse(Call<List<TrainDetails>> call, Response<List<TrainDetails>> response) {
				if (response.isSuccessful()) {
					List<TrainDetails> trainResponse = response.body();
					onSuccess.accept(trainResponse);
					return;

				} else {
					onError.accept("Error on response");
				}
			}

			@Override
			public void onFailure(Call<List<TrainDetails>> call, Throwable t) {
				onError.accept("Something went wrong");
			}
		});

	}

}