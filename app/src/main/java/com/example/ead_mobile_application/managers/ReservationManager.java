package com.example.ead_mobile_application.managers;

import android.util.Log;
import android.widget.Toast;

import com.example.ead_mobile_application.models.register.RegisterService;
import com.example.ead_mobile_application.models.reservation.ReservationDao;
import com.example.ead_mobile_application.models.reservation.ReservationDto;
import com.example.ead_mobile_application.models.reservation.ReservationEntity;
import com.example.ead_mobile_application.models.reservation.ReservationRequestBody;
import com.example.ead_mobile_application.models.reservation.ReservationResponse;
import com.example.ead_mobile_application.models.reservation.ReservationResponseBody;
import com.example.ead_mobile_application.models.reservation.ReservationService;
import com.example.ead_mobile_application.models.reservation.ReservationStatus;
import com.example.ead_mobile_application.utilities.MainThreadHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationManager {

    private static ReservationManager singleton;
    private DatabaseManager databaseManager;
    private ReservationService reservationService;

    public static ReservationManager getInstance(){
        if (singleton == null)
            singleton = new ReservationManager();
        return singleton;
    }

    private ReservationManager(){
        databaseManager = DatabaseManager.getInstance();
        reservationService = com.example.ead_mobile_application.managers.NetworkManager.getInstance().createService(ReservationService.class);
    }

    public void saveReservation(ReservationRequestBody body , Runnable onSuccess, Consumer<String> onError){
        if (!NetworkManager.getInstance().isNetworkAvailable()){
            onError.accept("No internet connection");
            return;
        }

        reservationService.addReservation(body).enqueue(new Callback<ReservationResponseBody>() {
            @Override
            public void onResponse(Call<ReservationResponseBody> call, Response<ReservationResponseBody> response) {
                if (response.isSuccessful()){
                    ReservationResponseBody reservationResponse = response.body();

                    if (reservationResponse != null){
                        System.out.println("Reservation response:55555555 " + reservationResponse.nic);
                        onSuccess.run();
                    }else{
                        onError.accept("No response from server");
                    }

                }else{
                    onError.accept("Reservation failed");
                }
            }

            @Override
            public void onFailure(Call<ReservationResponseBody> call, Throwable t) {
                onError.accept("Something went wrong");
            }
        });
    }

    public void getReservationsByNic(String nic, Consumer<List<ReservationResponseBody>> onSuccess, Consumer<String> onError) {
        if (!NetworkManager.getInstance().isNetworkAvailable()) {
            onError.accept("No internet connection");
            return;
        }

        reservationService.getReservationsByNic(nic).enqueue(new Callback<List<ReservationResponseBody>>() {
            @Override
            public void onResponse(Call<List<ReservationResponseBody>> call, Response<List<ReservationResponseBody>> response) {
                if (response.isSuccessful()) {
                    List<ReservationResponseBody> reservationResponses = response.body();

                    if (reservationResponses != null && reservationResponses.size() > 0) {
                        // You might want to loop through the list of responses if there are multiple results


                       System.out.println("Reservation response:777777777 " + reservationResponses.get(0).nic);
                        onSuccess.accept(reservationResponses);
                    } else {
                        onError.accept("No reservations yet");
                    }
                } else {
                    onError.accept("Reservation retrieval failed");
                }
            }

            @Override
            public void onFailure(Call<List<ReservationResponseBody>> call, Throwable t) {
                onError.accept("Something went wrong");
            }
        });
    }


    private void saveServerReservations(List<ReservationDto>reservations){
        databaseManager.db().ReservationDao().removeAll();

        List<ReservationEntity> tempEntities = new ArrayList<>();
        for(ReservationDto t : reservations){
            tempEntities.add(ReservationEntity.fromDto(t));
        }

        databaseManager.db().ReservationDao().insertAll(tempEntities);
    }

    private void getReservationsFromServer(Consumer<List<ReservationDto>> onReservationsLoaded, Consumer<String> onError) {
        if (!NetworkManager.getInstance().isNetworkAvailable()){
            onError.accept("No internet connection");
            return;
        }

        try{

            Response<ReservationResponse> response = reservationService.getReservations().execute();
            ReservationResponse reservationResponse = response.body();

            if (reservationResponse.isSuccess()){
                //onReservationsLoaded.accept(reservationResponse.reservations);
                List<ReservationDto> reservations = reservationResponse.getReservations();
                onReservationsLoaded.accept(reservations);
            }
            else{
                onError.accept("Retrieving tasks list from server failed");
            }
        }
        catch(Exception e){
            Log.d("Error", e.toString());
            onError.accept("Unknown error while receiving task list from server");
        }
    }

    /**
     * Check if the local db has any tasks, if so return them. Otherwise,
     * fetch new list from the server.
     */
    public void getReservations(Consumer<List<ReservationEntity>> onReservationsLoaded, Consumer<String> onError) {

        new Thread(() -> {
            ReservationDao dao = databaseManager.db().ReservationDao();
            List<ReservationEntity> dbReservations = dao.getAll();

            if (dbReservations != null && dbReservations.size() > 0){
                MainThreadHelper.onMainThread(() -> onReservationsLoaded.accept(dbReservations));
            }
            else{
                // TODO: Consume the API, store the tasks locally, and call onReservationsLoaded

                if (!NetworkManager.getInstance().isNetworkAvailable()){
                    MainThreadHelper.onMainThread(() -> onError.accept("No internet connection"));
                }

                getReservationsFromServer(
                        serverTasks -> {
                            saveServerReservations(serverTasks);
                            List<ReservationEntity> allReservations = dao.getAll();
                            MainThreadHelper.onMainThread(() -> onReservationsLoaded.accept(allReservations));
                        },
                        error -> MainThreadHelper.onMainThread(() -> onError.accept(error))
                );

            }


        }).start();
    }

    public ReservationStatus getNextReservationStatus(ReservationStatus status){
        switch(status){
            case PENDING: return ReservationStatus.IN_PROGRESS;
            case IN_PROGRESS: return ReservationStatus.COMPLETED;
            default: return null;
        }
    }

    public void updateReservationStatus(ReservationEntity reservation, ReservationStatus nextStatus, Consumer<ReservationEntity> onSuccess, Consumer<String> onError){
        new Thread(new Runnable() {
            @Override
            public void run() {
                reservation.status = nextStatus;

                try{
                    databaseManager.db().ReservationDao().update(reservation);
                    MainThreadHelper.onMainThread(() -> onSuccess.accept(reservation));
                }
                catch(Exception e){
                    Log.e("Update", e.toString());
                    MainThreadHelper.onMainThread(() -> {
                        onError.accept("Error occurred while trying to change task status");
                    });
                }
            }
        }).start();
    }



}
