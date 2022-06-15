package com.ray.collegecompanion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

//Create Two roles
//Main Admin
//Staff role
//Check role

public class RegisterLogin extends AppCompatActivity {

    private String role, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_login);

        EditText  lusername, lpassword;
        lusername = findViewById(R.id.registerId);
        lpassword = findViewById(R.id.registerPassword);

        Button registerLogin = findViewById(R.id.registerLogin);
        registerLogin.setOnClickListener(view -> {
            role = lusername.getText().toString().trim();
            password = lpassword.getText().toString().trim();
            if(role.equals("admin")){
                if(password.equals("admin")){
                    startActivity(new Intent(RegisterLogin.this, Register.class));
                }
                else{
                    lpassword.setError("Incorrect Password");
                    lpassword.requestFocus();
                }
            }
            else if(role.equals("staff")){
                    if(password.equals("staff")){
                        startActivity(new Intent(RegisterLogin.this, StaffRegister.class));
                }
                else{
                    lpassword.setError("Incorrect Password");
                    lpassword.requestFocus();
                }
            }
        });
    }
}