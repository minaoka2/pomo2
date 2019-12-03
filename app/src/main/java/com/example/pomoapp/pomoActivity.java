package com.example.pomoapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class pomoActivity extends AppCompatActivity {
    private Button start;
    private Button reset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pomo_activity);
        final TextView timer = findViewById(R.id.timerDisplay);
        start = findViewById(R.id.startPomo);
        reset = findViewById(R.id.resetPomo);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timer.setText("this will start the timer");
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.setText("this will reset the timer");
            }
        });

    }
}
