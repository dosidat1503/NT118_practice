package com.example.lab5;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Bai2Activity  extends AppCompatActivity {
    private ProgressBar pbWaiting;
    private TextView tvTopCaption;
    private EditText etInput;
    private Button btnExecute;
    private int globalValue, accum;
    private long startTime;
    private final String PATIENCE = "Some important data is being collected now.\nPlease be patient...wait...";
    private Handler handler;
    private Runnable fgRunnable, bgRunnable;
    private Thread testThread;

    private void findViewByIds() {
        tvTopCaption = (TextView) findViewById(R.id.tv_top_caption);
        pbWaiting = (ProgressBar) findViewById(R.id.pb_waiting);
        etInput = (EditText) findViewById(R.id.et_input);
        btnExecute = (Button) findViewById(R.id.btn_execute);
    }

    private void initVariables() {
        globalValue = 0;
        accum = 0;
        startTime = System.currentTimeMillis();
        handler = new Handler();

        fgRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // Calculate nev valve
                    int progressStep = 5;
                    double totalTime = (System.currentTimeMillis() - startTime) / 1000;
                    synchronized (this) {
                        globalValue += 100;
                    }
                    // Update UI
                    tvTopCaption.setText(PATIENCE + totalTime + " - " + globalValue);
                    pbWaiting.incrementProgressBy(progressStep);
                    accum += progressStep;
                    // Check to stop
                    if (accum >= pbWaiting.getMax()) {
                        tvTopCaption.setText(getString(R.string.bg_work_is_over));
                        pbWaiting.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    Log.e("fgRunnable", e.getMessage());
                }
            }
        };

        bgRunnable = new Runnable () {
            @Override
            public void run() {
                try {
                    for (int i = 0; 1 < 20; i++) {
                        // Sleep 1 second
                        Thread.sleep(1000);
                        // ¥ow talk to main thread
                        // Optinally change some global variable such as: globalValue
                        synchronized (this) {
                            globalValue += 1;
                        }
                        handler.post(fgRunnable);
                    }
                } catch (Exception e) {
                    Log.e("bgRunnable", e.getMessage());
                }
            }
        };
        testThread = new Thread (bgRunnable) ;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai2);
        findViewByIds();
        initVariables();
        // Hendle onClickListenner
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etInput.getText().toString();
                Toast.makeText(Bai2Activity.this, "You said: " + input, Toast.LENGTH_SHORT).show();
            }
        });
        // Start thread
        testThread.start();
        pbWaiting.incrementProgressBy(0);
    }
}
