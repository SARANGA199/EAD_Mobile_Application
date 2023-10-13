package com.example.ead_mobile_application.models.update;

public class UpdateRequestBody {
    public String nic;
    public String name;
    public String email;
    public String password;

    public UpdateRequestBody(String nic, String name, String email, String password) {
        this.nic = nic;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
