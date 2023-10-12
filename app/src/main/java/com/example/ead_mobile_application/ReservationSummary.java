package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_mobile_application.managers.ContextManager;
import com.example.ead_mobile_application.managers.ReservationManager;
import com.example.ead_mobile_application.models.reservation.ReservationRequestBody;

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

	private ReservationManager reservationManager;

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

		ContextManager.getInstance().setApplicationContext(this.getApplicationContext());
		reservationManager = ReservationManager.getInstance();


		// Get the intent that started this activity
		Intent intent = getIntent();

		// Retrieve data from the intent using the keys
		String trainId = intent.getStringExtra("trainId");
		String trainName = intent.getStringExtra("trainName");
		String depature = intent.getStringExtra("departure");
		String arrival = intent.getStringExtra("arrival");
		String depatureTime = intent.getStringExtra("departureTime");
		String arrivalTime = intent.getStringExtra("arrivalTime");
		String averageTimeDuration = intent.getStringExtra("tripTimeDuration");
		String requestedSeatCount = intent.getStringExtra("requestedSeatCount");
		String amount = intent.getStringExtra("amount");
		String date = intent.getStringExtra("date");

		//generate random unique id for referenceId.It should start with "REF" and then 10 random numbers
		String referenceId = "REF" + String.valueOf((int) (Math.random() * 1000000000));
		String nic ="990641099V";
		//String trainId = "6517de92ac07810b65f07dbf";
		 //convert requestedSeatCount to int
		int passengersCount= Integer.parseInt(requestedSeatCount);
		//convert totalAmount to Double
		double totalAmount= Double.parseDouble(amount) * passengersCount;



		// Set the text
		this.trainName.setText(trainName);
		this.departure.setText(depature);
		this.arrival.setText(arrival);
		this.departureTime.setText(depatureTime);
		this.arrivalTime.setText(arrivalTime);
		this.tripTimeDuration.setText(averageTimeDuration);
		this.requestedSeatCount.setText(String.valueOf(requestedSeatCount));
		this.amount.setText(String.valueOf(totalAmount));
		this.date.setText(date);

		ReservationRequestBody reservationRequestBody = new ReservationRequestBody(
				referenceId,
				nic,
				trainId,
				passengersCount,
				date,
				totalAmount,
				depature,
				arrival,
				depatureTime,
				arrivalTime,
				averageTimeDuration

		);

		checkout.setOnClickListener(v -> addNewReservation(reservationRequestBody));

	}

	private void addNewReservation(ReservationRequestBody reservationRequestBody) {

		reservationManager.saveReservation(reservationRequestBody , () -> handleRegisterSuccess(),error -> handleRegisterFailed(error));

	}

	private void handleRegisterSuccess() {
		Toast.makeText(this, "Reservation Successful", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Reservations.class);
		startActivity(intent);
	}

	private void handleRegisterFailed(String error) {
		Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
	}

}