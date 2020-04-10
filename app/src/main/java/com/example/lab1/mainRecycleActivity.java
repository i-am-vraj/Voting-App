package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class mainRecycleActivity extends AppCompatActivity {


    ArrayList personNames = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7"));
    ArrayList imageId = new ArrayList<>(Arrays.asList(R.drawable.bjp,R.drawable.modiji,R.drawable.modiji,R.drawable.bjp,R.drawable.modiji,R.drawable.modiji,R.drawable.bjp));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycle);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        CustomAdapter customAdapter = new CustomAdapter(mainRecycleActivity.this, personNames, imageId);
        recyclerView.setAdapter(customAdapter);
    }
}
