package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_mobile_application.managers.ContextManager;
import com.example.ead_mobile_application.managers.ReservationManager;
import com.example.ead_mobile_application.models.reservation.ReservationResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ReservationAction extends AppCompatActivity {

	ReservationResponseBody reservation;
	private TextView reservationId;
	private TextView nic;
	private TextView trainId;
	private TextView date;
	private TextView departure;
	private TextView arrival;
	private TextView departureTime;
	private TextView arrivalTime;
	private TextView tripTimeDuration;
	private TextView numberOfSeat;
	private TextView amount;
	private Button cancelReservation;
	private Button updateReservation;

	private CardView status;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservation_action);

		//get the data from the intent
		Intent intent = getIntent();
		if (intent != null) {

			reservation = (ReservationResponseBody) intent.getSerializableExtra("reservationBody");
			System.out.println("update rservation 1234 "+reservation.nic);
		}

		reservationId = findViewById(R.id.reference_id);
		nic = findViewById(R.id.nic);
		trainId = findViewById(R.id.trainId);
		date = findViewById(R.id.date);
		departure = findViewById(R.id.departure);
		arrival = findViewById(R.id.arrival);
		departureTime = findViewById(R.id.departureTime);
		arrivalTime = findViewById(R.id.arrivalTime);
		tripTimeDuration = findViewById(R.id.tripTimeDuration);
		numberOfSeat = findViewById(R.id.seatCount);
		amount = findViewById(R.id.amount);
		cancelReservation = findViewById(R.id.res_delete);
		updateReservation = findViewById(R.id.res_update);
		status = findViewById(R.id.status_btn);

		reservationId.setText(reservation.getReferenceId());
		nic.setText(reservation.getNic());
		trainId.setText(reservation.getTrainId());
		date.setText(reservation.getDate());
		departure.setText(reservation.getDepature());
		arrival.setText(reservation.getArrival());
		departureTime.setText(reservation.getDepatureTime());
		arrivalTime.setText(reservation.getArrivalTime());
		tripTimeDuration.setText(reservation.getAverageTimeDuration());
		numberOfSeat.setText(String.valueOf(reservation.getPassengersCount()));
		amount.setText(String.valueOf(reservation.getTotalAmount()));

		// Calculate the day difference between the current date and reservation date
		Calendar currentDate = Calendar.getInstance();
		Date current = currentDate.getTime();

		// Parse the date from the item (assuming item is a String)
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
		Date reservationDate = null;
		try {
			reservationDate = dateFormat.parse(reservation.getDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (reservationDate == null) {

			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
			try {
				reservationDate = dateFormat2.parse(reservation.getDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		long timeDifference = reservationDate.getTime() - current.getTime();
		int dayDifference = (int) TimeUnit.DAYS.convert(timeDifference, TimeUnit.MILLISECONDS);

		if (dayDifference < 0) {
			cancelReservation.setVisibility(View.GONE);
			updateReservation.setVisibility(View.GONE);
			status.setVisibility(View.VISIBLE);
			status.setEnabled(false);
		} else {
			if (dayDifference <= 5) {
				updateReservation.setEnabled(false);
				cancelReservation.setEnabled(false);

				updateReservation.setBackgroundResource(R.drawable.button_disabled);
				cancelReservation.setBackgroundResource(R.drawable.button_disabled);


				//display toast message color will be red
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.custom_toast_layout, null);
				TextView text = layout.findViewById(R.id.toast_message);
				text.setText("You cannot update or cancel this reservation before 5 days");

				Toast toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_SHORT);
				toast.setView(layout);

				toast.show();
			} else {
				updateReservation.setOnClickListener(v -> {
					Intent intent1 = new Intent(v.getContext(), UpdateReservation.class);
					intent1.putExtra("updateBody", reservation);
					startActivity(intent1);
				});

				cancelReservation.setOnClickListener(v -> {
					showCancelConfirmationDialog(reservation.getId());
				});

			}

		}

	}


	private void showCancelConfirmationDialog(final String reservationId) {
		AlertDialog.Builder builder = new AlertDialog.Builder( this.getApplicationContext());
		builder.setMessage("Are you sure you want to cancel this reservation?");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// User confirmed the cancellation
				cancelReservations(reservationId);
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// User canceled the operation
				dialog.dismiss();
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
	}
	public void cancelReservations(String id){

		ReservationManager reservationManager;
		ContextManager.getInstance().setApplicationContext(getApplicationContext());
		reservationManager = ReservationManager.getInstance();
		reservationManager.cancelReservation(id, (message) -> handleReUpdateSuccess(message), error -> handleReUpdateFailed(error));

	}

	public void handleReUpdateSuccess(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

	}

	public void handleReUpdateFailed(String error) {
		Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
	}



}