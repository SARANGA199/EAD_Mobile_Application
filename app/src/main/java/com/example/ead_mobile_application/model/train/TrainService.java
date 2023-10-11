package com.example.ead_mobile_application.model.train;

import com.example.ead_mobile_application.TrainDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TrainService {

	@POST("Train/search")
	Call<List<TrainDetails>> searchTrain(@Body TrainRequestBody trainRequestBody);

}
