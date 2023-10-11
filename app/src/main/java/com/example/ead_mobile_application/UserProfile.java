package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.ead_mobile_application.managers.ContextManager;

public class UserProfile extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);

		Context context = ContextManager.getInstance().getApplicationContext();
		SharedPreferences sharedPref = context.getSharedPreferences("loginstate", Context.MODE_PRIVATE);

		String nic = sharedPref.getString("nic", "");
		String name = sharedPref.getString("name", "");
		String email = sharedPref.getString("email", "");
		String isActive = sharedPref.getBoolean("is_active", false) ? "Active" : "Inactive";
		String userRole = sharedPref.getInt("user_role", 0) == 1 ? "Admin" : "User";

		System.out.println("NIC: " + nic);
		System.out.println("Name: " + name);

	}
}