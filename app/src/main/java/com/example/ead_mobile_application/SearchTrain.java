package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchTrain extends AppCompatActivity {
	private Calendar calendar;
	private EditText dateEditText;
	private Spinner destinationSpinner;
	private Spinner fromSpinner;
	private EditText numberOfSeats;
	private Button searchButton;

	private String mongoDBDateTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_train);

		dateEditText = findViewById(R.id.date_picker_edit_text);
		destinationSpinner = findViewById(R.id.destination_spinner);
		fromSpinner = findViewById(R.id.from_spinner);
		numberOfSeats = findViewById(R.id.num_of_seats_edit_text);
		searchButton = findViewById(R.id.btn_search_train);


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

		searchButton.setOnClickListener(view -> searchTrain());
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

		// Set the initial selection to the first item (Select Destination)
		destinationSpinner.setSelection(0);
		fromSpinner.setSelection(0);

		// Set up listeners to handle Spinner selections
		destinationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				String selectedDestination = parentView.getItemAtPosition(position).toString();
				if (position > 0) {
					System.out.println("Selected Destination: " + selectedDestination);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// Handle nothing selected if needed
			}
		});

		fromSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
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

	private void searchTrain(){
		String destination = destinationSpinner.getSelectedItem().toString();
		String from = fromSpinner.getSelectedItem().toString();
		String date = dateEditText.getText().toString();
		String seats = numberOfSeats.getText().toString();

		//convert string to int
		int seatsInt = Integer.parseInt(seats);
		if (seatsInt < 1) {
			//display toast message
			Toast.makeText(getApplicationContext(), "Please enter a valid number of seats", Toast.LENGTH_SHORT).show();
			return;
		} if (seatsInt >=5 ) {
			//display toast message
			Toast.makeText(getApplicationContext(), "You can only book a maximum of 4 seats", Toast.LENGTH_SHORT).show();
			return;
		}

		if(destination.equals("Select Destination")){
			destination = "";
		}
		if(from.equals("Select From")){
			from = "";
		}

		System.out.println("Destination: " + destination);
		System.out.println("From: " + from);
		System.out.println("Date: " + mongoDBDateTime);
		System.out.println("Seats: " + seats);
	}
}