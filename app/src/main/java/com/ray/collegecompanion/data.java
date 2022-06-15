package com.ray.collegecompanion;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class data extends AppCompatActivity {

    DatabaseReference reference;
    Button getData;
    EditText editTextId;
    TextView nameValue, dobValue, genderValue, deptValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data);

        getData = findViewById(R.id.getDataButton);
        editTextId = findViewById(R.id.searchData);

        nameValue = findViewById(R.id.nameHolder);
        dobValue = findViewById(R.id.dobHolder);
        genderValue = findViewById(R.id.genderHolder);
        deptValue = findViewById(R.id.deptHolder);

        getData.setOnClickListener(view -> {
            String name = editTextId.getText().toString();
            if(!name.isEmpty()){
                readData(name);
            }
            else{
                Toast.makeText(data.this,"Enter a Name!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void readData(String name){
        reference = FirebaseDatabase.getInstance("https://test-77be8-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Students");
        reference.child(name).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                if(task.getResult().exists()){
                    Toast.makeText(data.this,"Successfully Read Data",Toast.LENGTH_SHORT).show();
                    DataSnapshot dataSnapshot = task.getResult();
                    String studentName = String.valueOf(dataSnapshot.child("name").getValue());
                    String studentDob = String.valueOf(dataSnapshot.child("dob").getValue());
                    String studentGender = String.valueOf(dataSnapshot.child("gender").getValue());
                    String studentDept = String.valueOf(dataSnapshot.child("dept").getValue());
                    nameValue.setText(studentName);
                    dobValue.setText(studentDob);
                    genderValue.setText(studentGender);
                    deptValue.setText(studentDept);
                }
                else{
                    Toast.makeText(data.this, "User Doesn't Exist", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(data.this, "Failed to read", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> Toast.makeText(data.this, "Failed to collect", Toast.LENGTH_SHORT).show());
    }
}