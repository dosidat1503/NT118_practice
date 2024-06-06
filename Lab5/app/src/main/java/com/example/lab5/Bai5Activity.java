package com.example.lab5;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Bai5Activity extends AppCompatActivity {
    private Button btnPlay;
    private TextView tvStatus;
    private MediaPlayer mediaPlayer;
    private String audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
    private boolean isPlaying = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai4);

        btnPlay = findViewById(R.id.btnPlay);
        tvStatus = findViewById(R.id.tvStatus);

        btnPlay.setOnClickListener(v -> {
            if (isPlaying) {
                pauseMusic();
            } else {
                playMusic(audioUrl);
            }
        });

        SeekBar seekBar = findViewById(R.id.seekBar);
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        seekBar.setMax(maxVolume);
        int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekBar.setProgress(currVolume);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do Nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do Nothing
            }
        });
    }

    private void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPlaying = false;
            tvStatus.setText("Paused");
            btnPlay.setText("Play");
        }
    }

    private void playMusic(String url) {
        tvStatus.setText("Loading...");
//        Khi nhấn nút phát, một Observable sẽ được tạo để phát nhạc trong luồng nền (Schedulers.io()).
//        Observable.fromCallable(() -> { ... }) là một phần của RxJava
//        Nó cho phép bạn tạo một Observable từ một Callable,
//        tức là một đoạn mã sẽ được thực thi khi có người đăng ký (subscribe) vào Observable này.
        Observable.fromCallable(() -> {
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();
            } else {
                mediaPlayer.reset();
            }

            try {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
                mediaPlayer.start();
                return "Playing";
            } catch (IOException e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        })
//      Khi nhấn nút phát, một Observable sẽ được tạo để phát nhạc trong luồng nền (Schedulers.io()).
        .subscribeOn(Schedulers.io())
        .subscribe(status -> {
//      Kết quả sẽ được xử lý và cập nhật giao diện người dùng trong luồng chính thông qua Handler.
            handler.post(() -> {
                isPlaying = status.equals("Playing");
                tvStatus.setText(status);
                btnPlay.setText(isPlaying ? "Pause" : "Play");
            });
        });
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}