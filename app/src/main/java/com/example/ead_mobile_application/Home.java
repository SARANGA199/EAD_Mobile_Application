package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ead_mobile_application.managers.DatabaseManager;
import com.example.ead_mobile_application.managers.LoginManager;
import com.example.ead_mobile_application.models.login.LoginDao;
import com.example.ead_mobile_application.models.login.LoginEntity;

import java.util.List;

public class Home extends AppCompatActivity {

	//cardView variables
	private CardView cardView1;
	private CardView cardView2;

	private Button logout;
	private Button userProfile;

	private LoginManager loginManager;
	private DatabaseManager databaseManager;
	private final String loginStateFile = "loginstate";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		cardView1 = findViewById(R.id.cardView);
		cardView2 = findViewById(R.id.cardView2);
		logout = findViewById(R.id.logoutButton);
		userProfile = findViewById(R.id.userProfileButton);

		cardView1.setOnClickListener(v -> {
			//navigate to login screen
			Intent reservation = new Intent(Home.this, SearchTrain.class);
			startActivity(reservation);

		});

		cardView2.setOnClickListener(v -> {
			//navigate to login screen
			Intent reservation = new Intent(Home.this, Reservations.class);
			startActivity(reservation);

		});

		logout.setOnClickListener(v -> {
			loginManager = LoginManager.getInstance();
			loginManager.logout();
		});

		userProfile.setOnClickListener(v -> {
			//navigate to login screen
			Intent reservation = new Intent(Home.this, UserProfile.class);
			startActivity(reservation);

		});


	}



}