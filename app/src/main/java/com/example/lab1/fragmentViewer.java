package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class fragmentViewer extends AppCompatActivity {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_viewer);
        viewPager = findViewById(R.id.viewPager);
        SwipeAdapter adapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}
