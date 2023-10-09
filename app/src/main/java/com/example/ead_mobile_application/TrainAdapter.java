package com.example.ead_mobile_application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder> {

	private List<TrainDetails> trainDetailsList;

	public TrainAdapter(List<TrainDetails> trainDetailsList) {
		this.trainDetailsList = trainDetailsList;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_list_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
			TrainDetails item = trainDetailsList.get(position);
			holder.bind(item);
	}

	@Override
	public int getItemCount() {
		return trainDetailsList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		private TextView trainName;
		private TextView departure;
		private TextView arrival;
		private TextView departureTime;
		private TextView arrivalTime;
		private TextView tripTimeDuration;
		private TextView requestedSeatCount;
		private TextView availableSeatCount;
		private TextView amount;
		private Button bookTicket;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			trainName = itemView.findViewById(R.id.trainName);
			departure = itemView.findViewById(R.id.departure);
			arrival = itemView.findViewById(R.id.arrival);
			departureTime = itemView.findViewById(R.id.departureTime);
			arrivalTime = itemView.findViewById(R.id.arrivalTime);
			tripTimeDuration = itemView.findViewById(R.id.tripTimeDuration);
			requestedSeatCount = itemView.findViewById(R.id.requestedSeatCount);
			availableSeatCount = itemView.findViewById(R.id.availableSeatCount);
			amount = itemView.findViewById(R.id.amount);
			bookTicket = itemView.findViewById(R.id.book_ticket);
		}

		public void bind(TrainDetails item) {
			trainName.setText(item.getTrainName());
			departure.setText(item.getDeparture());
			arrival.setText(item.getArrival());
			departureTime.setText(item.getDepartureTime());
			arrivalTime.setText(item.getArrivalTime());
			tripTimeDuration.setText(String.valueOf(item.getTripTimeDuration()));
			requestedSeatCount.setText(String.valueOf(item.getRequestedSeatCount()));
			availableSeatCount.setText(String.valueOf(item.getAvailableSeatCount()));
			amount.setText(String.valueOf(item.getAmount()));
		}
	}

}
