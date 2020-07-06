package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSetPoint, btnLaptopSheet, btnStaySheet, btnCheckSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSetPoint = findViewById(R.id.btnSetPoint);
        btnLaptopSheet = findViewById(R.id.laptopNameSheet);
        btnStaySheet = findViewById(R.id.stayNameSheet);
        btnCheckSong = findViewById(R.id.btnCheckSong);

        btnSetPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetPointActivity.class);
                startActivity(intent);
            }
        });

        btnLaptopSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheckLaptop.class);
                startActivity(intent);
            }
        });

        btnStaySheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheckStay.class);
                startActivity(intent);
            }
        });
        btnCheckSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheckSong.class);
                startActivity(intent);
            }
        });

    }
}
