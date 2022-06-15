package com.ray.collegecompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        Button cgpaBtn, notesBtn, dataBtn, registerBtn,mapBtn;

        cgpaBtn = findViewById(R.id.cgpaButton);
        cgpaBtn.setOnClickListener(this);

        notesBtn = findViewById(R.id.notesButton);
        notesBtn.setOnClickListener(this);

        dataBtn = findViewById(R.id.dataButton);
        dataBtn.setOnClickListener(this);

        registerBtn = findViewById(R.id.registerMenuButton);
        registerBtn.setOnClickListener(this);

        mapBtn = findViewById(R.id.Map);
        mapBtn.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cgpaButton:
                startActivity(new Intent(Menu.this,CgpaCalculator.class));
                break;
            case R.id.notesButton:
                startActivity(new Intent(Menu.this, Notes.class));
                break;
            case R.id.dataButton:
                startActivity(new Intent(Menu.this, data.class));
                break;
            case R.id.registerMenuButton:
                startActivity(new Intent(Menu.this, RegisterLogin.class));
                break;
            case R.id.Map:
                startActivity(new Intent(Menu.this, Map.class));
                break;
        }
    }
}