package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ead_mobile_application.models.reservation.ReservationResponseBody;

import java.util.List;

public class UpdateReservation extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_reservation);

		//get the data from the intent
		Intent intent = getIntent();
		if (intent != null) {

			ReservationResponseBody reservation = (ReservationResponseBody) intent.getSerializableExtra("updateBody");
			System.out.println("update rservation 1234 "+reservation.nic);



		}


	}
}