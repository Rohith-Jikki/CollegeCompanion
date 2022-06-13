package com.ray.collegecompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private TextView banner;
    private EditText EditTextUsername, EditTextAge, EditTextEmail, EditTextPassword;
    private ProgressBar progressBar;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        banner = findViewById(R.id.titleR);
        banner.setOnClickListener(this);

        register = findViewById(R.id.registerButton);
        register.setOnClickListener(this);

        EditTextUsername = findViewById(R.id.name);
        EditTextAge = findViewById(R.id.age);
        EditTextEmail = findViewById(R.id.emailR);
        EditTextPassword = findViewById(R.id.passwordR);

        progressBar = findViewById(R.id.progressbarR);
    }

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
        String age = EditTextAge.getText().toString().trim();
        String userName = EditTextUsername.getText().toString().trim();

        if(userName.isEmpty()){
            EditTextUsername.setError("Name is required");
            EditTextUsername.requestFocus();
            return;
        }

        if (age.isEmpty()){
            EditTextAge.setError("Age is required");
            EditTextAge.requestFocus();
            return;
        }

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
                        progressBar.setVisibility(View.GONE);
                    }
                    else{
                        Toast.makeText(Register.this,"Registration Unsuccessful",Toast.LENGTH_LONG).show();
                    }
                });

    }
}