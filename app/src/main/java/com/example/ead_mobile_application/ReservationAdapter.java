package com.example.ead_mobile_application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder>{

	private List<ReservationDetails> reservationDetailsList;

	public ReservationAdapter(List<ReservationDetails> reservationDetailsList) {
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
		ReservationDetails item = reservationDetailsList.get(position);
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
		}

		public void bind(ReservationDetails item){
			reservationId.setText(item.getReference_id());
			nic.setText(item.getNic());
			trainId.setText(item.getTrain_id());
			date.setText(item.getDate());
			departure.setText(item.getDepature());
			arrival.setText(item.getArrival());
			departureTime.setText(item.getDeparture_time());
			arrivalTime.setText(item.getArrival_time());
			tripTimeDuration.setText(item.getAverage_time_duration());
			numberOfSeat.setText(String.valueOf(item.getPassengers_count()));
			amount.setText(String.valueOf(item.getAmount()));
		}


	}

}
