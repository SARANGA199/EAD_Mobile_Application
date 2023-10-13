package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ead_mobile_application.managers.ContextManager;
import com.example.ead_mobile_application.managers.DatabaseManager;
import com.example.ead_mobile_application.managers.ReservationManager;
import com.example.ead_mobile_application.models.login.LoginDao;
import com.example.ead_mobile_application.models.login.LoginEntity;
import com.example.ead_mobile_application.models.reservation.ReservationResponseBody;

import java.util.ArrayList;
import java.util.List;

public class Reservations extends AppCompatActivity {

	ReservationManager reservationManager;
	private DatabaseManager databaseManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservations);

		ContextManager.getInstance().setApplicationContext(this.getApplicationContext());
		reservationManager = ReservationManager.getInstance();
		databaseManager = DatabaseManager.getInstance();

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
					getReservations(loginEntity.nic);
				}
			}
		}.execute();



	}

	private void getReservations(String nic) {

		reservationManager.getReservationsByNic(nic , reservations -> handleOnSuccess(reservations), error -> handleOnError(error));

	}

	private void handleOnSuccess(List<ReservationResponseBody> reservations) {

		//check if the list is empty
		if(reservations.isEmpty()){
			Toast.makeText(this, "No reservations found", Toast.LENGTH_SHORT).show();
			return;
		}

		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		ReservationAdapter reservationAdapter = new ReservationAdapter(reservations);
		recyclerView.setAdapter(reservationAdapter);
	}

	private void handleOnError(String error) {
		Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
	}


}