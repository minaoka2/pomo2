package com.example.pomoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //this is committing correctly
    private Button task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        task = (Button) findViewById(R.id.tasksAndAssignments);
        task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openTask();
            }
        });
    }

    public void openTask() {
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }
}
