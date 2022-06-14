package com.ray.collegecompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class Map extends AppCompatActivity {
    ImageView map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Button firstFloor, secondFloor, thirdFloor, groundFloor;

        map = findViewById(R.id.mapImage);

        firstFloor = findViewById(R.id.firstFloorButton);
        firstFloor.setOnClickListener(view -> map.setImageResource(R.drawable.ic_first_floor));

        secondFloor = findViewById(R.id.secondFloorButton);
        secondFloor.setOnClickListener(view -> map.setImageResource(R.drawable.ic_second_floor));

        thirdFloor = findViewById(R.id.thirdFloorButton);
        thirdFloor.setOnClickListener(view -> map.setImageResource(R.drawable.ic_third_floor));

        groundFloor = findViewById(R.id.groundFloorButton);
        groundFloor.setOnClickListener(view -> map.setImageResource(R.drawable.ic_ground_floor));
    }
}