package com.example.ead_mobile_application.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ead_mobile_application.R;
import com.example.ead_mobile_application.models.reservation.ReservationEntity;
import com.example.ead_mobile_application.models.reservation.ReservationStatus;
import com.example.ead_mobile_application.utilities.ItemClickListener;

public class ReservationViewHolder extends RecyclerView.ViewHolder{

    private TextView tvTitle;
    private TextView tvDueDate;
    private TextView tvStatus;

    public ReservationViewHolder(@NonNull View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvDueDate = itemView.findViewById(R.id.tvDueDate);
        tvStatus = itemView.findViewById(R.id.tvStatus);
    }

    private void setStatusUI(String status, @DrawableRes int drawableResource){
        Context context = itemView.getContext();
        tvStatus.setText(status);
        tvStatus.setBackground(ContextCompat.getDrawable(context, drawableResource));
    }

    private void setTaskStatus(ReservationStatus status){
        switch(status){
            case PENDING: setStatusUI("Pending", R.drawable.status_pending_drawable); break;
            case IN_PROGRESS: setStatusUI("In progress", R.drawable.status_inprogress_drawable); break;
            case COMPLETED: setStatusUI("Completed", R.drawable.status_complete_drawable); break;
        }
    }

    private void setClickListener(ItemClickListener clickListener){
        if (clickListener != null){
            itemView.setOnClickListener(view -> clickListener.onItemClick(view, getAdapterPosition()));
        }
        else{
            itemView.setOnClickListener(null);
        }
    }

    public void setData(ReservationEntity entity, ItemClickListener clickListener){
        tvTitle.setText(entity.referenceId);
        tvDueDate.setText(entity.getDueDateAsString());
        setTaskStatus(entity.status);
        setClickListener(clickListener);
    }



}
