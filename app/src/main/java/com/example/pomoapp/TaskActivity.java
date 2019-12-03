package com.example.pomoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class TaskActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);

        final Button remove = findViewById(R.id.RemoveTask);
        final Button add = findViewById(R.id.AddTask);
        remove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                TextView text = findViewById(R.id.listOfTasks);
                text.setText("this will remove a element of the list");
            }
        });
    }
}