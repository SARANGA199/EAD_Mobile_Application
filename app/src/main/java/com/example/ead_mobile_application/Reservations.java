package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Reservations extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservations);

		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		List<ReservationDetails> reservationDetailsList =  new ArrayList<>();
		reservationDetailsList.add(new ReservationDetails("1", "001M", "990584718V","85741",4, "2023-10-01", "Matara", "Galle", "6:00 AM", "7:00 PM", "1.0 Hours", 750.0));
		reservationDetailsList.add(new ReservationDetails("2", "002M", "990542618V","82441",4, "2023-10-01", "Matara", "Colombo", "6:00 AM", "9:00 PM", "3.0 Hours", 3250.0));

		ReservationAdapter reservationAdapter = new ReservationAdapter(reservationDetailsList);
		recyclerView.setAdapter(reservationAdapter);
	}
}