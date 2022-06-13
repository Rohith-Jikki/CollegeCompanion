package com.ray.collegecompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private EditText email, password;
    private Button Login;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    Intent gotoMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.Register);
        register.setOnClickListener(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        Login = findViewById(R.id.submit);
        Login.setOnClickListener(this);

        progressBar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Register:
                startActivity(new Intent(this,Register.class));
                break;
            case R.id.submit:
                userLogin();
        }
    }

    private void userLogin() {
        String EditTextEmail = email.getText().toString().trim();
        String EditTextPassword = password.getText().toString().trim();

        if (EditTextEmail.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(EditTextEmail).matches()){
            email.setError("Enter a valid Email");
            email.requestFocus();
            return;
        }
        if(EditTextPassword.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if (EditTextPassword.length() < 8){
            password.setError("Password length must be at least 8 characters");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(EditTextEmail,EditTextPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    gotoMenu = new Intent(MainActivity.this,Menu.class);
                    startActivity(gotoMenu);
                    progressBar.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(MainActivity.this,"Check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}