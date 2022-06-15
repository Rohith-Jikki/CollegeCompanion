package com.ray.collegecompanion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText email, password;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    Intent gotoMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        Button login = findViewById(R.id.submit);
        login.setOnClickListener(view -> userLogin());

        progressBar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

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

        mAuth.signInWithEmailAndPassword(EditTextEmail,EditTextPassword).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                gotoMenu = new Intent(MainActivity.this,Menu.class);
                startActivity(gotoMenu);
            }
            else{
                Toast.makeText(MainActivity.this,"Check your credentials", Toast.LENGTH_LONG).show();
            }
            progressBar.setVisibility(View.GONE);
        });
    }
}