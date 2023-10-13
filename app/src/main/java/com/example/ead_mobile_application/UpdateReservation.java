package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_mobile_application.managers.ContextManager;
import com.example.ead_mobile_application.managers.ReservationManager;
import com.example.ead_mobile_application.models.reservation.ReservationResponseBody;
import com.example.ead_mobile_application.models.reservation.UpdateReservationBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class UpdateReservation extends AppCompatActivity {

	private Calendar calendar;
	private EditText dateEditText;
	private Spinner destinationSpinner;
	private Spinner fromSpinner;
	private EditText numberOfSeats;
	private Button searchButton;

	private String mongoDBDateTime;

	ReservationResponseBody reservation;
	private ReservationManager reservationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_reservation);

		//get the data from the intent
		Intent intent = getIntent();
		if (intent != null) {

			 reservation = (ReservationResponseBody) intent.getSerializableExtra("updateBody");

		}

		dateEditText = findViewById(R.id.date_picker_edit_text);
		destinationSpinner = findViewById(R.id.destination_spinner);
		fromSpinner = findViewById(R.id.from_spinner);
		numberOfSeats = findViewById(R.id.num_of_seats_edit_text);
		searchButton = findViewById(R.id.btn_search_train);

		//get date only from the date time
		String[] date = reservation.date.split("T");
		reservation.date = date[0];

		//set the data to the fields
		dateEditText.setText(reservation.date);
		numberOfSeats.setText(String.valueOf(reservation.passengersCount));
		// Set the initial selection to as string



		// Set up the Spinners
	    setupSpinners();
		// Set up the date picker
	    calendar = Calendar.getInstance();

		// Set the date and time to now
		dateEditText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				showDatePickerDialog();
			}
		});

		ContextManager.getInstance().setApplicationContext(this.getApplicationContext());
		reservationManager = ReservationManager.getInstance();

		searchButton.setOnClickListener(view -> updateReservation());
	}

	private void setupSpinners() {
		// Create an ArrayAdapter for the Spinners
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this,
				R.array.locations_array,
				android.R.layout.simple_spinner_item
		);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Set the ArrayAdapter for both Spinners
		destinationSpinner.setAdapter(adapter);
		fromSpinner.setAdapter(adapter);

		// Get the values from the previous page
		String previousDestinationValue = reservation.arrival;
		String previousFromValue = reservation.depature;

		// Set the initial selection for the destinationSpinner
		int destinationPosition = adapter.getPosition(previousDestinationValue);
		if (destinationPosition >= 0) {
			destinationSpinner.setSelection(destinationPosition);
		} else {

		}

		// Set the initial selection for the fromSpinner
		int fromPosition = adapter.getPosition(previousFromValue);
		if (fromPosition >= 0) {
			fromSpinner.setSelection(fromPosition);
		} else {
		}

		destinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				String selectedDestination = parentView.getItemAtPosition(position).toString();
				if (position > 0) {
					System.out.println("Selected Destination: " + selectedDestination);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});

		fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				String selectedFrom = parentView.getItemAtPosition(position).toString();
				if (position > 0) {
					System.out.println("Selected From: " + selectedFrom);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// Handle nothing selected if needed
			}
		});
	}

	private void showDatePickerDialog() {
		// Calculate the minimum and maximum allowed dates
		Calendar minDate = Calendar.getInstance();
		Calendar maxDate = Calendar.getInstance();
		maxDate.add(Calendar.DAY_OF_MONTH, 30);

		DatePickerDialog datePickerDialog = new DatePickerDialog(
				this,
				new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						// Set the selected date
						calendar.set(Calendar.YEAR, year);
						calendar.set(Calendar.MONTH, monthOfYear);
						calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

						// Get the current time
						Calendar currentTime = Calendar.getInstance();
						calendar.set(Calendar.HOUR_OF_DAY, currentTime.get(Calendar.HOUR_OF_DAY));
						calendar.set(Calendar.MINUTE, currentTime.get(Calendar.MINUTE));
						calendar.set(Calendar.SECOND, currentTime.get(Calendar.SECOND));

						// Format the date and time as MongoDB format
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
						sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
						mongoDBDateTime = sdf.format(calendar.getTime());

						//display the date on the edit text
						String dateNow = mongoDBDateTime.substring(0,10);
						dateEditText.setText(dateNow);


						//send it to the backend
						System.out.println("Formatted date and time in MongoDB format: " + mongoDBDateTime);
					}
				},
				calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH)
		);

		// Set the minimum and maximum allowed dates
		datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
		datePickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());

		// Show only the DatePicker, hide the TimePicker
		DatePicker datePicker = datePickerDialog.getDatePicker();
		datePicker.setCalendarViewShown(true);
		datePicker.setSpinnersShown(false);

		datePickerDialog.show();
	}

	private void updateReservation() {
		String destination = destinationSpinner.getSelectedItem().toString();
		String from = fromSpinner.getSelectedItem().toString();
		String date = dateEditText.getText().toString();
		String seats = numberOfSeats.getText().toString();
		String newDate;

		if (mongoDBDateTime == null) {
			//display toast message
			newDate = reservation.date;

		}else{
			newDate = mongoDBDateTime;
		}


		//convert string to int
		int seatsInt = Integer.parseInt(seats);
		if (seatsInt < 1) {
			//display toast message
			Toast.makeText(getApplicationContext(), "Please enter a valid number of seats", Toast.LENGTH_SHORT).show();
			return;
		}
		if (seatsInt >= 5) {
			//display toast message
			Toast.makeText(getApplicationContext(), "You can only book a maximum of 4 seats", Toast.LENGTH_SHORT).show();
			return;
		}

		if (destination.equals("Select Destination")) {
			destination = "";
		}
		if (from.equals("Select From")) {
			from = "";
		}

		UpdateReservationBody updateReservationBody = new UpdateReservationBody(seatsInt,newDate ,from ,destination );

		//send the data to the backend
		reservationManager.updateReservation(reservation.id ,updateReservationBody,(message) -> handleReUpdateSuccess(message), error -> handleReUpdateFailed(error));
	}

	private void handleReUpdateSuccess(String message) {

		if (message.equals("Reservation updated successfully !")) {
			//display toast message
			//Toast.makeText(getApplicationContext(), "Reservation Updated", Toast.LENGTH_SHORT).show();
			LayoutInflater inflater = getLayoutInflater();
			View layout = inflater.inflate(R.layout.success_toast_layout, null);

			TextView text = layout.findViewById(R.id.toast_message_success);
			text.setText("Reservation Updated Successfully");

			Toast toast = new Toast(getApplicationContext());
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setView(layout);

			toast.show();
			//go back to the main activity
			Intent intent = new Intent(this, Reservations.class);
			startActivity(intent);
		}

		//display toast message color will be red
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.custom_toast_layout, null);

		TextView text = layout.findViewById(R.id.toast_message);
		text.setText(message);

		Toast toast = new Toast(getApplicationContext());
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);

		toast.show();
	}

	private void handleReUpdateFailed(String error) {
		//display toast message
		Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
		System.out.println("Reservation Update Failed: " + error);
	}

}