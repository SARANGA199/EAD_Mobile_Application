package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AvailableTrainList extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_available_train_list);

		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		List<TrainDetails> trainDetailsList =  new ArrayList<>();
		trainDetailsList.add(new TrainDetails("1", "Ruhunu kumari express", "Matara", "Galle", "6:00 AM", "7:00 PM", 1.0, 3, 296, 450.0));
		trainDetailsList.add(new TrainDetails("2", "Rajarata Rajini", "Matara", "Colombo", "9:30 AM", "2:30 PM", 5.0, 4, 285, 880.0));

		TrainAdapter trainAdapter = new TrainAdapter(trainDetailsList);
		recyclerView.setAdapter(trainAdapter);
	}
}