package com.example.pomoapp;

import android.content.Intent;
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
            @Override
            public void onClick(View view) {
                TextView text = findViewById(R.id.listOfTasks);
                text.setText("Task removed");
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });

    }
    public void addTask() {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }
}