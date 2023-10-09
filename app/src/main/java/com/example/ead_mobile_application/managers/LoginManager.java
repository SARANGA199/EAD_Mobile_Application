package com.example.ead_mobile_application.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.core.util.Consumer;


import com.example.ead_mobile_application.managers.ContextManager;
import com.example.ead_mobile_application.managers.NetworkManager;
import com.example.ead_mobile_application.models.login.LoginRequestBody;
import com.example.ead_mobile_application.models.login.LoginResponse;
import com.example.ead_mobile_application.models.login.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginManager {
    private static LoginManager singleton;
    private LoginService loginService;
    private final String loginStateFile = "loginstate";
    private final String isLoggedInKey = "logged_in";

    public static LoginManager getInstance() {
        if (singleton == null)
            singleton = new LoginManager();

        return singleton;
    }

    private LoginManager() {
        loginService = com.example.ead_mobile_application.managers.NetworkManager.getInstance().createService(LoginService.class);
    }

    public Boolean validateCredentials(String nic, String password) {
        if (nic == null || nic.length() == 0)
            return false;

        if (password == null || password.length() == 0)
            return false;

        return true;
    }

    public void login(
            String nic,
            String password,
            Runnable onSuccess,
            Consumer<String> onError
    ) {
        if (!NetworkManager.getInstance().isNetworkAvailable()) {
            onError.accept("No internet connectivity");
            return;
        }

        LoginRequestBody body = new LoginRequestBody(nic, password);
        loginService.login(body).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            LoginResponse loginResponse = response.body();
                            if (loginResponse != null) {

                                System.out.println("\nNIC: " + loginResponse.getNic());
                                System.out.println("Name: " + loginResponse.getName());
                                System.out.println("Email: " + loginResponse.getEmail());

                                Context context = com.example.ead_mobile_application.managers.ContextManager.getInstance().getApplicationContext();
                                //add toasts messages login is successful
                                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();

                                System.out.print("Navigate to home");




                            } else {
                                onError.accept("Unknown error occurred while logging in");
                            }
                        } else {
                            // Handle unsuccessful response
                            if (response.code() == 404) {
                                // User not found
                                onError.accept("User with NIC " + nic + " not found");
                                System.out.println("User with NIC " + nic + " not found");
                            } else {
                                // Other errors, including "Incorrect Nic or password"
                                onError.accept("NIC or password is incorrect");
                                System.out.println("NIC or password is incorrect");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        onError.accept("Unknown error occurred while logging in");

                    }
                });

    }

    public void setLoggedInState(boolean isLoggedIn) {
        Context context = com.example.ead_mobile_application.managers.ContextManager.getInstance().getApplicationContext();
        SharedPreferences.Editor editor = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE).edit();
        editor.putBoolean(isLoggedInKey, isLoggedIn);
        editor.apply();
    }

    public boolean getIsLoggedIn() {
        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences prefs = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE);
        return prefs.getBoolean(isLoggedInKey, false);
    }
}
