package com.example.ead_mobile_application.model.register;

public class RegisterRequestBody {
	public String nic;
	public String name;
	public String email;
	public String password;

	public RegisterRequestBody(String nic, String name, String email, String password) {
		this.nic = nic;
		this.name = name;
		this.email = email;
		this.password = password;
	}
}
