package com.example.lab1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {

    Button bstart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        bstart = findViewById(R.id.button);
        bstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = findViewById(R.id.time);
                int i = Integer.parseInt(text.getText().toString());
                Intent intent = new Intent(AlarmActivity.this, MyBroadcastReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 234324243, intent, 0);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i * 1000), pendingIntent);
                Toast.makeText(AlarmActivity.this, "Alarm set in " + i + " seconds", Toast.LENGTH_LONG).show();
            }
        });
    }

}
