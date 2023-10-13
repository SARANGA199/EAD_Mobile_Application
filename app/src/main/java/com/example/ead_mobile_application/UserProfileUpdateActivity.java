package com.example.ead_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ead_mobile_application.managers.ContextManager;
import com.example.ead_mobile_application.managers.DatabaseManager;
import com.example.ead_mobile_application.managers.LoginManager;
import com.example.ead_mobile_application.managers.RegisterManager;
import com.example.ead_mobile_application.models.login.LoginDao;
import com.example.ead_mobile_application.models.login.LoginEntity;

import java.util.List;

public class UserProfileUpdateActivity extends AppCompatActivity {
    private EditText nic;
    private EditText Name;
    private EditText Email;
    private EditText password;
    private EditText confirmPassword;
    private Button register;

    private RegisterManager registerManager;
    private DatabaseManager databaseManager;

    private LoginManager loginManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_update);

        nic = findViewById(R.id.nic);
        Name = findViewById(R.id.name);
        Email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.re_password);
        register = findViewById(R.id.signup_btn);

        //To register the user
        register.setOnClickListener(view -> update());

        ContextManager.getInstance().setApplicationContext(this.getApplicationContext());
        registerManager = RegisterManager.getInstance();
        databaseManager = DatabaseManager.getInstance();
        loginManager = LoginManager.getInstance();

        new AsyncTask<Void, Void, LoginEntity>() {
            @Override
            protected LoginEntity doInBackground(Void... voids) {
                LoginDao dao = databaseManager.db().LoginDao();
                List<LoginEntity> loginEntity = dao.get();
                return (loginEntity != null && loginEntity.size() > 0) ? loginEntity.get(0) : null;
            }

            @Override
            protected void onPostExecute(LoginEntity loginEntity) {
                if (loginEntity != null) {
                    // Update UI with retrieved data
                    nic.setText(loginEntity.nic);
                    Name.setText(loginEntity.name);
                    Email.setText(loginEntity.email);
                } else {
                    // Handle the case where no data is found
                    // You may want to display a message or take appropriate action
                }
            }
        }.execute();
    }

    private void update() {
        String nic = this.nic.getText().toString();
        String name = this.Name.getText().toString();
        String email = this.Email.getText().toString();
        String password = this.password.getText().toString();
        String confirmPassword = this.confirmPassword.getText().toString();

        if (nic.isEmpty()) {
            this.nic.setError("NIC is required");
            this.nic.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            this.Name.setError("Name is required");
            this.Name.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            this.Email.setError("Email is required");
            this.Email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            this.password.setError("Password is required");
            this.password.requestFocus();
            return;
        }else if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            this.password.setError("Password should contain at least 8 characters, 1 uppercase, 1 lowercase, 1 number and 1 special character");
            this.password.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            this.confirmPassword.setError("Confirm Password is required");
            this.confirmPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            this.confirmPassword.setError("Password and Confirm Password should be same");
            this.confirmPassword.requestFocus();
            return;
        }

        registerManager.updateProfile(nic, name, email, password, () -> {
            Toast.makeText(getApplicationContext(), "User update successfully", Toast.LENGTH_SHORT).show();
            loginManager = LoginManager.getInstance();
            loginManager.logout();
        }, error -> {
            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
        });
    }
}