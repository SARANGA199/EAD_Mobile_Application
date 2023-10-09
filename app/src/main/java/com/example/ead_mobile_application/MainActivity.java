package com.example.ead_mobile_application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.ead_mobile_application.adapter.ReservationListAdapter;
import com.example.ead_mobile_application.managers.ContextManager;
import com.example.ead_mobile_application.managers.ReservationManager;
import com.example.ead_mobile_application.models.reservation.ReservationEntity;
import com.example.ead_mobile_application.models.reservation.ReservationStatus;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ReservationManager reservationManager;
    private Toolbar toolbar;
    private RelativeLayout loadingView;
    private RecyclerView recyclerView;
    private ReservationListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ContextManager.getInstance().setApplicationContext(getApplicationContext());
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        loadingView = findViewById(R.id.loadingView);
        recyclerView = findViewById(R.id.recyclerView);

        setSupportActionBar(toolbar);
        setupRecyclerView();

        reservationManager = ReservationManager.getInstance();
        loadData();

    }
    private void setIsLoading(boolean isLoading){
        loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
    }

    private void loadData(){
        setIsLoading(true);
        reservationManager.getReservations(
                this::handleDataLoaded,
                this::handleError
        );
    }

    private void handleDataLoaded(List<ReservationEntity> reservations){
        setIsLoading(false);
        adapter.updateData(reservations);
    }

    private void handleError(String error){
        setIsLoading(false);
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    private void setupRecyclerView(){
        adapter = new ReservationListAdapter();
        adapter.setItemClickListener((view, position) -> handleItemClick(position));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void handleItemClick(int position){
        ReservationEntity reservation = adapter.getReservationAt(position);
        ReservationStatus nextStatus = reservationManager.getNextReservationStatus(reservation.status);

        Log.d("Click", "You clicked " + reservation.referenceId);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (nextStatus == null){
            builder.setTitle("Task already completed");
            builder.setMessage("You cannot change the state of completed tasks");
            builder.setPositiveButton("OK", null);
        }
        else{
            String nextStatusName = String.format("\"%s\"?", nextStatus);

            builder.setTitle("Change task status");
            builder.setMessage("Do you want to change the status to " + nextStatusName);
            builder.setNegativeButton("NO", null);
            builder.setPositiveButton("OK", (dialog, which) -> handleChangeItemStatus(reservation, nextStatus, position));
        }

        builder.show();
    }

    private void handleChangeItemStatus(ReservationEntity reservation, ReservationStatus nextStatus, int position){
        reservationManager.updateReservationStatus(
                reservation,
                nextStatus,
                newReservation -> adapter.updateReservationAt(position, newReservation),
                this::handleError);
    }




}