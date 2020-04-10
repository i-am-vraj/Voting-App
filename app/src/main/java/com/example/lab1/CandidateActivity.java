package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CandidateActivity extends AppCompatActivity {

    Button bparty;
    TextView name;
    TextView basicEdu;
    TextView notableWorks;
    TextView history;
    TextView earnings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);

        bparty = findViewById(R.id.party);

        name = findViewById(R.id.cname);
        basicEdu = findViewById(R.id.pqual);
        notableWorks = findViewById(R.id.cwork);
        history = findViewById(R.id.cpast);
        earnings = findViewById(R.id.cproperty);

        name.setText("Narendra Modi");
        basicEdu.setText("BBA");
        notableWorks.setText("Daarubandhi,Demonitization");
        history.setText("Gujarat Dange");
        earnings.setText("80 LACS");

        Intent intent = getIntent();
        if(!(intent.getStringExtra("name")  == null)) {
            name.setText(intent.getStringExtra("name"));
            basicEdu.setText(intent.getStringExtra("basicEdu"));
            notableWorks.setText(intent.getStringExtra("notableWorks"));
            history.setText(intent.getStringExtra("history"));
            earnings.setText(intent.getStringExtra("earning"));
        }
        bparty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CandidateActivity.this,MainActivity.class));
            }
        });
    }
}
