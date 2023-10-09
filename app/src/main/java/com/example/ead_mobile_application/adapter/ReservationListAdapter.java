package com.example.ead_mobile_application.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ead_mobile_application.R;
import com.example.ead_mobile_application.models.reservation.ReservationEntity;
import com.example.ead_mobile_application.utilities.ItemClickListener;
import com.example.ead_mobile_application.viewholder.ReservationViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ReservationListAdapter extends RecyclerView.Adapter{

    private List<ReservationEntity> list;
    private ItemClickListener clickListener;
    public ReservationListAdapter(){
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.reservation_item, parent, false);
        return new ReservationViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ReservationViewHolder reservationViewHolder = (ReservationViewHolder) holder;
        reservationViewHolder.setData(list.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateData(List<ReservationEntity> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public ReservationEntity getReservationAt(int position){
        return list.get(position);
    }

    public void updateReservationAt(int position, ReservationEntity newReservation){
        this.list.set(position, newReservation);
        notifyItemChanged(position);
    }

}
