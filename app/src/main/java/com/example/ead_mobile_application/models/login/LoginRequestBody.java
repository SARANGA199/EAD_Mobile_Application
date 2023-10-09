package com.example.ead_mobile_application.models.login;

public class LoginRequestBody {
    public String nic;
    public String password;

    public LoginRequestBody(String nic, String password) {
        this.nic = nic;
        this.password = password;
    }
}
