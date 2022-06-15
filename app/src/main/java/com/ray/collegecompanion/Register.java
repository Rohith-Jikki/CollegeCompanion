package com.ray.collegecompanion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private EditText EditTextEmail, EditTextPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        TextView banner = findViewById(R.id.titleR);
        banner.setOnClickListener(this);

        Button register = findViewById(R.id.registerButton);
        register.setOnClickListener(this);

        EditTextEmail = findViewById(R.id.emailR);
        EditTextPassword = findViewById(R.id.passwordR);

        progressBar = findViewById(R.id.progressbarR);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.titleR:
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.registerButton:
                registerUser();
                break;
        }
    }

    public void registerUser(){
        String email = EditTextEmail.getText().toString().trim();
        String password = EditTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            EditTextEmail.setError("Email is required");
            EditTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EditTextEmail.setError("Enter a valid Email");
            EditTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            EditTextPassword.setError("Password is required");
            EditTextPassword.requestFocus();
            return;
        }
        if (password.length() < 8){
            EditTextPassword.setError("Password length must be at least 8 characters");
            EditTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(Register.this,"Registration Complete",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(Register.this,"Registration Unsuccessful",Toast.LENGTH_LONG).show();
                    }
                    progressBar.setVisibility(View.GONE);
                });

    }
}