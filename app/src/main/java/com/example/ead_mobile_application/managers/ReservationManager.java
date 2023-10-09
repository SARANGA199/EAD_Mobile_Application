package com.example.ead_mobile_application.managers;

import android.util.Log;

import com.example.ead_mobile_application.models.reservation.ReservationDao;
import com.example.ead_mobile_application.models.reservation.ReservationDto;
import com.example.ead_mobile_application.models.reservation.ReservationEntity;
import com.example.ead_mobile_application.models.reservation.ReservationResponse;
import com.example.ead_mobile_application.models.reservation.ReservationService;
import com.example.ead_mobile_application.models.reservation.ReservationStatus;
import com.example.ead_mobile_application.utilities.MainThreadHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import retrofit2.Response;

public class ReservationManager {

    private static ReservationManager singleton;
    private DatabaseManager databaseManager;
    private ReservationService taskService;

    public static ReservationManager getInstance(){
        if (singleton == null)
            singleton = new ReservationManager();
        return singleton;
    }

    private ReservationManager(){
        databaseManager = DatabaseManager.getInstance();
        taskService = NetworkManager.getInstance().createService(ReservationService.class);
    }

    private void saveServerReservations(List<ReservationDto> serverTasks){
        databaseManager.db().ReservationDao().removeAll();

        List<ReservationEntity> tempEntities = new ArrayList<>();
        for(ReservationDto t : serverTasks){
            tempEntities.add(ReservationEntity.fromDto(t));
        }

        databaseManager.db().ReservationDao().insertAll(tempEntities);
    }

    private void getReservationsFromServer(Consumer<List<ReservationDto>> onTasksLoaded, Consumer<String> onError) {
        if (!NetworkManager.getInstance().isNetworkAvailable()){
            onError.accept("No internet connection");
            return;
        }

        try{
            Response<ReservationResponse> response = taskService.tasks().execute();
            ReservationResponse taskResponse = response.body();

            if (taskResponse.success){
                onTasksLoaded.accept(taskResponse.tasks);
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
    public void getReservations(Consumer<List<ReservationEntity>> onTasksLoaded, Consumer<String> onError) {

        new Thread(() -> {
            ReservationDao dao = databaseManager.db().ReservationDao();
            List<ReservationEntity> dbTasks = dao.getAll();

            if (dbTasks != null && dbTasks.size() > 0){
                MainThreadHelper.onMainThread(() -> onTasksLoaded.accept(dbTasks));
            }
            else{
                // TODO: Consume the API, store the tasks locally, and call onTasksLoaded

                if (!NetworkManager.getInstance().isNetworkAvailable()){
                    MainThreadHelper.onMainThread(() -> onError.accept("No internet connection"));
                }

                getReservationsFromServer(
                        serverTasks -> {
                            saveServerReservations(serverTasks);
                            List<ReservationEntity> allTasks = dao.getAll();
                            MainThreadHelper.onMainThread(() -> onTasksLoaded.accept(allTasks));
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

    public void updateReservationStatus(ReservationEntity task, ReservationStatus nextStatus, Consumer<ReservationEntity> onSuccess, Consumer<String> onError){
        new Thread(new Runnable() {
            @Override
            public void run() {
                task.status = nextStatus;

                try{
                    databaseManager.db().ReservationDao().update(task);
                    MainThreadHelper.onMainThread(() -> onSuccess.accept(task));
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
