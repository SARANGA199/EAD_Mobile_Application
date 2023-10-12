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


//		RecyclerView recyclerView = findViewById(R.id.recycler_view);
//		recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//		List<ReservationDetails> reservationDetailsList =  new ArrayList<>();
//		reservationDetailsList.add(new ReservationDetails("1", "001M", "990584718V","85741",4, "2023-10-01", "Matara", "Galle", "6:00 AM", "7:00 PM", "1.0 Hours", 750.0));
//		reservationDetailsList.add(new ReservationDetails("2", "002M", "990542618V","82441",4, "2023-10-01", "Matara", "Colombo", "6:00 AM", "9:00 PM", "3.0 Hours", 3250.0));
//
//		ReservationAdapter reservationAdapter = new ReservationAdapter(reservationDetailsList);
//		recyclerView.setAdapter(reservationAdapter);
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

		Toast.makeText(this, "Success 333333333333333", Toast.LENGTH_SHORT).show();


		System.out.println("reservations.size() = " + reservations.size());
		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		ReservationAdapter reservationAdapter = new ReservationAdapter(reservations);
		recyclerView.setAdapter(reservationAdapter);
	}

	private void handleOnError(String error) {
		Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
	}


}