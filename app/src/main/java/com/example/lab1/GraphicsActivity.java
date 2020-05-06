package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GraphicsActivity extends AppCompatActivity {

    ImageView iv1, iv2;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        iv1 = (ImageView) findViewById(R.id.imageView2);
        iv2 = (ImageView) findViewById(R.id.imageView3);
        runOnUiThread(new Runnable(){
            public void run() {
                // UI code goes here
                animation = AnimationUtils.loadAnimation(GraphicsActivity.this,R.anim.rotate);
                iv1.startAnimation(animation);
            }
        });
        runOnUiThread(new Runnable(){
            public void run() {
                // UI code goes here
                animation = AnimationUtils.loadAnimation(GraphicsActivity.this,R.anim.alpha);
                iv2.startAnimation(animation);
            }
        });
        new Handler().postDelayed(new Runnable() {
            // Using handler with postDelayed called runnable run method
            @Override
            public void run() {
                Intent i = new Intent(GraphicsActivity.this, MainActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, 5*1000);
    }
}
