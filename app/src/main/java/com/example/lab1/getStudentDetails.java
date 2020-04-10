package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class getStudentDetails extends AppCompatActivity {

    static final int REQ = 1;
    EditText roll,sub1,sub2,sub3,sub4,sub5;
    Button showbDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_student_details);

        roll = findViewById(R.id.rollno);
        sub1 = findViewById(R.id.sub1);
        sub2 = findViewById(R.id.sub2);
        sub3 = findViewById(R.id.sub3);
        sub4 = findViewById(R.id.sub4);
        sub5 = findViewById(R.id.sub5);
        showbDetails = findViewById(R.id.showDetails);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roll.setTextColor(getResources().getColor(R.color.black));
                roll.setText("");
            }
        });

        showbDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sroll = roll.getText().toString();
                String ssub1 = sub1.getText().toString();
                String ssub2 = sub2.getText().toString();
                String ssub3 = sub3.getText().toString();
                String ssub4 = sub4.getText().toString();
                String ssub5 = sub5.getText().toString();

                if(!(sroll.equals("") || ssub1.equals("") || ssub2.equals("") || ssub3.equals("")|| ssub4.equals("")|| ssub5.equals("")))
                {
                    Intent intent = new Intent(getStudentDetails.this,showStudentDetails.class);
                    intent.putExtra("roll",sroll);
                    intent.putExtra("sub1",ssub1);
                    intent.putExtra("sub2",ssub2);
                    intent.putExtra("sub3",ssub3);
                    intent.putExtra("sub4",ssub4);
                    intent.putExtra("sub5",ssub5);
                    startActivity(intent);
                }
                else
                {
                    roll.setTextColor(getResources().getColor(R.color.red));
                    roll.setText("Please enter all the details!");
                    sub1.setText(null);
                    sub2.setText(null);
                    sub3.setText(null);
                    sub4.setText(null);
                    sub5.setText(null);
                }
            }
        });
    }
}
