package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_mobile_application.managers.ContextManager;
import com.example.ead_mobile_application.managers.LoginManager;
import com.example.ead_mobile_application.R.id;
import com.example.ead_mobile_application.models.login.LoginResponse;

public class LoginActivity extends AppCompatActivity {
	private EditText etNic;
	private EditText etPassword;
	private  TextView forgotPassword;
	private Button btnLogin;
	private LoginManager loginManager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Button txtSignUp = (Button) findViewById(id.btn_signUp);
		TextView signUpText = findViewById(R.id.signUp_text);


		etNic = findViewById(id.user_nic);
		etPassword = findViewById(id.user_password);
		btnLogin = findViewById(id.btn_login);
		forgotPassword = findViewById(id.forgot_password);

		//To show the sign up button
		signUpText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				txtSignUp.setVisibility(View.VISIBLE);
			}
		});

		//navigate to home page
		forgotPassword.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent home = new Intent(getApplicationContext(),UserProfile.class);
				startActivity(home);
			}
		});


		txtSignUp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent signUp = new Intent(getApplicationContext(),AvailableTrainList.class);
				startActivity(signUp);

			}
		});

		btnLogin.setOnClickListener(view -> login());

		ContextManager.getInstance().setApplicationContext(getApplicationContext());
		loginManager = LoginManager.getInstance();

		checkLoginState();

	}

	private void login() {
		String nic = etNic.getText().toString();
		String password = etPassword.getText().toString();
		boolean isValid = loginManager.validateCredentials(nic, password);

		if (!isValid) {
			Toast.makeText(this, "Invalid NIC or Password", Toast.LENGTH_LONG).show();
		}


		loginManager.login(
				nic,
				password,
				loginResponse -> {
					handleLoginSuccess(loginResponse);
				},
				error -> handleLoginFailed(error));
	}

	private void handleLoginSuccess(LoginResponse loginResponse) {
		loginManager.setLoggedInState(true);
		loginManager.setUserDetails(loginResponse);
		checkLoginState();
	}

	private void handleLoginFailed(String error) {
		Toast.makeText(this, error, Toast.LENGTH_LONG).show();
	}

	private void checkLoginState() {
		System.out.print("loginManager.getIsLoggedIn() "+ loginManager.getIsLoggedIn());
		if (loginManager.getIsLoggedIn()) {
			showMainActivity();
		}
	}

	private void showMainActivity() {
		System.out.print("showMainActivity");
		Intent intent = new Intent(this, Home.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onBackPressed() {
		Button signUpButton = findViewById(R.id.btn_signUp);
		signUpButton.setVisibility(View.GONE);
		super.onBackPressed();
	}
}