package com.example.ead_mobile_application;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ead_mobile_application.managers.ContextManager;
import com.example.ead_mobile_application.managers.ReservationManager;
import com.example.ead_mobile_application.managers.TrainManager;
import com.example.ead_mobile_application.models.reservation.ReservationResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder>{

	private List<ReservationResponseBody> reservationDetailsList;



	public ReservationAdapter(List<ReservationResponseBody> reservationDetailsList) {
		this.reservationDetailsList = reservationDetailsList;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		ReservationResponseBody item = reservationDetailsList.get(position);
		holder.bind(item);
	}

	@Override
	public int getItemCount() {
		return reservationDetailsList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder{



		private TextView reservationId;
		private TextView nic;
		private TextView trainId;
		private TextView date;
		private TextView departure;
		private TextView arrival;
		private TextView departureTime;
		private TextView arrivalTime;
		private TextView tripTimeDuration;
		private TextView numberOfSeat;
		private TextView amount;
		private Button cancelReservation;
		private Button updateReservation;

		private CardView cardView;



		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			reservationId = itemView.findViewById(R.id.reference_id);
			nic = itemView.findViewById(R.id.nic);
			trainId = itemView.findViewById(R.id.trainId);
			date = itemView.findViewById(R.id.date);
			departure = itemView.findViewById(R.id.departure);
			arrival = itemView.findViewById(R.id.arrival);
			departureTime = itemView.findViewById(R.id.departureTime);
			arrivalTime = itemView.findViewById(R.id.arrivalTime);
			tripTimeDuration = itemView.findViewById(R.id.tripTimeDuration);
			numberOfSeat = itemView.findViewById(R.id.seatCount);
			amount = itemView.findViewById(R.id.amount);
			cancelReservation = itemView.findViewById(R.id.res_delete);
			updateReservation = itemView.findViewById(R.id.res_update);
			cardView = itemView.findViewById(R.id.res_history);
		}

		public void bind(ReservationResponseBody item){
			reservationId.setText(item.getReferenceId());
			nic.setText(item.getNic());
			trainId.setText(item.getTrainId());
			date.setText(item.getDate());
			departure.setText(item.getDepature());
			arrival.setText(item.getArrival());
			departureTime.setText(item.getDepatureTime());
			arrivalTime.setText(item.getArrivalTime());
			tripTimeDuration.setText(item.getAverageTimeDuration());
			numberOfSeat.setText(String.valueOf(item.getPassengersCount()));
			amount.setText(String.valueOf(item.getTotalAmount()));


			//send data to another activity
			cardView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(v.getContext(), ReservationAction.class);
					intent.putExtra("reservationBody", item);
					v.getContext().startActivity(intent);
				}
			});

			//send data to update reservation activity
			updateReservation.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
						// Enable the button and allow the update

						Intent intent = new Intent(v.getContext(), UpdateReservation.class);
						intent.putExtra("updateBody", item);
						v.getContext().startActivity(intent);
				}
			});
			//cancel reservation
			cancelReservation.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//send data to update reservation activity
					showCancelConfirmationDialog(item.getId());

				}
			});


	}

		private void showCancelConfirmationDialog(final String reservationId) {
			AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
			builder.setMessage("Are you sure you want to cancel this reservation?");
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// User confirmed the cancellation
					cancelReservations(reservationId);
					dialog.dismiss();
				}
			});
			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// User canceled the operation
					dialog.dismiss();
				}
			});

			AlertDialog dialog = builder.create();
			dialog.show();
		}
		public void cancelReservations(String id){

		ReservationManager reservationManager;
		ContextManager.getInstance().setApplicationContext(itemView.getContext());
		reservationManager = ReservationManager.getInstance();
		reservationManager.cancelReservation(id, (message) -> handleReUpdateSuccess(message), error -> handleReUpdateFailed(error));

	}

	public void handleReUpdateSuccess(String message) {
		Toast.makeText(itemView.getContext(), message, Toast.LENGTH_SHORT).show();

	}

	public void handleReUpdateFailed(String error) {
		Toast.makeText(itemView.getContext(), error, Toast.LENGTH_SHORT).show();
	}


	}

}
