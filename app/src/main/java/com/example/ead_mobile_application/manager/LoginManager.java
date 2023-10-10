package com.example.ead_mobile_application.manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.core.util.Consumer;


import com.example.ead_mobile_application.Home;
import com.example.ead_mobile_application.LoginActivity;
import com.example.ead_mobile_application.model.login.LoginRequestBody;
import com.example.ead_mobile_application.model.login.LoginResponse;
import com.example.ead_mobile_application.model.login.LoginService;

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
        loginService = NetworkManager.getInstance().createService(LoginService.class);
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
            Consumer<LoginResponse> onSuccess,
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

                                Context context = ContextManager.getInstance().getApplicationContext();
                                //add toasts messages login is successful
                                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();

                                System.out.print("Navigate to home");

                                onSuccess.accept(loginResponse);



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
        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences.Editor editor = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE).edit();
        editor.putBoolean(isLoggedInKey, isLoggedIn);
        editor.apply();
    }

    public boolean getIsLoggedIn() {
        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences prefs = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE);
        return prefs.getBoolean(isLoggedInKey, false);
    }

    public void setUserDetails(LoginResponse loginResponse) {
        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences.Editor editor = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE).edit();
        editor.putString("nic", loginResponse.getNic());
        editor.putString("name", loginResponse.getName());
        editor.putString("email", loginResponse.getEmail());
        editor.putBoolean("is_active", loginResponse.getIsActive());
        editor.putInt("user_role", loginResponse.getUser_role());
        editor.apply();
    }

    public void logout() {
        setLoggedInState(false);
        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences.Editor editor = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

}
