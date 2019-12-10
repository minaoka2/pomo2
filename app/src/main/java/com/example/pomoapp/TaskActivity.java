package com.example.pomoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;



public class TaskActivity extends AppCompatActivity {
    List<String> toDoList;

    ListView listView;

    ArrayAdapter<String> arrayAdapter;
    EditText input;
    Button add;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);
        add = findViewById(R.id.addButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
        toDoList = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<>(this, R.layout.list_layout, toDoList);
        listView = findViewById(R.id.listView);

        listView.setAdapter(arrayAdapter);
        input = findViewById(R.id.input);

    }

    public void addItem() {
        toDoList.add(input.getText().toString());
        arrayAdapter.notifyDataSetChanged();

        input.setText("");
    }

}