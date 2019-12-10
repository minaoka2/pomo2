package com.example.pomoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button task = (Button) findViewById(R.id.tasksAndAssignments);
        Button pomo = findViewById(R.id.Timer);
        task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openTask();
            }
        });
        pomo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimerActivity();
            }
        });
    }

    public void openTask() {
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }
    public void openTimerActivity() {
        Intent intent = new Intent(this, pomoActivity.class);
        startActivity(intent);
    }
}
