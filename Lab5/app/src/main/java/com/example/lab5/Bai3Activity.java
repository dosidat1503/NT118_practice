package com.example.lab5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bai3Activity extends AppCompatActivity {
    private Button btnQuickJob, btnSlowJob;
    private TextView tvStatus;
    private Bai3_SlowTask slowTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai3);
        findViewByIds();
// Init slowtask
        slowTask=new Bai3_SlowTask(Bai3Activity.this, tvStatus);
// Handle onClickListenner
        btnQuickJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                tvStatus.setText(sdf.format(new Date()));
            }
        });
        btnSlowJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slowTask.execute();
            }
        });
    }

    private void findViewByIds() {
        btnQuickJob = (Button) findViewById(R.id.btn_quick_job);
        btnSlowJob = (Button) findViewById(R.id.btn_slow_job);
        tvStatus = (TextView) findViewById(R.id.tv_status);
    }
}
