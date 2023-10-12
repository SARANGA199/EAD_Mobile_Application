package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AvailableTrainList extends AppCompatActivity {

	ArrayList<TrainDetails> trainResponse;
	private String reserveDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_available_train_list);

		Intent intent = getIntent();
		if (intent != null) {
			reserveDate = intent.getStringExtra("date");
			trainResponse = getIntent().getParcelableArrayListExtra("trainResponse");

			//loop through the trainResponse
			trainResponse.forEach(trainDetails -> {
				System.out.println(trainDetails.getTrainName());
				//convert the arrival time and departure time to only hours and minutes
				String arrivalTime = trainDetails.getArrivalTime();
				String departureTime = trainDetails.getDepartureTime();
				String arrivalTimeHours = arrivalTime.substring(0, arrivalTime.indexOf(":"));
				String arrivalTimeMinutes = arrivalTime.substring(arrivalTime.indexOf(":") + 1, arrivalTime.indexOf(" "));
				String departureTimeHours = departureTime.substring(0, departureTime.indexOf(":"));
				String departureTimeMinutes = departureTime.substring(departureTime.indexOf(":") + 1, departureTime.indexOf(" "));
				//set the arrival time and departure time to the new format
				trainDetails.setArrivalTime(arrivalTimeHours + ":" + arrivalTimeMinutes);
				trainDetails.setDepartureTime(departureTimeHours + ":" + departureTimeMinutes);

				trainDetails.setDate(reserveDate);
			});

		}




		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		List<TrainDetails> trainDetailsList =  new ArrayList<>();
		trainDetailsList.add(new TrainDetails("1", "Ruhunu kumari express", "Matara", "Galle", "6:00 AM", "7:00 PM", 1.0, 3, 296, 450.0));
		trainDetailsList.add(new TrainDetails("2", "Rajarata Rajini", "Matara", "Colombo", "9:30 AM", "2:30 PM", 5.0, 4, 285, 880.0));

		TrainAdapter trainAdapter = new TrainAdapter(trainResponse);
		recyclerView.setAdapter(trainAdapter);
	}
}