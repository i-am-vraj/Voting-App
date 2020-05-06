package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyCandidateActivity extends AppCompatActivity {


    EditText nameET;
    EditText basicEduET;
    EditText pastET;
    EditText workingsET;
    EditText earningsET;
    ImageView imgView1;
    ImageView imgView2;

    Button update, delete;

    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_candidate);

        setTitle("Modify Candidate");

        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        nameET = findViewById(R.id.cnameETm);
        basicEduET = findViewById(R.id.pqualETm);
        pastET = findViewById(R.id.cpastETm);
        workingsET = findViewById(R.id.cworkETm);
        earningsET = findViewById(R.id.cpropertyETm);
        imgView1 = findViewById(R.id.img1);
        imgView2 = findViewById(R.id.img2);

        //disappear
        imgView1.setVisibility(View.GONE);
        imgView2.setVisibility(View.GONE);

        update = findViewById(R.id.update_button);
        delete = findViewById(R.id.delete_button);


        Intent intent = getIntent();
        final String id = intent.getStringExtra("cid");
        String name = intent.getStringExtra("cname");
        String work = intent.getStringExtra("cwork");
        String qual = intent.getStringExtra("pqual");
        String past = intent.getStringExtra("cpast");
        String property = intent.getStringExtra("cproperty");

//        byte[] img1 = intent.getByteArrayExtra("img1");
//        byte[] img2 = intent.getByteArrayExtra("img2");
//        Bitmap bitmap1 = BitmapFactory.decodeByteArray(img1, 0, img1.length);
//        Bitmap bitmap2 = BitmapFactory.decodeByteArray(img2, 0, img2.length);
//        imgView1.setImageBitmap(bitmap1);
//        imgView2.setImageBitmap(bitmap2);

        nameET.setText(name);
        workingsET.setText(work);
        basicEduET.setText(qual);
        pastET.setText(past);
        earningsET.setText(property);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = nameET.getText().toString();
                final String basicEdu = basicEduET.getText().toString();
                final String past = pastET.getText().toString();
                final String work = workingsET.getText().toString();
                final String earning = earningsET.getText().toString();

                databaseManager.update(Integer.parseInt(id), name, basicEdu, work, past, earning);
                Intent home_intent = new Intent(getApplicationContext(), CRUDActivity.class);
                startActivity(home_intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseManager.delete(Integer.parseInt(id));
                Intent home_intent = new Intent(getApplicationContext(), CRUDActivity.class);
                startActivity(home_intent);
            }
        });
    }

}
