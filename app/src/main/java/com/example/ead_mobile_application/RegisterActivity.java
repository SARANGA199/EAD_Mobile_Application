package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ead_mobile_application.managers.ContextManager;
import com.example.ead_mobile_application.managers.RegisterManager;

public class RegisterActivity extends AppCompatActivity {

	private EditText nic;
	private EditText Name;
	private EditText Email;
	private EditText password;
	private EditText confirmPassword;
	private Button register;

	private RegisterManager registerManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		nic = findViewById(R.id.nic);
		Name = findViewById(R.id.name);
		Email = findViewById(R.id.email);
		password = findViewById(R.id.password);
		confirmPassword = findViewById(R.id.re_password);
		register = findViewById(R.id.signup_btn);

		//To register the user
		register.setOnClickListener(view -> register());

		ContextManager.getInstance().setApplicationContext(this.getApplicationContext());
		registerManager = RegisterManager.getInstance();

	}

	private void register() {
		String nic = this.nic.getText().toString();
		String name = this.Name.getText().toString();
		String email = this.Email.getText().toString();
		String password = this.password.getText().toString();
		String confirmPassword = this.confirmPassword.getText().toString();

		if (nic.isEmpty()) {
			this.nic.setError("NIC is required");
			this.nic.requestFocus();
			return;
		}

		if (name.isEmpty()) {
			this.Name.setError("Name is required");
			this.Name.requestFocus();
			return;
		}

		if (email.isEmpty()) {
			this.Email.setError("Email is required");
			this.Email.requestFocus();
			return;
		}

		if (password.isEmpty()) {
			this.password.setError("Password is required");
			this.password.requestFocus();
			return;
		}else if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
			this.password.setError("Password should contain at least 8 characters, 1 uppercase, 1 lowercase, 1 number and 1 special character");
			this.password.requestFocus();
			return;
		}

		if (confirmPassword.isEmpty()) {
			this.confirmPassword.setError("Confirm Password is required");
			this.confirmPassword.requestFocus();
			return;
		}

		if (!password.equals(confirmPassword)) {
			this.confirmPassword.setError("Password and Confirm Password should be same");
			this.confirmPassword.requestFocus();
			return;
		}

		registerManager.register(nic, name, email, password,() -> handleRegisterSuccess(),error -> handleRegisterFailed(error));

	}

	private void handleRegisterSuccess() {
		Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();
		Intent login = new Intent(getApplicationContext(),LoginActivity.class);
		startActivity(login);
	}

	private void handleRegisterFailed(String error) {
		Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
	}

}