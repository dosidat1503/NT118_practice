package com.example.lab5;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.media.AudioManager;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class Bai4Activity extends AppCompatActivity {
    private Button btnPlay;
    private TextView tvStatus;
    private MediaPlayer mediaPlayer;
    private String audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
    private boolean isPlaying = false; // Track whether music is playing
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
                new PlayMusicTask().execute(audioUrl);
            }
        });

        // Initialize the SeekBar in your Activity or Fragment:
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        // Get the audio manager
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Set the maximum volume of the SeekBar to the maximum volume of the MediaPlayer:
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        seekBar.setMax(maxVolume);

        // Set the current volume of the SeekBar to the current volume of the MediaPlayer:
        int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekBar.setProgress(currVolume);

        // Add a SeekBar.OnSeekBarChangeListener to the SeekBar:
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
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
    private class PlayMusicTask extends AsyncTask<String, String, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvStatus.setText("Loading...");
        }

        @Override
        protected Void doInBackground(String... strings) {
            String url = strings[0];
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();
            } else {
                mediaPlayer.reset();
            }

            try {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare(); // might take long! (for buffering, etc)
                publishProgress("Playing");
                mediaPlayer.start();
                isPlaying = true;
                runOnUiThread(() -> btnPlay.setText("Pause"));
            } catch (IOException e) {
                e.printStackTrace();
                publishProgress("Error: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            tvStatus.setText(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tvStatus.setText("Playing");
        }
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
