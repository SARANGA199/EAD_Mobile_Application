package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

	Button btnLogin;
	Button btnRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_screen);

		btnLogin = findViewById(R.id.loginButton);
		btnRegister = findViewById(R.id.registerButton);

		btnLogin.setOnClickListener(v -> {
			//navigate to login screen
			Intent login = new Intent(WelcomeScreen.this, LoginActivity.class);
			startActivity(login);
			finish();
		});

		btnRegister.setOnClickListener(v -> {
			//navigate to login screen
			Intent register = new Intent(WelcomeScreen.this, RegisterActivity.class);
			startActivity(register);
			finish();
		});

		}

}