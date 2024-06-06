package com.example.lab5;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Bai1Activity extends AppCompatActivity {
    private ProgressBar pbFirst, pbSecond;
    private TextView tvMsgWorking, tvMsgReturned;
    private boolean isRunning;
    private int MAX_SEC;

    private int intTest;
    private Thread bgThread;
    private Handler handler;
    private Button btnStart;

    private void findViewByIds() {
        pbFirst = (ProgressBar) findViewById(R.id.pb_first);
        pbSecond = (ProgressBar) findViewById(R.id.pb_second);
        tvMsgWorking = (TextView) findViewById(R.id.tv_working);
        tvMsgReturned = (TextView) findViewById(R.id.tv_return);
        btnStart = (Button) findViewById(R.id.btn_start);
    }

    private void initVariables() {
        isRunning = false;
        MAX_SEC = 20;
        intTest = 1;
        pbFirst.setMax(MAX_SEC);
        pbFirst.setProgress(0);

        // Init Views
        pbFirst.setVisibility(View.GONE);
        pbSecond.setVisibility(View.GONE);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                String returnedValue = (String) msg.obj;

                // Do something with the value sent by background thread here...
                tvMsgReturned.setText(getString(R.string.returned_by_bg_thread) + returnedValue);
                pbFirst.incrementProgressBy(2);
            }
        };
    }

    private void initBgThread() {
        bgThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; 1 < MAX_SEC & isRunning; i++) {
                        // Sieep one second
                        Thread.sleep(1000);
                        Random rnd = new Random();
                        // This is a locally generated value
                        String data = "Thread value: " + (int) rnd.nextInt(101);
                        // We can see changk (global) class variables
                        data += getString(R.string.global_value_seen) + " " + intTest;
                        intTest++;
                        // 1f thread is still alive send the message
                        if (isRunning) {
                            // Request a message token and put seme data in it
                            Message msg = handler.obtainMessage(1, (String) data);
                            handler.sendMessage(msg);
                        }
                    }
                } catch (Throwable t) {
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai1);
        findViewByIds();
        initVariables();
        // Handle clickListenner
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = true;
                pbFirst.setVisibility(View.VISIBLE);
                pbSecond.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.GONE);
                bgThread.start();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initBgThread();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }
}