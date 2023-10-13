package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ead_mobile_application.managers.ContextManager;
import com.example.ead_mobile_application.managers.DatabaseManager;
import com.example.ead_mobile_application.models.login.LoginDao;
import com.example.ead_mobile_application.models.login.LoginEntity;

import java.util.List;

public class UserProfile extends AppCompatActivity {

	private TextView nic;
	private TextView Name;
	private TextView Email;
	private TextView password;
	private Button update;

	private DatabaseManager databaseManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);

		// Get the text views
		nic = findViewById(R.id.pro_nic);
		Name = findViewById(R.id.pro_name);
		Email = findViewById(R.id.pro_email);
		password = findViewById(R.id.pro_passsword);
		update = findViewById(R.id.edit_profile_button);

		// Create a function to navigate to edit profile page
		update.setOnClickListener(view -> {
			// Navigate to edit profile page
			Intent intent = new Intent(this, UserProfileUpdateActivity.class);
			startActivity(intent);
		});

		ContextManager.getInstance().setApplicationContext(this.getApplicationContext());
		databaseManager = DatabaseManager.getInstance();

		// Execute database operations in a background thread
		new AsyncTask<Void, Void, LoginEntity>() {
			@Override
			protected LoginEntity doInBackground(Void... voids) {
				LoginDao dao = databaseManager.db().LoginDao();
				List<LoginEntity> loginEntity = dao.get();
				return (loginEntity != null && loginEntity.size() > 0) ? loginEntity.get(0) : null;
			}

			@Override
			protected void onPostExecute(LoginEntity loginEntity) {
				if (loginEntity != null) {
					// Update UI with retrieved data
					nic.setText(loginEntity.nic);
					Name.setText(loginEntity.name);
					Email.setText(loginEntity.email);
				} else {
					// No user logged in
					// Navigate to login page
					Intent intent = new Intent(UserProfile.this, LoginActivity.class);
					startActivity(intent);

				}
			}
		}.execute();
	}
}