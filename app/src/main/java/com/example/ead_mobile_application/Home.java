package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class Home extends AppCompatActivity {

	//cardView variables
	CardView cardView1;
	CardView cardView2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		cardView1 = findViewById(R.id.cardView);
		cardView2 = findViewById(R.id.cardView2);

		cardView1.setOnClickListener(v -> {
			//navigate to login screen
			Intent reservation = new Intent(Home.this, SearchTrain.class);
			startActivity(reservation);
			finish();
		});

		cardView2.setOnClickListener(v -> {
			//navigate to login screen
			Intent reservation = new Intent(Home.this, Reservations.class);
			startActivity(reservation);

		});



	}
}