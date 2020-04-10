package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class showStudentDetails extends AppCompatActivity {

    static final int REQ = 1;
    TextView roll,sub1,sub2,sub3,sub4,sub5,total,percent;
    Button goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student_details);

        roll = findViewById(R.id.rollnod);
        sub1 = findViewById(R.id.sub1d);
        sub2 = findViewById(R.id.sub2d);
        sub3 = findViewById(R.id.sub3d);
        sub4 = findViewById(R.id.sub4d);
        sub5 = findViewById(R.id.sub5d);
        total = findViewById(R.id.total);
        percent = findViewById(R.id.percent);
        goBack = findViewById(R.id.back);

        Intent intent = getIntent();

        String sroll = intent.getStringExtra("roll");
        String ssub1 = intent.getStringExtra("sub1");
        String ssub2 = intent.getStringExtra("sub2");
        String ssub3 = intent.getStringExtra("sub3");
        String ssub4 = intent.getStringExtra("sub4");
        String ssub5 = intent.getStringExtra("sub5");
        String stotal = "" + (Integer.parseInt(ssub1) + Integer.parseInt(ssub2) + Integer.parseInt(ssub3) + Integer.parseInt(ssub4) + Integer.parseInt(ssub5));
        String spercent = "" + (Integer.parseInt(stotal)/5);

        roll.setText(sroll);
        sub1.setText(ssub1);
        sub2.setText(ssub2);
        sub3.setText(ssub3);
        sub4.setText(ssub4);
        sub5.setText(ssub5);
        total.setText(stotal);
        percent.setText(spercent);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
