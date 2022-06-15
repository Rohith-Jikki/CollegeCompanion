package com.ray.collegecompanion;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Notes extends AppCompatActivity implements View.OnClickListener{

    Button subjectOneDown, subjectTwoDown, subjectThreeDown, subjectFourDown, subjectFiveDown, subjectSixDown;
    public static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notes);

        subjectOneDown = findViewById(R.id.subjectOneNotes);
        subjectOneDown.setOnClickListener(this);

        subjectTwoDown = findViewById(R.id.subjectTwoNotes);
        subjectTwoDown.setOnClickListener(this);

        subjectThreeDown = findViewById(R.id.subjectThreeNotes);
        subjectThreeDown.setOnClickListener(this);

        subjectFourDown = findViewById(R.id.subjectFourNotes);
        subjectFourDown.setOnClickListener(this);

        subjectFiveDown = findViewById(R.id.subjectFiveNotes);
        subjectFiveDown.setOnClickListener(this);

        subjectSixDown = findViewById(R.id.subjectSixNotes);
        subjectSixDown.setOnClickListener(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }

    }
    public void downloadNotes(String token, String fileName){

        DownloadManager downloads = (DownloadManager)  getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(token);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName);
        long reference = downloads.enqueue(request);

        Toast.makeText(Notes.this, "File is Downloaded", Toast.LENGTH_SHORT).show();

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.subjectOneNotes:
                downloadNotes("https://firebasestorage.googleapis.com/v0/b/test-77be8.appspot.com/o/Subject1.pdf?alt=media&token=94d8c799-1075-4e24-85d7-78037cfc323f","subjectOne.pdf");
                break;

            case R.id.subjectTwoNotes:
                downloadNotes("https://firebasestorage.googleapis.com/v0/b/test-77be8.appspot.com/o/Subject1.pdf?alt=media&token=94d8c799-1075-4e24-85d7-78037cfc323f","subjectTwo.pdf");
                break;

            case R.id.subjectThreeNotes:
                downloadNotes("https://firebasestorage.googleapis.com/v0/b/test-77be8.appspot.com/o/Subject1.pdf?alt=media&token=94d8c799-1075-4e24-85d7-78037cfc323f","subjectThree.pdf");
                break;
            case R.id.subjectFourNotes:
                downloadNotes("https://firebasestorage.googleapis.com/v0/b/test-77be8.appspot.com/o/Subject1.pdf?alt=media&token=94d8c799-1075-4e24-85d7-78037cfc323f","subjectFour.pdf");
                break;
            case R.id.subjectFiveNotes:
                downloadNotes("https://firebasestorage.googleapis.com/v0/b/test-77be8.appspot.com/o/Subject1.pdf?alt=media&token=94d8c799-1075-4e24-85d7-78037cfc323f","subjectFive.pdf");
                break;
            case R.id.subjectSixNotes:
                downloadNotes("https://firebasestorage.googleapis.com/v0/b/test-77be8.appspot.com/o/Subject1.pdf?alt=media&token=94d8c799-1075-4e24-85d7-78037cfc323f","subjectSix.pdf");
                break;

        }
    }
}