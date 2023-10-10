package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ead_mobile_application.manager.LoginManager;

public class Home extends AppCompatActivity {

	//cardView variables
	private CardView cardView1;
	private CardView cardView2;

	private Button logoutButton;
	private Button userProfileButton;

	private LoginManager loginManager;
	private final String loginStateFile = "loginstate";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		cardView1 = findViewById(R.id.cardView);
		cardView2 = findViewById(R.id.cardView2);
		logoutButton = findViewById(R.id.logoutButton);
		userProfileButton = findViewById(R.id.userProfileButton);

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

		logoutButton.setOnClickListener(v -> {
			loginManager = LoginManager.getInstance();
			loginManager.logout();
		});

		userProfileButton.setOnClickListener(v -> {
			//navigate to login screen
			Intent reservation = new Intent(Home.this, UserProfile.class);
			startActivity(reservation);
			finish();
		});


	}
}