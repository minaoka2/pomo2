package com.example.pomoapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.UrlQuerySanitizer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class pomoActivity extends AppCompatActivity {
    private final String CHANNEL_ID = "Channel1";
    private final int NOTIFICATION_ID = 001;
    // 25 minutes to milliseconds
    private static final long START_TIME_IN_MILL = 12000;

    private TextView textViewCountdown;
    private Button buttonStartPause;
    private Button buttonReset;
    private CountDownTimer countDownTImer;
    private boolean timerRunning;
    private long timeLeftInMill = START_TIME_IN_MILL;
    private NotificationManagerCompat notificationManager;

    private static HttpURLConnection connection;

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
        notificationManager = NotificationManagerCompat.from(this);
        createNotificationChannels();

    }

    private static String requestQuote() throws Exception {
        URL url = new URL("https://quotes.rest/qod?category=inspire");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStream input = connection.getInputStream();
        String quote = input.toString();
        return quote;
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
                buttonReset.setText("Reset");
                buttonStartPause.setText("Start");
                // set up notification here.
               sendNotificationChannel();

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
    private void sendNotificationChannel() {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_timer_notification)
                .setContentTitle("Timer is complete")
                .setContentText("take a quick break, you deserve it!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }
    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel1.setDescription("this is channel 1");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);

        }

    }
}

