package com.ray.collegecompanion;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class StaffRegister extends AppCompatActivity {

    String Id, name, dept, dob, gender;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_staff_register);


        EditText registerId, registerName, registerDept, registerDob;
        Spinner registerGender;
        Button registerButton;

        registerId = findViewById(R.id.registerUID);
        registerName = findViewById(R.id.registerName);
        registerDept = findViewById(R.id.registerDept);
        registerDob = findViewById(R.id.registerDob);

        registerGender = findViewById(R.id.registerGender);

        registerButton = findViewById(R.id.staffRegisterButton);

        mDatabase = FirebaseDatabase.getInstance("https://test-77be8-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();

        registerButton.setOnClickListener(view -> {
            Id = registerId.getText().toString().trim();
            name = registerName.getText().toString().trim();
            dept = registerDept.getText().toString().trim();
            dob = registerDob.getText().toString().trim();
            gender = registerGender.getSelectedItem().toString();

            Student student = new Student(name,dob,dept,gender);

            mDatabase.child("Students").child(Id).setValue(student).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(StaffRegister.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(StaffRegister.this, "Error Occurred While Adding Data", Toast.LENGTH_SHORT).show();
                }
            });

        });
    }
}