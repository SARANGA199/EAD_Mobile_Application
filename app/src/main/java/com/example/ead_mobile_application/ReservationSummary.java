package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReservationSummary extends AppCompatActivity {

	private TextView trainName;
	private TextView departure;
	private TextView arrival;
	private TextView departureTime;
	private TextView arrivalTime;
	private TextView tripTimeDuration;
	private TextView requestedSeatCount;

	private TextView amount;
	private TextView date;
	private Button checkout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservation_summary);

		trainName = findViewById(R.id.trainName);
		departure = findViewById(R.id.departure);
		arrival = findViewById(R.id.arrival);
		departureTime = findViewById(R.id.departureTime);
		arrivalTime = findViewById(R.id.arrivalTime);
		tripTimeDuration = findViewById(R.id.tripTimeDuration);
		requestedSeatCount = findViewById(R.id.requestedSeatCount);
		date = findViewById(R.id.date);
		amount = findViewById(R.id.amount);
		checkout = findViewById(R.id.btn_summary_train);


		// Get the intent that started this activity
		Intent intent = getIntent();

		// Retrieve data from the intent using the keys
		String trainName = intent.getStringExtra("trainName");
		String departure = intent.getStringExtra("departure");
		String arrival = intent.getStringExtra("arrival");
		String departureTime = intent.getStringExtra("departureTime");
		String arrivalTime = intent.getStringExtra("arrivalTime");
		String tripTimeDuration = intent.getStringExtra("tripTimeDuration");
		String requestedSeatCount = intent.getStringExtra("requestedSeatCount");
		String amount = intent.getStringExtra("amount");
		String date = intent.getStringExtra("date");

		// Set the text
		this.trainName.setText(trainName);
		this.departure.setText(departure);
		this.arrival.setText(arrival);
		this.departureTime.setText(departureTime);
		this.arrivalTime.setText(arrivalTime);
		this.tripTimeDuration.setText(tripTimeDuration);
		this.requestedSeatCount.setText(String.valueOf(requestedSeatCount));
		this.amount.setText(String.valueOf(amount));
		this.date.setText(date);

		checkout.setOnClickListener(v -> {
			//display a toast message
			Toast.makeText(getApplicationContext(), "Reservation added Successfully", Toast.LENGTH_SHORT).show();

			Intent intent1 = new Intent(ReservationSummary.this, Home.class);
			startActivity(intent1);
		});

	}
}