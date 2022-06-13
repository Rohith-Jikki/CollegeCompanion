package com.ray.collegecompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CgpaCalculator extends AppCompatActivity {

    String finalValue = "0.00";
    String[] gradeStorage = new String[6];

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa_calculator);

        Spinner gradeOne = findViewById(R.id.grade);
        Spinner gradeTwo = findViewById(R.id.grade2);
        Spinner gradeThree = findViewById(R.id.grade3);
        Spinner gradeFour = findViewById(R.id.grade4);
        Spinner gradeFive = findViewById(R.id.grade5);
        Spinner gradeSix = findViewById(R.id.grade6);

        TextView resultValue = findViewById(R.id.cgpa);
        Button Result = findViewById(R.id.Result);
        Result.setOnClickListener(view -> {
            gradeStorage[0] = gradeOne.getSelectedItem().toString();
            gradeStorage[1] = gradeTwo.getSelectedItem().toString();
            gradeStorage[2] = gradeThree.getSelectedItem().toString();
            gradeStorage[3] = gradeFour.getSelectedItem().toString();
            gradeStorage[4] = gradeFive.getSelectedItem().toString();
            gradeStorage[5] = gradeSix.getSelectedItem().toString();
            finalValue = String.format("%.2f",cgpa(gradeStorage));
            resultValue.setText(finalValue);
        });
    }

    public static float cgpa(String[] grades){
        float result;
        float temp = 0;
        for (String value: grades) {
            switch (value){
                case "O":
                    temp += 10;
                    break;
                case "A+":
                    temp += 9;
                    break;
                case "A":
                    temp += 8;
                    break;
                case "B+":
                    temp += 7;
                    break;
                case "B":
                    temp += 6;
                    break;
                case "C":
                    temp += 5;
                    break;
                case "U":
                    temp += 0;
                    break;
            }
        }
        result = temp / 6;
        return result;
    }
}