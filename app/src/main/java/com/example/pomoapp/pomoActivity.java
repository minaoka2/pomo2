package com.example.pomoapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class pomoActivity extends AppCompatActivity {
    // 25 minutes to milliseconds
    private static final long START_TIME_IN_MILL = 1500000;

    private TextView textViewCountdown;
    private Button buttonStartPause;
    private Button buttonReset;
    private CountDownTimer countDownTImer;
    private boolean timerRunning;
    private long timeLeftInMill = START_TIME_IN_MILL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pomo_activity);
        buttonStartPause = findViewById(R.id.startPomo);
        buttonReset = findViewById(R.id.resetPomo);
        textViewCountdown = findViewById(R.id.text_view_countdown);

        buttonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });
        updateCountDownText();

    }

    private void startTimer() {
       countDownTImer = new CountDownTimer(timeLeftInMill, 1000) {
           @Override
           public void onTick(long millisUntilFinished) {
               timeLeftInMill = millisUntilFinished;
               updateCountDownText();
           }

           @Override
           public void onFinish() {
                timerRunning = false;
                buttonReset.setText("Resume");

           }
       }.start();
        timerRunning = true;
        buttonStartPause.setText("Pause");
    }
    private void pauseTimer() {
        countDownTImer.cancel();
        timerRunning = false;
        buttonStartPause.setText("Resume");

    }
    private void resetTimer() {
        timeLeftInMill = START_TIME_IN_MILL;
        updateCountDownText();


    }
    private void updateCountDownText() {
        int minute = (int) (timeLeftInMill / 1000) / 60;
        int seconds = (int) (timeLeftInMill / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minute, seconds);
        textViewCountdown.setText(timeLeftFormatted);

    }
}

