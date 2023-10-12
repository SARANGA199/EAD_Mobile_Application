package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ead_mobile_application.managers.ContextManager;
import com.example.ead_mobile_application.managers.ReservationManager;
import com.example.ead_mobile_application.models.reservation.ReservationResponseBody;

import java.util.ArrayList;
import java.util.List;

public class Reservations extends AppCompatActivity {

	ReservationManager reservationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservations);

		ContextManager.getInstance().setApplicationContext(this.getApplicationContext());
		reservationManager = ReservationManager.getInstance();

		String nic = "990641099V";
		getReservations(nic);

	}

	private void getReservations(String nic) {

		reservationManager.getReservationsByNic(nic , reservations -> handleOnSuccess(reservations), error -> handleOnError(error));

	}

	private void handleOnSuccess(List<ReservationResponseBody> reservations) {

		//check if the list is empty
		if(reservations.isEmpty()){
			Toast.makeText(this, "No reservations found", Toast.LENGTH_SHORT).show();
			return;
		}

		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		ReservationAdapter reservationAdapter = new ReservationAdapter(reservations);
		recyclerView.setAdapter(reservationAdapter);
	}

	private void handleOnError(String error) {
		Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
	}


}