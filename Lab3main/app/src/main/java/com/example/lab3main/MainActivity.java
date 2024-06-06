package com.example.lab3main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button phan1bai1 = findViewById(R.id.phan1bai1);
        Button phan1bai22 = findViewById(R.id.phan1bai22);
        Button phan1bai23 = findViewById(R.id.phan1bai23);
        Button phan2 = findViewById(R.id.phan2);

        phan1bai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Phan1Bai1Activity.class);
                startActivity(intent);
            }
        });

        phan1bai22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Phan1Bai22Activity.class);
                startActivity(intent);
            }
        });
//
        phan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Phan2Activity.class);
                startActivity(intent);
            }
        });

        phan1bai23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Phan1Bai23Activity.class);
                startActivity(intent);
            }
        });

    }
}